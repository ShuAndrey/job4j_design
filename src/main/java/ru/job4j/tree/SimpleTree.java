package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

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
     * Метод проверяет является ли дерево бинарным.
     *
     * @return true если дерево бинарное.
     */
    public boolean isBinary() {
        return findByPredicate(x -> x.children.size() > 2).isEmpty();
    }

    /**
     * Метод ищет значение в дереве.
     *
     * @param value значение для поиска.
     * @return возвращает Optional значение.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(x -> x.value.equals(value));
    }

    /**
     * Поиск значений в дереве.
     * Применяется алгоритм поиска в ширину.
     *
     * @param condition предикат.
     * @return - возвращает Optional значение.
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
