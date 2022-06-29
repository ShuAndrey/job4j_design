package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс демнстрирует работу BufferReader.
 *
 * @author Andrey Shulgin
 */
public class LogFilter {
    /**
     * Метод читает файл и сохраняет в коллекцию строки, где предпоследнеее число это 404.
     *
     * @param file файл.
     * @return коллекция.
     */
    public static List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line = in.readLine();
            String[] array;
            while (line != null) {
                array = line.split(" ");
                if ("404".equals(array[array.length - 2])) {
                    result.add(line);
                }
                line = in.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод запсывает логи в файл.
     *
     * @param log  логи.
     * @param file файл.
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Демонстрация работы метода filter.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}