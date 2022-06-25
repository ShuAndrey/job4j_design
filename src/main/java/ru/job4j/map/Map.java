package ru.job4j.map;

/**
 * Интерфейс.
 *
 * @author Andrey Shulgin
 */
public interface Map<K, V> extends Iterable<K> {
    boolean put(K key, V value);

    V get(K key);

    boolean remove(K key);
}