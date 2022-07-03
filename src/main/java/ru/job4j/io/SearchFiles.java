package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Поиск файлов по предикату.
 *
 * @author Andrey Shulgin
 */
public class SearchFiles implements FileVisitor<Path> {
    /**
     * Предикат
     */
    private final Predicate<Path> condition;
    /**
     * Коллекция для хранения.
     */
    private List<Path> paths = new ArrayList<>();

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }

    /**
     * Логика, которую надо выполнять перед входом в папку
     *
     * @param dir   a reference to the directory
     * @param attrs the directory's basic attributes
     * @return продолжение обхода дерева.
     * @throws IOException
     */
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    /**
     * Логика того, что нужно делать с каждым файлом.
     *
     * @param file  a reference to the file
     * @param attrs the file's basic attributes
     * @return продолжение обхода дерева.
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path path = file.toAbsolutePath();
        if (condition.test(path)) {
            paths.add(path);
            System.out.println(path);
        }
        return CONTINUE;
    }

    /**
     * Логика, если вход в файл невозможен (нет доступа, или другие причины).
     *
     * @param file a reference to the file
     * @param exc  the I/O exception that prevented the file from being visited
     * @return продолжение обхода дерева.
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    /**
     * Логика, которую надо выполнять после захода в папку.
     *
     * @param dir a reference to the directory
     * @param exc {@code null} if the iteration of the directory completes without
     *            an error; otherwise the I/O exception that caused the iteration
     *            of the directory to complete prematurely
     * @return продолжение обхода дерева.
     * @throws IOException
     */
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }

    /**
     * Метод возвращает коллекцию путей к файлам.
     *
     * @return коллекция.
     */
    public List<Path> getPaths() {
        List<Path> result = new ArrayList<>(paths);
        paths = null;
        return result;
    }
}
