package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для массива с последнего элемента к первому.
 *
 * @author Andrey Shulgin
 */
public class BackwardArrayIt implements Iterator<Integer> {
    /**
     * Массив.
     */
    private final int[] data;
    /**
     * Указатель итератора.
     */
    private int point = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    /**
     * Метод hasNext проверяет, если ли следующий элемент.
     *
     * @return - true если следующий элемент существует.
     */
    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    /**
     * Метод next возвращает следующий элемент массива. От последнего к первому.
     *
     * @return - следующий элемент массива.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[data.length - 1 - point++];
    }
}