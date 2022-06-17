package ru.job4j.collection;


/**
 * @author Andrey Shulgin
 */
public class SimpleStack<T> {

    private final ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }
}