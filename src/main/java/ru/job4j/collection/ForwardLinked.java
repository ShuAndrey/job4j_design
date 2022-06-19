package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Связанный список.
 *
 * @author Andrey Shulgin
 */
public class ForwardLinked<T> implements Iterable<T> {
    /**
     * Первый элемент списка.
     */
    private Node<T> head;

    /**
     * Метод добавляет элемент в конец списка.
     *
     * @param value - элемент.
     */
    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * Метод добавляет элемент в начало списка.
     *
     * @param value - элемент.
     */
    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    /**
     * Метод удаляет первый элемент списка.
     * Если список пуст метод генерирует исключение
     * {@link NoSuchElementException}
     *
     * @return - удаленный элемент.
     */
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T result = head.value;
        Node<T> node = head.next;
        head.value = null;
        head.next = null;
        head = node;
        return result;
    }

    /**
     * Итератор связанного списка.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            /**
             * Узел связанного списка.
             */
            Node<T> node = head;

            /**
             * Метод hasNext проверяет, если ли следующий элемент.
             *
             * @return - true если следующий элемент существует.
             */
            @Override
            public boolean hasNext() {
                return node != null;
            }

            /**
             * Метод next возвращает следующий элемент списка.
             * Если следующего элемента нет генерирует исключение
             * {@link NoSuchElementException}.
             * @return - следующий элемент списка.
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     * Узел списка.
     */
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}