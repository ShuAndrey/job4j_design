package ru.job4j.collection;


import java.util.NoSuchElementException;

/**
 * Контейнер Stack на базе связанного списка.
 *
 * @author Andrey Shulgin
 */
public class SimpleStack<T> {

    /**
     * Связанный список.
     */
    private final ForwardLinked<T> linked = new ForwardLinked<>();

    /**
     * Метод удаляет первый элемент списка.
     * Если список пуст метод генерирует исключение
     * {@link NoSuchElementException}
     *
     * @return - удаленный элемент.
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * Метод добавляет элемент в начало списка.
     *
     * @param value - элемент.
     */
    public void push(T value) {
        linked.addFirst(value);
    }
}