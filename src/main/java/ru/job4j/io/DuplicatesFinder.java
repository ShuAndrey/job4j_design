package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Запуск приложения.
 *
 * @author Andrey Shulgin
 */
public class DuplicatesFinder {
    /**
     * Запуск приложения.
     *
     * @param args args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        start(new Scanner(System.in), new DuplicatesVisitor());
    }

    private static void start(Scanner scanner, DuplicatesVisitor duplicates) throws IOException {
        System.out.println("Приложение ищет дубликаты файлов.");
        System.out.println("Введите директорию. Например, C:\\Users");
        Files.walkFileTree(Path.of(scanner.nextLine()), duplicates);
        Map<FileProperty, List<Path>> getter = duplicates.getFiles();
        for (FileProperty map : getter.keySet()) {
            if (getter.get(map).size() > 1) {
                System.out.println(String.format("%s - %s bytes", map.getName(), map.getSize()));
                for (Path path : getter.get(map)) {
                    System.out.println(path.toAbsolutePath());
                }
                System.out.println(System.lineSeparator());
            }
        }
    }
}