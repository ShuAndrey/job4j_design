package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Класс для чтения настроек из файла.
 *
 * @author Andrey Shulgin
 */
public class Config {

    /**
     * Путь к файлу.
     */
    private final String path;
    /**
     * Карта для хранения значений.
     */
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод читает файл и записывает данные в коллекцию.
     * Если в файле не правильные значения конфигурации,
     * то возникает ошибка {@link IllegalArgumentException}.
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String line = read.readLine();
            int index;
            while (line != null) {
                if (!line.contains("#") && line.contains("=")) {
                    index = line.indexOf("=");
                    if (index == 0 || index == line.length() - 1) {
                        throw new IllegalArgumentException();
                    }
                    values.put(line.substring(0, index), line.substring(index + 1));
                }
                line = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ВОзвращает значение из коллекии по ключу.
     *
     * @param key ключ.
     * @return значение.
     */
    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    /**
     * Вывод файла на консоль.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}