package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Связанный список, аналог LinkedList.
 *
 * @author Andrey Shulgin
 */
public class SimpleLinkedList<E> implements LinkedList<E> {
    /**
     * Последний элемент.
     */
    private Node<E> last;
    /**
     * Первый элемент.
     */
    private Node<E> first;
    /**
     * Количество элементов.
     */
    private int size;
    /**
     * Счетчик операций, которые структурно
     * модифицируют (добавляют и удаляют элементы) связанный список.
     */
    private int modCount;

    /**
     * Метод добавляет элемент в связанный список.
     *
     * @param value - элемент.
     */
    @Override
    public void add(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Метод возвращает элемент связанного списка по индексу.
     * При индексе не входящем в диапазон индексов списка возникает ошибка
     * {@link  IndexOutOfBoundsException}
     *
     * @param index - индекс.
     * @return - элемент.
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = first;
        for (int i = 1; i <= index; i++) {
            node = first.next;
        }
        return node.item;
    }

    /**
     * Итератор связанного списка.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            /**
             * Переменная для проверки изменения списка.
             */
            private final int expectedModCount = modCount;
            /**
             * Указатель итератора.
             */
            private int point;
            /**
             * Узел связанного списка.
             */
            private Node<E> eNode;

            /**
             * Метод hasNext проверяет, если ли следующий элемент.
             * Если после вызова итератора был изменен размер коллекции
             * генерирует исключение
             * {@link ConcurrentModificationException}
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
             * Метод next возвращает следующий элемент списка.
             * Если следующего элемента нет генерирует исключение
             * {@link NoSuchElementException}.
             * @return - следующий элемент списка.
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (eNode != null) {
                    eNode = eNode.next;
                } else {
                    eNode = first;
                }
                point++;
                return eNode.item;
            }
        };
    }

    /**
     * Узел списка.
     */
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}