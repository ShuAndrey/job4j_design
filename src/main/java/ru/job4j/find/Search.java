package ru.job4j.find;

import ru.job4j.io.SearchFiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Andrey Shulgin
 */
public class Search {
    public static void main(String[] args) throws IOException {
        isValid(args);
        Path start = Paths.get(args[0]);
        String format = args[1];
        search(start, p -> p.toFile()
                .getName()
                .endsWith(format))
                .forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void isValid(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Wrong number of arguments. "
                            + "Usage java -jar Search.jar ROOT_FOLDER FILE_FORMAT."
            );
        }
        if (!new File(args[0]).isDirectory()) {
            throw new IllegalArgumentException("Directory does not exist or is not a directory.");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("File format not specified.");
        }
    }
}