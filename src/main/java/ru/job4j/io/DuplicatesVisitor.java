package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс для поиска дубликатов в дериктории.
 *
 * @author Andrey Shulgin
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    /**
     * Хранилище файлов.
     */
    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    public Map<FileProperty, List<Path>> getFiles() {
        return files;
    }

    /**
     * Метод для поиска дубликатов если они есть.
     *
     * @param file  a reference to the file
     * @param attrs the file's basic attributes
     * @return продолжение обхода дерева.
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        long length = file.toFile().length();
        String name = file.getFileName().toString();
        FileProperty fileProperty = new FileProperty(length, name);
        if (files.putIfAbsent(fileProperty, new ArrayList<>()) == null) {
            files.get(fileProperty).add(file);
        } else {
            files.get(fileProperty).add(file);
        }
        return super.visitFile(file, attrs);
    }
}