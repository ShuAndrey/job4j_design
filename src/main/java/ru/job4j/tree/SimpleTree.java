package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

/**
 * Дерево, аналог Tree.
 *
 * @author Andrey Shulgin
 */
public class SimpleTree<E> implements Tree<E> {
    /**
     * Корень дерева.
     */
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод добавляет значение в дерево.
     *
     * @param parent значение оторое будет считаться родителем.
     * @param child  значене для добавления.
     * @return true если значение было добавлено в дерево.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> nodeP = findBy(parent);
        Optional<Node<E>> nodeCh = findBy(child);
        if (nodeP.isPresent() && nodeCh.isEmpty()) {
            nodeP.get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    /**
     * Поиск значений в дереве.
     * Применяется алгоритм поиска в ширину.
     *
     * @param value значение для поиска.
     * @return true если найден.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
