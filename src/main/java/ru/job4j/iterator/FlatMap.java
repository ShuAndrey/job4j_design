package ru.job4j.iterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Итератор вложенных итераторов.
 *
 * @author Andrey Shulgin
 */
public class FlatMap<T> implements Iterator<T> {
    /**
     * Итератор итераторов.
     */
    private final Iterator<Iterator<T>> data;
    /**
     * Указатель итератора.
     */
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    /**
     * Метод hasNext проверяет, если ли следующий элемент.
     *
     * @return - true если следующий элемент существует.
     */
    @Override
    public boolean hasNext() {
        if (!cursor.hasNext() && data.hasNext()) {
            cursor = data.next();
            return hasNext();
        }
        return cursor.hasNext();
    }

    /**
     * Метод next возвращает следующий элемент вложенных итераторов.
     *
     * @return - следующий элемент.
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

    /**
     * Демонстрация работы.
     *
     * @param args - args.
     */
    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}