package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        FileProperty file = new FileProperty(12, "even.txt");
        System.out.println(String.format("%s - %s bite", file.getName(), file.getSize()));
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor(file));
    }
}