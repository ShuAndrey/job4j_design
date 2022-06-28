package ru.job4j.io;

import java.io.FileInputStream;

/**
 * Класс показывает работу FileInputStream.
 *
 * @author Andrey Shulgin
 */
public class EvenNumberFile {
    /**
     * Метод читает файл и проверяет числа на четность.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int rst = Integer.parseInt(line);
                System.out.println(rst % 2 == 0
                        ? line + " - четное."
                        : line + " - нечетное.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}