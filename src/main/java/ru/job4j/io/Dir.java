package ru.job4j.io;

import java.io.File;
import java.util.Objects;

/**
 * Программа проверяет что директория projects - это директория и выводит
 * ее содержимое.
 *
 * @author Andrey Shulgin
 */
public class Dir {
    /**
     * Запуск программы.
     *
     * @param args args
     */
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            System.out.println(String.format("%s %s", subfile.getName(), subfile.length()));
        }
    }
}
