package app;

import algorithms.ExternalMergeSort;
import utils.CommandLineParser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Path> files = new ArrayList<>();
        ExternalMergeSort externalMergeSort = CommandLineParser.parse(args, files);
        if (externalMergeSort != null) {
            externalMergeSort.merge(files);
        }
    }
}
