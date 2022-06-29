package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            in.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
