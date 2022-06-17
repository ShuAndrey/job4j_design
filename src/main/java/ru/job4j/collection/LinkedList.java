package ru.job4j.collection;

/**
 * Интерфейс.
 *
 * @author Andrey Shulgin
 */
public interface LinkedList<E> extends Iterable<E> {
    void add(E value);

    E get(int index);
}