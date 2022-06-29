package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    public List<String> filter(String file) {
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
     * Демонстрация работы метода filter.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        log.forEach(System.out::println);
    }
}