package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Andrey Shulgin
 */
public class ConsoleChat {
    /**
     * Константа для выхода из чата.
     */
    private static final String OUT = "закончить";
    /**
     * Константа для приостановки ответов бота.
     */
    private static final String STOP = "стоп";
    /**
     * Константа для возобновления ответов бота.
     */
    private static final String CONTINUE = "продолжить";
    /**
     * Путь для записи диалога.
     */
    private final String path;
    /**
     * Путь для файла с ответами бота.
     */
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Запуск приложения.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<String> phrases = readPhrases();
        List<String> dialog = new ArrayList<>();
        String question = scanner.nextLine();
        String answer;
        boolean respond = true;
        while (!OUT.equals(question)) {
            dialog.add(question);
            if (STOP.equals(question)) {
                respond = false;
            }
            if (CONTINUE.equals(question)) {
                respond = true;
            }
            if (respond) {
                answer = phrases.get(random(phrases.size()));
                dialog.add(answer);
                System.out.println(answer);
            }
            question = scanner.nextLine();
        }
        saveLog(dialog);
    }

    /**
     * Метод получения псевдослучайного вещественного числа от 0 до max (исключая max);
     */
    private static int random(int max) {
        return (int) (Math.random() * max);
    }

    /**
     * Метод для записи ответов бота в список.
     *
     * @return список.
     */
    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            String line = br.readLine();
            while (line != null) {
                result.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод для записи диалога в файл.
     *
     * @param log диалог
     */
    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251")))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Запуск приложения.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("dialog.txt", "answers.txt");
        cc.run();
    }
}
