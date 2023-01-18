package algorithms;

import org.apache.commons.io.FileUtils;
import utils.Order;
import utils.ValueType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ExternalMergeSort {
    private static final int MAX_FILES_PER_MERGE = 100;
    private static final int BUFFER_SIZE = 8192;
    private static final Path TEMP_DIRECTORY = Paths.get("temp_files_for_merge");
    private String outputFileName;
    private Order order;
    private ValueType valueType;
    private Comparator comparator;

    public ExternalMergeSort(String outputFileName, Order order, ValueType valueType) {
        this.order = order;
        setComparator();
        this.valueType = valueType;
        this.outputFileName = outputFileName;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public Path merge(List<Path> files) {
        try {
            for (int i = 0; i < files.size(); i++) {
                if (Files.notExists(files.get(i)) || isEmptyFile(files.get(i))) {
                    System.out.println("Error: file not exists or empty, skip");
                    files.remove(i);
                    i--;
                }
            }
            while (files.size() > 1) {
                List<Path> nextRound = new ArrayList<>();
                for (int i = 0; i < files.size(); i += MAX_FILES_PER_MERGE) {
                    Path merged = mergeGroup(files.subList(i, Math.min(files.size(), i + MAX_FILES_PER_MERGE)));
                    nextRound.add(merged);
                }
                files = nextRound;
            }

            Path result;
            if (files.isEmpty()) {
                result = null;
            } else {
                result = files.get(0);
                if (Files.exists(Paths.get(outputFileName))) {
                    Files.delete(Paths.get(outputFileName));
                }
                Path output = Paths.get(outputFileName);
                Files.copy(result, output);
            }

            return result;
        } catch (IOException e) {
            System.err.println("Error: trouble with files files");
            throw new UncheckedIOException(e);
        }
    }

    private boolean isEmptyFile(Path file) {
        try {
            return Files.size(file) == 0;
        } catch (IOException e) {
            System.err.println("Error: can't check file size");
            throw new UncheckedIOException(e);
        }
    }

    public static void cleanTempFiles() {
        try {
            FileUtils.cleanDirectory(TEMP_DIRECTORY.toFile());
            Files.delete(TEMP_DIRECTORY);
        } catch (IOException e) {
            System.err.println("Error when clean temp directory");
        }
    }

    private Path mergeGroup(List<Path> list) throws IOException {
        Path output;

        if (list.size() == 1) {
            output = list.get(0);
            Files.copy(list.get(0), Paths.get("out.txt"));
        } else {
            if (!Files.exists(TEMP_DIRECTORY)) {
                Files.createDirectory(TEMP_DIRECTORY);
            }

            List<State> states = new ArrayList<>();
            for (Path file : list) {
                State state = createState(file);
                states.add(state);
            }

            output = Files.createTempFile(TEMP_DIRECTORY, "", "");
            try (OutputStream out = new BufferedOutputStream(new FileOutputStream(output.toString()), BUFFER_SIZE);
                 Writer writer = new BufferedWriter(new OutputStreamWriter(out))) {

                PriorityQueue<State> queue;
                if (valueType == ValueType.INT) {
                    queue = new PriorityQueue<>((x, y) -> comparator.compare(Integer.parseInt(x.value), Integer.parseInt(y.value)));
                } else {
                    queue = new PriorityQueue<>((x, y) -> comparator.compare(x.value, y.value));
                }
                queue.addAll(states);

                String last = null;
                while (!queue.isEmpty()) {
                    State state = queue.poll();
                    last = compareAndWrite(writer, state, last);

                    state.value = readAutoClosing(state.reader);
                    while (state.value != null && !checkSorting(last, state.value)) {
                        state.value = readAutoClosing(state.reader);
                    }

                    if (state.value != null) {
                        queue.offer(state);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error: trouble with temp file");
            }
        }
        return output;
    }

    private String compareAndWrite(Writer writer, State state, String last) throws IOException {
        if (valueType == ValueType.INT) {
            if (last == null
                    || comparator.compare(Integer.parseInt(state.value), Integer.parseInt(last)) == 0
                    || comparator.compare(Integer.parseInt(state.value), Integer.parseInt(last)) != 0) {
                last = writeInFile(writer, state);
            }
        } else {
            if (last == null || comparator.compare(state.value, last) == 0 || comparator.compare(state.value, last) != 0) {
                last = writeInFile(writer, state);
            }
        }
        return last;
    }

    private String writeInFile(Writer writer, State state) throws IOException {
        writer.write(state.value + "\n");
        return state.value;
    }

    private boolean checkSorting(String last, String current) {
        boolean result;
        if (valueType == ValueType.INT) {
            result = comparator.compare(Integer.parseInt(current), Integer.parseInt(last)) >= 0;
        } else {
            result = comparator.compare(current, last) >= 0;
        }
        return result;
    }

    private State createState(Path file) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(file.toString()), BUFFER_SIZE);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String value = readAutoClosing(reader);

        return new State(file, reader, value);
    }

    private String readAutoClosing(BufferedReader reader) throws IOException {
        String value = reader.readLine();

        if (valueType == ValueType.INT) {
            while (value != null && !value.matches("^[1-9][0-9]*$")) {
                value = reader.readLine();
                System.out.println("Error: string when expected int, skip.");
            }
        }

        while (value != null && (value.contains(" ") || value.equals(""))) {
            value = reader.readLine();
            System.out.println("Error: string with spaces or empty string, skip.");
        }

        if (value == null) {
            reader.close();
        }
        return value;
    }

    private void setComparator() {
        if (order == Order.ASC) {
            comparator = Comparator.naturalOrder();
        } else {
            comparator = Comparator.reverseOrder();
        }
    }

    private static final class State {
        final Path file;
        BufferedReader reader;
        String value;

        State(Path file, BufferedReader reader, String value) {
            this.file = file;
            this.reader = reader;
            this.value = value;
        }
    }
}
