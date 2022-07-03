package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

/**
 * Класс для поиска дубликатов файла.
 *
 * @author Andrey Shulgin
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    /**
     * Файл у которого необходимо найти дубликаты.
     */
    private final FileProperty fileProperty;

    public DuplicatesVisitor(FileProperty fileProperty) {
        this.fileProperty = fileProperty;
    }

    /**
     * Метод для поиска дубликатов файла если они есть.
     *
     * @param file  a reference to the file
     * @param attrs the file's basic attributes
     * @return - возвращает аюсолютный путь к файлу.
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Objects.equals(file.getFileName().toString(), fileProperty.getName())
                && file.toFile().length() == fileProperty.getSize()) {
            System.out.println(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}