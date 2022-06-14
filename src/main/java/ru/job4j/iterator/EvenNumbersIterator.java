package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для четных цифр массива.
 *
 * @author Andrey Shulgin
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    /**
     * Массив.
     */
    private int[] data;
    /**
     * Указатель итератора.
     */
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * Метод hasNext проверяет, если ли следующий элемент.
     *
     * @return - true если следующий элемент существует.
     */
    @Override
    public boolean hasNext() {
        boolean rst = false;
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                rst = true;
                index = i;
                break;
            }
        }
        return rst;
    }

    /**
     * Метод next возвращает следующий четный элемент массива.
     *
     * @return - следующий элемент массива.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
