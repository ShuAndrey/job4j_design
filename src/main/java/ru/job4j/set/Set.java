package ru.job4j.set;

/**
 * Интерфейс.
 *
 * @author Andrey Shulgin
 */
public interface Set<T> extends Iterable<T> {
    boolean add(T value);

    boolean contains(T value);
}