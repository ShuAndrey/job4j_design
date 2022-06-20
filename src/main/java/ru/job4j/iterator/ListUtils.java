package ru.job4j.iterator;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Реализация ListIterator.
 *
 * @author Andrey Shulgin
 */
public class ListUtils {

    /**
     * Метод вставляет значение перед индексом.
     *
     * @param list  - коллекция.
     * @param index - индекс.
     * @param value - значение.
     * @param <T>   - тип значений хранящихся в коллекции.
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     * Метод вставляет значение после индекса.
     *
     * @param list  - коллекция.
     * @param index - индекс.
     * @param value - значение.
     * @param <T>   - тип значений хранящихся в коллекции.
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.next();
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     * Метод удаляет все элементы, которые удовлетворяют предикату.
     *
     * @param list   - коллекция.
     * @param filter - предикат.
     * @param <T>    - тип значений хранящихся в коллекции.
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
            }
        }
    }

    /**
     * Метод заменяет все элементы, которые удовлетворяют предикату.
     *
     * @param list   - коллекция.
     * @param filter - предикат.
     * @param value  - значение.
     * @param <T>    - тип значений хранящихся в коллекции.
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.set(value);
            }
        }
    }

    /**
     * Метод удаляет из списка те элементы, которые есть в elements.
     *
     * @param list     - коллекция.
     * @param elements - коллекция.
     * @param <T>      - тип значений хранящихся в коллекции.
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            T rst = iterator.next();
            for (T elem : elements) {
                if (rst.equals(elem)) {
                    iterator.remove();
                    break;
                }
            }
        }
    }
}

