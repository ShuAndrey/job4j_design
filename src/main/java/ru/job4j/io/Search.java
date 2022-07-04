package ru.job4j.io;

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
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Root folder is null. Usage java -jar Search.jar ROOT_FOLDER."
            );
        }
        if (args.length == 1) {
            throw new IllegalArgumentException(
                    "File format not specified. Usage java -jar Search.jar FILE_FORMAT."
            );
        }
    }
}