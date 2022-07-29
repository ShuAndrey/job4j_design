package ru.job4j.io;

import ru.job4j.find.ArgsName;
import ru.job4j.find.Search;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Архивирует проект.
 *
 * @author Andrey Shulgin
 */
public class Zip {

    /**
     * Создание архива.
     *
     * @param sources файлы для архивации.
     * @param target  архив.
     */
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(String.valueOf(source)))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Запуск приложения.
     *
     * @param args args
     */
    public static void main(String[] args) throws IOException {
        ArgsName jvm = ArgsName.of(args);
        check(args, jvm);
        List<Path> sources = Search.search(Paths.get(jvm.get("d")), p -> !p.toFile()
                .getName()
                .endsWith(jvm.get("e")));
        new Zip().packFiles(sources, new File(jvm.get("o")));
    }

    private static void check(String[] args, ArgsName jvm) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Incorrect array length.");
        }
        if (!new File((jvm.get("d"))).isDirectory()) {
            throw new IllegalArgumentException("Path is not a directory. " + jvm.get("d"));
        }
        if (!jvm.get("e").startsWith(".")) {
            throw new IllegalArgumentException("The value is not in the correct format. " + jvm.get("e"));
        }
        if (!jvm.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("The value must be in .zip format. " + jvm.get("o"));
        }
    }
}
