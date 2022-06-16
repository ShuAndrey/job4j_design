package ru.job4j.collection;

import java.util.*;

/**
 * Динамический массив, аналог ArrayList.
 *
 * @author Andrey Shulgin
 */
public class SimpleArrayList<T> implements SimpleList<T> {

    /**
     * Массив элементов (контейнер коллекции).
     */
    private T[] container;
    /**
     * Количество элементов в коллекции.
     */
    private int size;
    /**
     * Счетчик операций, которые структурно
     * модифицируют (добавляют и удаляют элементы) коллекцию.
     */
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**
     * Метод добавляет элемент коллекцию.
     * Предусмотрено увеличение массива перед добавлением элемента,
     * если массив полностью заполнен.
     *
     * @param value - элемент.
     */
    @Override
    public void add(T value) {
        checkSize();
        container[size++] = value;
        modCount++;
    }

    /**
     * Метод заменяет элемент в коллекции по индексу.
     * При индексе не входящем в диапазон индексов коллекции возникает ошибка
     * {@link  IndexOutOfBoundsException}
     *
     * @param index    - индекс.
     * @param newValue - новое значение.
     * @return - старое значение.
     */
    @Override
    public T set(int index, T newValue) {
        T result = get(index);
        container[index] = newValue;
        return result;
    }

    /**
     * Метод удаляет элемент коллекции по индексу.
     * При индексе не входящем в диапазон индексов коллекции возникает ошибка
     * {@link  IndexOutOfBoundsException}
     *
     * @param index - индекс.
     * @return - удаленный элемент.
     */
    @Override
    public T remove(int index) {
        T result = get(index);
        System.arraycopy(container,
                index + 1,
                container,
                index,
                size - 1 - index);
        size--;
        modCount++;
        return result;
    }

    /**
     * Метод возвращает элемент коллекции по индексу.
     * При индексе не входящем в диапазон индексов коллекции возникает ошибка
     * {@link  IndexOutOfBoundsException}
     *
     * @param index - индекс.
     * @return - элемент.
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    /**
     * Метод возвращает количество элементов в коллекции.
     *
     * @return - количество элементов в коллекции.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Итератор коллекции.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            /**
             * Переменная для проверки изменения массива.
             */
            private final int expectedModCount = modCount;
            /**
             * Указатель итератора.
             */
            private int point = 0;

            /**
             * Метод hasNext проверяет, если ли следующий элемент.
             * Если после вызова итератора был изменен размер коллекции
             * генерирует исключение {@link ConcurrentModificationException}.
             * @return - true если следующий элемент существует.
             */
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            /**
             * Метод next возвращает следующий элемент коллекции.
             * Если следующего элемента нет генерирует исключение
             * {@link NoSuchElementException}
             * @return - следующий элемент коллекции.
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }

        };
    }

    /**
     * Метод увеличивает массив массив в 2 раза, если он полностью заполнен.
     * Если длинна массива равна 0, то размер массива становится равен 2.
     */
    private void checkSize() {
        int length = container.length != 0 ? container.length * 2 : 2;
        if (size == container.length) {
            container = Arrays.copyOf(container, length);
        }
    }
}