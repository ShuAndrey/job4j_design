package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

/**
 * Универсальное хранилище.
 *
 * @author Andrey Shulgin
 */
public final class MemStore<T extends Base> implements Store<T> {

    /**
     * Хранилище.
     */
    private final Map<String, T> storage = new HashMap<>();

    /**
     * Добавляет элемент в хранилище.
     *
     * @param model - элемент.
     */
    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    /**
     * Заменяет элемент по id.
     *
     * @param id    - ключ.
     * @param model - элемент.
     * @return - true если замена успешна.
     */
    @Override
    public boolean replace(String id, T model) {
        boolean rst = false;
        if (storage.containsKey(id)) {
            storage.put(id, model);
            rst = true;
        }
        return rst;
    }

    /**
     * Удаляет элемент по id.
     *
     * @param id - ключ.
     * @return - true если удаление успешно.
     */
    @Override
    public boolean delete(String id) {
        boolean rst = false;
        if (storage.containsKey(id)) {
            storage.remove(id);
            return true;
        }
        return rst;
    }

    /**
     * Поиск элемента по id.
     *
     * @param id - ключ.
     * @return - элемент.
     */
    @Override
    public T findById(String id) {
        T rst = null;
        if (storage.containsKey(id)) {
            rst = storage.get(id);
        }
        return rst;
    }
}
