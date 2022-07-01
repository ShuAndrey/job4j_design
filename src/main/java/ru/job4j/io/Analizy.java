package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс ищет ошибки в логах и записывает в файл время ошибок.
 *
 * @author Andrey Shulgin
 */
public class Analizy {
    /**
     * Метод ищет ошибки в логах и записывает в файл время ошибок.
     *
     * @param source логи.
     * @param target файл для записи ошибок.
     */
    public void unavailable(String source, String target) {
        List<String> times = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line = reader.readLine();
            String threeLetters;
            String timeStart = null;
            String timeFinish;
            while (line != null) {
                threeLetters = line.substring(0, 3);
                if (timeStart == null && ("400".equals(threeLetters) || "500".equals(threeLetters))) {
                    timeStart = line.substring(4);
                }
                if (timeStart != null && !"400".equals(threeLetters) && !"500".equals(threeLetters)) {
                    timeFinish = line.substring(4);
                    times.add(timeStart + ";" + timeFinish);
                    timeStart = null;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            times.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Запуск программы.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        new Analizy().unavailable("server.log", "unavailable.csv");
    }

}