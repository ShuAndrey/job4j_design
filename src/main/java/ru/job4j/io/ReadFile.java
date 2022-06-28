package ru.job4j.io;

import java.io.FileInputStream;

/**
 * Класс показывает работу FileInputStream.
 *
 * @author Andrey Shulgin
 */
public class ReadFile {
    /**
     * Метод читает файл и выводит его в консоль.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("input.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}