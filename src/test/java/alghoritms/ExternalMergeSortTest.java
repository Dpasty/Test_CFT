package alghoritms;

import algorithms.ExternalMergeSort;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.Order;
import utils.ValueType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class ExternalMergeSortTest {
    @AfterEach
    public void clear() {
        try {
            Files.delete(Paths.get("out.txt"));
        } catch (IOException e) {
            System.err.println("File does not exists");
        }
    }

    @AfterAll
    public static void cleanTemp() {
        ExternalMergeSort.cleanTempFiles();
    }

    @Test
    void mergeTestNormalSingle() throws IOException {
        var externalMergeSort = new ExternalMergeSort("out.txt", Order.ASC, ValueType.INT);
        List<Path> files = new ArrayList<>() {};
        files.add(Paths.get("src/test/resources/normal_tests/int_asc_1.txt"));

        ArrayList<Integer> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(externalMergeSort.merge(files).toString()))) {
            while(reader.ready()) {
                result.add(Integer.parseInt(reader.readLine()));
            }
        }

        List<Integer> answer = result.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for (int i = 0; i < answer.size(); i++) {
            Assertions.assertEquals(result.get(i), answer.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "src/test/resources/normal_tests/int_asc_1.txt, src/test/resources/normal_tests/int_asc_2.txt, src/test/resources/normal_tests/int_asc_3.txt"
    })
    void mergeTestNormalIntegerMultiple(String file_1, String file_2, String file_3) throws IOException {
        var externalMergeSort = new ExternalMergeSort("out.txt", Order.ASC, ValueType.INT);
        List<Path> files = new ArrayList<>() {};
        files.add(Paths.get(file_1));
        files.add(Paths.get(file_2));
        files.add(Paths.get(file_3));

        ArrayList<Integer> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(externalMergeSort.merge(files).toString()))) {
            while(reader.ready()) {
                result.add(Integer.parseInt(reader.readLine()));
            }
        }

        List<Integer> answer = result.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for (int i = 0; i < answer.size(); i++) {
            Assertions.assertEquals(result.get(i), answer.get(i));
        }
    }

    @Test
    void mergeTestNormalIntegerSingleDesc() throws IOException {
        var externalMergeSort = new ExternalMergeSort("out.txt", Order.DESC, ValueType.INT);
        List<Path> files = new ArrayList<>() {};
        files.add(Paths.get("src/test/resources/normal_tests/int_desc_1.txt"));

        ArrayList<Integer> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(externalMergeSort.merge(files).toString()))) {
            while(reader.ready()) {
                result.add(Integer.parseInt(reader.readLine()));
            }
        }

        List<Integer> answer = result.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (int i = 0; i < answer.size(); i++) {
            Assertions.assertEquals(result.get(i), answer.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "src/test/resources/normal_tests/int_desc_1.txt, src/test/resources/normal_tests/int_desc_2.txt, src/test/resources/normal_tests/int_desc_3.txt"
    })
    void mergeTestNormalIntegerMultipleDesc(String file_1, String file_2, String file_3) throws IOException {
        var externalMergeSort = new ExternalMergeSort("out.txt", Order.DESC, ValueType.INT);
        List<Path> files = new ArrayList<>() {};
        files.add(Paths.get(file_1));
        files.add(Paths.get(file_2));
        files.add(Paths.get(file_3));

        ArrayList<Integer> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(externalMergeSort.merge(files).toString()))) {
            while(reader.ready()) {
                result.add(Integer.parseInt(reader.readLine()));
            }
        }

        List<Integer> answer = result.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (int i = 0; i < answer.size(); i++) {
            Assertions.assertEquals(result.get(i), answer.get(i));
        }
    }

    @Test
    void mergeTestNormalStringSingle() throws IOException {
        var externalMergeSort = new ExternalMergeSort("out.txt", Order.ASC, ValueType.STRING);
        List<Path> files = new ArrayList<>() {};
        files.add(Paths.get("src/test/resources/normal_tests/string_asc_1.txt"));

        ArrayList<String> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(externalMergeSort.merge(files).toString()))) {
            while(reader.ready()) {
                result.add(reader.readLine());
            }
        }

        List<String> answer = result.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for (int i = 0; i < answer.size(); i++) {
            Assertions.assertEquals(result.get(i), answer.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "src/test/resources/normal_tests/string_asc_1.txt, src/test/resources/normal_tests/string_asc_2.txt, src/test/resources/normal_tests/string_asc_3.txt"
    })
    void mergeTestNormalStringMultiple(String file_1, String file_2, String file_3) throws IOException {
        var externalMergeSort = new ExternalMergeSort("out.txt", Order.ASC, ValueType.STRING);
        List<Path> files = new ArrayList<>() {};
        files.add(Paths.get(file_1));
        files.add(Paths.get(file_2));
        files.add(Paths.get(file_3));

        ArrayList<String> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(externalMergeSort.merge(files).toString()))) {
            while(reader.ready()) {
                result.add(reader.readLine());
            }
        }

        List<String> answer = result.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for (int i = 0; i < answer.size(); i++) {
            Assertions.assertEquals(result.get(i), answer.get(i));
        }
    }

    @Test
    void mergeTestNormalStringSingleDesc() throws IOException {
        var externalMergeSort = new ExternalMergeSort("out.txt", Order.DESC, ValueType.STRING);
        List<Path> files = new ArrayList<>() {};
        files.add(Paths.get("src/test/resources/normal_tests/string_desc_1.txt"));

        ArrayList<String> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(externalMergeSort.merge(files).toString()))) {
            while(reader.ready()) {
                result.add(reader.readLine());
            }
        }

        List<String> answer = result.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (int i = 0; i < answer.size(); i++) {
            Assertions.assertEquals(result.get(i), answer.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "src/test/resources/normal_tests/string_desc_1.txt, src/test/resources/normal_tests/string_desc_2.txt, src/test/resources/normal_tests/string_desc_3.txt"
    })
    void mergeTestNormalStringMultipleDesc(String file_1, String file_2, String file_3) throws IOException {
        var externalMergeSort = new ExternalMergeSort("out.txt", Order.DESC, ValueType.STRING);
        List<Path> files = new ArrayList<>() {};
        files.add(Paths.get(file_1));
        files.add(Paths.get(file_2));
        files.add(Paths.get(file_3));

        ArrayList<String> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(externalMergeSort.merge(files).toString()))) {
            while(reader.ready()) {
                result.add(reader.readLine());
            }
        }

        List<String> answer = result.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (int i = 0; i < answer.size(); i++) {
            Assertions.assertEquals(result.get(i), answer.get(i));
        }
    }

    @Test
    void mergeTestEmptyFiles() {
        var externalMergeSort = new ExternalMergeSort("out.txt", Order.DESC, ValueType.STRING);
        List<Path> files = new ArrayList<>() {};
        files.add(Paths.get("src/test/resources/errors_tests/empty_file_1.txt"));
        files.add(Paths.get("src/test/resources/errors_tests/empty_file_2.txt"));

        Assertions.assertNull(externalMergeSort.merge(files));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "src/test/resources/errors_tests/int_with_spaces.txt, src/test/resources/errors_tests/int_without_spaces.txt"
    })
    void mergeTestFileWithSpaces(String file_1, String file_2) throws IOException {
        var externalMergeSort = new ExternalMergeSort("out.txt", Order.ASC, ValueType.INT);
        List<Path> files = new ArrayList<>() {};
        files.add(Paths.get(file_1));
        files.add(Paths.get(file_2));

        ArrayList<Integer> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(externalMergeSort.merge(files).toString()))) {
            while(reader.ready()) {
                result.add(Integer.parseInt(reader.readLine()));
            }
        }

        List<Integer> answer = result.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for (int i = 0; i < answer.size(); i++) {
            Assertions.assertEquals(result.get(i), answer.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "src/test/resources/errors_tests/string_with_empty_lines_1.txt, src/test/resources/errors_tests/string_with_empty_lines_2.txt"
    })
    void mergeTestFileWithEmptyStrings(String file_1, String file_2) throws IOException {
        var externalMergeSort = new ExternalMergeSort("out.txt", Order.ASC, ValueType.STRING);
        List<Path> files = new ArrayList<>() {};
        files.add(Paths.get(file_1));
        files.add(Paths.get(file_2));

        ArrayList<String> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(externalMergeSort.merge(files).toString()))) {
            while(reader.ready()) {
                result.add(reader.readLine());
            }
        }

        List<String> answer = result.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for (int i = 0; i < answer.size(); i++) {
            Assertions.assertEquals(result.get(i), answer.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "src/test/resources/errors_tests/error_in_sort_strings_asc_1.txt, src/test/resources/errors_tests/error_in_sort_strings_asc_2.txt"
    })
    void mergeTestFileWithErrorInSortStringsAsc(String file_1, String file_2) throws IOException {
        var externalMergeSort = new ExternalMergeSort("out.txt", Order.ASC, ValueType.STRING);
        List<Path> files = new ArrayList<>() {};
        files.add(Paths.get(file_1));
        files.add(Paths.get(file_2));
        ArrayList<String> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(externalMergeSort.merge(files).toString()))) {
            while(reader.ready()) {
                result.add(reader.readLine());
            }
        }

        List<String> answer = result.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for (int i = 0; i < answer.size(); i++) {
            Assertions.assertEquals(result.get(i), answer.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "src/test/resources/errors_tests/error_in_sort_int_desc_1.txt, src/test/resources/errors_tests/error_in_sort_int_desc_2.txt"
    })
    void mergeTestFileWithErrorInSortIntDesc(String file_1, String file_2) throws IOException {
        var externalMergeSort = new ExternalMergeSort("out.txt", Order.DESC, ValueType.INT);
        List<Path> files = new ArrayList<>() {};
        files.add(Paths.get(file_1));
        files.add(Paths.get(file_2));

        ArrayList<Integer> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(externalMergeSort.merge(files).toString()))) {
            while(reader.ready()) {
                result.add(Integer.parseInt(reader.readLine()));
            }
        }

        List<Integer> answer = result.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (int i = 0; i < answer.size(); i++) {
            Assertions.assertEquals(result.get(i), answer.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "src/test/resources/errors_tests/string_in_int_file_1.txt, src/test/resources/errors_tests/string_in_int_file_2.txt"
    })
    void mergeTestStringInIntFile(String file_1, String file_2) throws IOException {
        var externalMergeSort = new ExternalMergeSort("out.txt", Order.ASC, ValueType.INT);
        List<Path> files = new ArrayList<>() {};
        files.add(Paths.get(file_1));
        files.add(Paths.get(file_2));

        ArrayList<Integer> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(externalMergeSort.merge(files).toString()))) {
            while(reader.ready()) {
                result.add(Integer.parseInt(reader.readLine()));
            }
        }

        List<Integer> answer = result.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for (int i = 0; i < answer.size(); i++) {
            Assertions.assertEquals(result.get(i), answer.get(i));
        }
    }

    @Test
    public void mergeTestNotExistsFiles() {
        var externalMergeSort = new ExternalMergeSort("out.txt", Order.ASC, ValueType.INT);
        List<Path> files = new ArrayList<>() {};
        files.add(Paths.get("fdsfdsfs.txt"));
        Assertions.assertNull(externalMergeSort.merge(files));
    }
}
