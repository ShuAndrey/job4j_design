package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * Очередь на двух стеках.
 *
 * @author Andrey Shulgin
 */
public class SimpleQueue<T> {
    /**
     * Стек в который добавляют элементы.
     */
    private final SimpleStack<T> in = new SimpleStack<>();
    /**
     * Стек из которого удаляют элементы.
     */
    private final SimpleStack<T> out = new SimpleStack<>();
    /**
     * Количество элементов в стеке In.
     */
    private int countIn;
    /**
     * Количество элементов в стеке Out.
     */
    private int countOut;

    /**
     * Метод возвращает первое значение и удаляет его из стека.
     * Если стек пуст генерирует исключение
     * {@link NoSuchElementException}
     *
     * @return - значение.
     */
    public T poll() {
        if (countOut == 0) {
            changeStack();
        }
        if (countOut == 0) {
            throw new NoSuchElementException();
        }
        countOut--;
        return out.pop();
    }

    /**
     * Добавляет значение в конец стека.
     *
     * @param value - значение.
     */
    public void push(T value) {
        in.push(value);
        countIn++;
    }

    /**
     * Метод копирует значение из стека In в стек Out.
     */
    private void changeStack() {
        while (countIn > 0) {
            out.push(in.pop());
            countIn--;
            countOut++;
        }
    }
}
