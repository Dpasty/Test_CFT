package utils;

import algorithms.ExternalMergeSort;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandLineParser {
    public static ExternalMergeSort parse(String[] args, List<Path> files) {
        ExternalMergeSort externalMergeSort = null;
        Order order = Order.ASC;
        ValueType valueType;
        String outputFileName;

        try {
            if (args.length >= 4) {
                ArrayList<String> argv = new ArrayList<>(List.of(args));
                if (argv.contains("-d")) {
                    order = Order.DESC;
                }

                if (argv.contains("-i")) {
                    valueType = ValueType.INT;
                } else if (argv.contains("-s")) {
                    valueType = ValueType.STRING;
                } else {
                    throw new IllegalArgumentException();
                }

                outputFileName = args[2];
                for (int i = 3; i < args.length; i++) {
                    files.add(Paths.get(args[i]));
                }

                externalMergeSort = new ExternalMergeSort(outputFileName, order, valueType);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid arguments");
        }
        return externalMergeSort;
    }
}
