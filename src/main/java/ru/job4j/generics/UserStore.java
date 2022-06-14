package ru.job4j.generics;

/**
 * Хранилище пользователей.
 *
 * @author Andrey Shulgin
 */
public class UserStore implements Store<User> {
    /**
     * Хранилище.
     */
    private final Store<User> store = new MemStore<>();

    /**
     * Добавляет пользователя в хранилище.
     *
     * @param model - пользователь.
     */
    @Override
    public void add(User model) {
        store.add(model);
    }

    /**
     * Заменяет пользователя по id.
     *
     * @param id    - ключ.
     * @param model - пользователь.
     * @return - true если замена успешна.
     */
    @Override
    public boolean replace(String id, User model) {
        return store.replace(id, model);
    }

    /**
     * Удаляет пользователя по id.
     *
     * @param id - ключ.
     * @return - true если удаление успешно.
     */
    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    /**
     * Поиск пользователя по id.
     *
     * @param id - ключ.
     * @return - пользователь.
     */
    @Override
    public User findById(String id) {
        return store.findById(id);
    }
}