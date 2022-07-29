package ru.job4j.io;

import ru.job4j.find.ArgsName;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Утилита для вывода данных из файлов формата .csv
 *
 * @author Andrey Shulgin
 */
public class CSVReader {
    /**
     * Метод читает данные из файла, преобразует и выводит на консоль или записывает в другой файл.
     *
     * @param argsName входные данные
     */
    public static void handle(ArgsName argsName) {
        String[] lines;
        List<StringJoiner> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(
                new FileReader(argsName.get("path"), Charset.forName("WINDOWS-1251")))) {
            List<Integer> numbers = filter(argsName, scanner.nextLine());
            while (scanner.hasNext()) {
                StringJoiner line = new StringJoiner(argsName.get("delimiter"));
                lines = scanner.nextLine().split(argsName.get("delimiter"));
                for (Integer number : numbers) {
                    line.add(lines[number]);
                }
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        print(argsName, result);
    }

    /**
     * Преобразование данных после чтения.
     *
     * @param argsName входные данные.
     * @param column   название колонок.
     * @return список индексов.
     */
    private static List<Integer> filter(ArgsName argsName, String column) {
        List<Integer> numbers = new ArrayList<>();
        String[] lines = argsName.get("filter").split(",");
        String[] columns = column.split(argsName.get("delimiter"));
        for (int out = 0; out < lines.length; out++) {
            for (String s : columns) {
                if (lines[out].equals(s)) {
                    numbers.add(out);
                }
            }
        }
        return numbers;
    }

    /**
     * Вывод на консоль или запись результата в файл.
     *
     * @param argsName входные данные.
     * @param result   данные для вывода.
     */
    private static void print(ArgsName argsName, List<StringJoiner> result) {
        if ("stdout".equals(argsName.get("out"))) {
            System.out.println(argsName.get("filter").replace(",", argsName.get("delimiter")));
            result.forEach(System.out::println);
        } else {
            try (PrintWriter out = new PrintWriter(
                    new FileWriter(argsName.get("out"), Charset.forName("WINDOWS-1251"))
            )) {
                out.write(argsName.get("filter").replace(",",  argsName.get("delimiter")) + System.lineSeparator());
                for (StringJoiner stringJoiner : result) {
                    out.write(stringJoiner + System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void check(String[] args, ArgsName jvm) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Incorrect array length.");
        }
        if (!new File((jvm.get("path"))).exists()) {
            throw new IllegalArgumentException("File is not a exists. " + jvm.get("path"));
        }
        if (!jvm.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException(
                    "The file is not in the correct format. " + jvm.get("path")
            );
        }
        if (!";".equals(jvm.get("delimiter"))
                && !",".equals(jvm.get("delimiter"))
                && !"|".equals(jvm.get("delimiter"))) {
            throw new IllegalArgumentException(
                    "Wrong delimiter. Delimiter can be \",\", \";\", \"|\" "
                            + jvm.get("delimiter")
            );
        }
        if (!"stdout".equals(jvm.get("out")) && !jvm.get("out").endsWith(".txt")) {
            throw new IllegalArgumentException(
                    "The file is not in the correct format. "
                            + "The file must be .txt. "
                            + "To output to the console, write stdout "
                            + jvm.get("out"));
        }
        if (!(jvm.get("filter").contains(","))) {
            throw new IllegalArgumentException("Lines must be separated by a comma. " + jvm.get("filter"));
        }
    }

    /**
     * Запуск утилиты.
     *
     * @param args аргументы.
     */
    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(args);
        check(args, jvm);
        handle(jvm);
    }
}
