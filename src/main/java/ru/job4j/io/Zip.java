package ru.job4j.io;

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
     * Архив файла.
     *
     * @param source файл для архивации.
     * @param target архив.
     */
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
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
        List<Path> sources = Search.search(Paths.get(jvm.get("d")), p -> !p.toFile()
                .getName()
                .endsWith(jvm.get("e")));
        new Zip().packFiles(sources, new File(jvm.get("o")));
    }
}
