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
        Scanner scanner = new Scanner(System.in);
        DuplicatesVisitor duplicates = new DuplicatesVisitor();
        Map<FileProperty, List<Path>> getter = duplicates.getFiles();

        System.out.println("Приложение ищет дубликаты файлов.");
        System.out.println("Введите директорию. Например, C:\\Users");

        Files.walkFileTree(Path.of(scanner.nextLine()), duplicates);

        for (FileProperty map : getter.keySet()) {
            if (getter.get(map).size() > 1) {
                System.out.println(String.format("%s - %s bite", map.getName(), map.getSize()));
                for (Path path : getter.get(map)) {
                    System.out.println(path.toAbsolutePath());
                }
                System.out.println(System.lineSeparator());
            }
        }
    }
}