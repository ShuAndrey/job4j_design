package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Карта на базе массива, аналог HashMap, но без реализации коллизий.
 *
 * @author Andrey Shulgin
 */
public class SimpleMap<K, V> implements Map<K, V> {
    /**
     * Константа для проверки заполненности массива.
     */
    private static final float LOAD_FACTOR = 0.75f;
    /**
     * Размер массива.
     */
    private int capacity = 8;
    /**
     * Количество элементов в массиве.
     */
    private int count = 0;
    /**
     * Счетчик операций, которые структурно
     * модифицируют (добавляют и удаляют элементы) коллекцию.
     */
    private int modCount = 0;
    /**
     * Массив элементов (контейнер).
     */
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * Метод добавляет значение в коллекци.
     * При заполнении контейнера table на 75% увеличивает размер table в 2 раза.
     * Если после присвоения индекса в массиве уже есть значение, то возвращает false.
     *
     * @param key   - ключ.
     * @param value - значение.
     * @return - true если значение добавлено в коллекцию.
     */
    @Override
    public boolean put(K key, V value) {
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        boolean result = false;
        int keyHashCode;
        if (key == null) {
            keyHashCode = 0;
        } else {
            keyHashCode = key.hashCode();
        }
        int i = hash(keyHashCode);
        int index = indexFor(i);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            result = true;
            count++;
            modCount++;
        }
        return result;
    }

    /**
     * Метод вычисляет hash ключа.
     *
     * @param hashCode - hashcode ключа.
     * @return - значение hash.
     */
    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    /**
     * Метод вычисляет индекс по значению hash.
     *
     * @param hash - hash ключа.
     * @return - индекс.
     */
    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    /**
     * Метод увеличивает массив table в 2 раза.
     */
    private void expand() {
        capacity = capacity * 2;
        count = 0;
        MapEntry<K, V>[] newTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> map : newTable) {
            if (map != null) {
                put(map.key, map.value);
            }
        }
    }

    /**
     * Метод ищет в массиве элемент по ключу и возвращает значение
     * если ключ найден. Если ключа нет, то возвращает null.
     *
     * @param key - ключ.
     * @return - Значение.
     */
    @Override
    public V get(K key) {
        V result = null;
        for (MapEntry<K, V> map : table) {
            if (map != null
                    && Objects.hashCode(map.key) == Objects.hashCode(key)
                    && Objects.equals(key, map.key)) {
                result = map.value;
            }
        }
        return result;
    }

    /**
     * Метод удаляет значение из массива равное key.
     *
     * @param key - ключ.
     * @return - true если элемент был удален.
     */
    @Override
    public boolean remove(K key) {
        boolean result = false;
        for (int index = 0; index < table.length; index++) {
            if (table[index] != null
                    && Objects.hashCode(table[index].key) == Objects.hashCode(key)
                    && Objects.equals(key, table[index].key)) {
                table[index] = null;
                count--;
                modCount++;
                result = true;
            }
        }
        return result;
    }

    /**
     * Итератор массива
     *
     * @return - значение ключа.
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                for (int index = point; index < table.length; index++) {
                    if (table[index] != null) {
                        point = index;
                        result = true;
                        break;
                    }

                }
                return result;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    /**
     * Класс для хранения значений в массиве.
     *
     * @param <K> - ключ.
     * @param <V> - значение.
     */
    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
