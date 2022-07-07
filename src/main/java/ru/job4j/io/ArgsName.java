package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * Программа разбивает массив на пары ключ-значение.
 *
 * @author Andrey Shulgin
 */
public class ArgsName {

    /**
     * Коллекция для хранения пар ключ-значение.
     */
    private final Map<String, String> values = new HashMap<>();

    /**
     * Метод возвращает значение по ключу.
     * Если ключ не найден генерирует исключение {@link IllegalArgumentException}.
     *
     * @param key ключ.
     * @return значение.
     */
    public String get(String key) {
        String result = values.get(key);
        if (result == null) {
            throw new IllegalArgumentException("Key value pair missing. " + key);
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Array is empty.");
        }
        for (String arg : args) {
            int index = arg.indexOf("=");
            check(arg, index);
            values.put(arg.substring(1, index), arg.substring(index + 1));
        }
    }

    private void check(String arg, int index) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException("Value is no dash. " + arg);
        }
        if (!arg.contains("=")) {
            throw new IllegalArgumentException("Value has no equal sign. " + arg);
        }
        if (arg.startsWith("-=")) {
            throw new IllegalArgumentException("Value has no key. " + arg);
        }
        if (index == arg.length() - 1) {
            throw new IllegalArgumentException("Value is empty. " + arg);
        }
    }

    /**
     * Метод разбивает массив на пары ключ значение.
     *
     * @param args массив
     * @return коллекция пар ключ-значение.
     */
    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    /**
     * Демонстрация работы программы.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
