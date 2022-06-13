package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для двухмерного массива.
 *
 * @author Andrey Shulgin
 */
public class MatrixIt implements Iterator<Integer> {
    /**
     * Двухмерный массив.
     */
    private final int[][] data;
    /**
     * Указатель итератора.
     */
    private int row = 0;
    /**
     * Указатель итератора.
     */
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Метод hasNext проверяет, если ли следующий элемент.
     *
     * @return - true если следующий элемент существует.
     */
    @Override
    public boolean hasNext() {
        var rowLength = row < data.length;
        if (rowLength && (column == data[row].length || data[row].length == 0)) {
            row++;
            column = 0;
            return hasNext();
        }
        return rowLength && column < data[row].length;
    }

    /**
     * Метод next возвращает следующий элемент массива.
     *
     * @return - следующий элемент массива.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}