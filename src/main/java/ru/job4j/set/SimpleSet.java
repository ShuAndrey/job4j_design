package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

/**
 * Множество Set на базе массива.
 *
 * @author Andrey Shulgin
 */
public class SimpleSet<T> implements Set<T> {

    /**
     * Массив для хранения данных.
     */
    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    /**
     * Метод добавляет значение в массив если оно уникально.
     *
     * @param value - значение.
     * @return - true если значение было добавлено.
     */
    @Override
    public boolean add(T value) {
        boolean result = !contains(value);
        if (result) {
            set.add(value);
        }
        return result;
    }

    /**
     * Метод проверяет наличие значения value в множестве.
     *
     * @param value - значение.
     * @return - true если значение есть в множестве.
     */
    @Override
    public boolean contains(T value) {
        boolean result = false;
        Iterator<T> iterator = iterator();
        for (int index = 0; index < set.size(); index++) {
            if (Objects.equals(iterator.next(), value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Итератор.
     *
     * @return - итератор.
     */
    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}