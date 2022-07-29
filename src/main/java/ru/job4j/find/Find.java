package ru.job4j.find;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Поиск файлов по критерию.
 *
 * @author Andrey Shulgin
 */
public class Find {
    /**
     * Запуск утилиты.
     *
     * @param args аргументы.
     */
    public static void main(String[] args) {
        message();
        ArgsName jvm = ArgsName.of(args);
        check(args, jvm);
        run(jvm);
    }


    /**
     * Метод проверяет по какому критерию искать файлы.
     *
     * @param jvm ключи из аргументов
     */
    private static void run(ArgsName jvm) {
        List<Path> result = new ArrayList<>();
        if ("name".equals(jvm.get("t"))) {
            result = name(jvm);
        }
        if ("mask".equals(jvm.get("t"))) {
            result = mask(jvm);
        }
        if ("regex".equals(jvm.get("t"))) {
            result = regex(jvm);
        }
        saveLog(jvm, result);
    }

    /**
     * Проверка правильного внесения ключей для запуска.
     *
     * @param args аргументы
     * @param jvm  ключи из аргументов
     */
    private static void check(String[] args, ArgsName jvm) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Неверное количество параметров.");
        }
        if (!new File((jvm.get("d"))).isDirectory()) {
            throw new IllegalArgumentException("Путь не является каталогом. " + jvm.get("d"));
        }
        if (!"name".equals(jvm.get("t")) && !"mask".equals(jvm.get("t")) && !"regex".equals(jvm.get("t"))) {
            throw new IllegalArgumentException("Тип поиска может быть mask, name или regex " + jvm.get("t"));
        }
        if (!jvm.get("o").endsWith(".txt")) {
            throw new IllegalArgumentException("Файл для записи должен быть формата .txt " + jvm.get("o"));
        }
    }

    /**
     * Поиск по имени файла.
     *
     * @param jvm ключи из аргументов
     * @return список найденых файлов.
     */
    private static List<Path> name(ArgsName jvm) {
        List<Path> result = new ArrayList<>();
        try {
            result = Search.search(Paths.get(jvm.get("d")), p -> p.toFile()
                    .getName()
                    .equals(jvm.get("n")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Поиск по маске.
     *
     * @param jvm ключи из аргументов
     * @return список найденых файлов.
     */
    private static List<Path> mask(ArgsName jvm) {
        List<Path> result = new ArrayList<>();
        PathMatcher matcher = FileSystems.getDefault()
                .getPathMatcher("glob:" + jvm.get("n"));
        try {
            result = Search.search(Paths.get(jvm.get("d")), p -> matcher.matches(p.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Поиск по регулярному выражению.
     *
     * @param jvm ключи из аргументов
     * @return список найденых файлов.
     */
    private static List<Path> regex(ArgsName jvm) {
        List<Path> result = new ArrayList<>();
        try {
            result = Search.search(Paths.get(jvm.get("d")), p -> p.toFile()
                    .getName()
                    .matches(jvm.get("n")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Сохранение списка найденых файлов в файл.
     *
     * @param jvm    ключи из аргументов
     * @param result список найденных файлов
     */
    private static void saveLog(ArgsName jvm, List<Path> result) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(jvm.get("o"), Charset.forName("WINDOWS-1251")))) {
            result.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Подсказка при запуске утилиты.
     */
    private static void message() {
        String ln = System.lineSeparator();
        System.out.println("При запуске утилиты необходимо указать:" + ln
                + "-d - директория, в которой начинать поиск." + ln
                + "-n - имя файла, маска, либо регулярное выражение." + ln
                + "-t - тип поиска: mask искать по маске, name по полному совпадение имени, "
                + "regex по регулярному выражению." + ln
                + "-o - результат записать в файл."
        );
    }
}
