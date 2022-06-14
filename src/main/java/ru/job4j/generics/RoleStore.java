package ru.job4j.generics;

/**
 * Хранилище ролей.
 *
 * @author Andrey Shulgin
 */
public class RoleStore implements Store<Role> {
    /**
     * Хранилище.
     */
    private final Store<Role> roleStore = new MemStore<>();

    /**
     * Добавляет роль в хранилище.
     *
     * @param model - роль.
     */
    @Override
    public void add(Role model) {
        roleStore.add(model);
    }

    /**
     * Заменяет роль по id.
     *
     * @param id    - ключ.
     * @param model - роль.
     * @return - true если замена успешна.
     */
    @Override
    public boolean replace(String id, Role model) {
        return roleStore.replace(id, model);
    }

    /**
     * Удаляет роль по id.
     *
     * @param id - ключ.
     * @return - true если удаление успешно.
     */
    @Override
    public boolean delete(String id) {
        return roleStore.delete(id);
    }

    /**
     * Поиск роли по id.
     *
     * @param id - ключ.
     * @return - роль.
     */
    @Override
    public Role findById(String id) {
        return roleStore.findById(id);
    }
}
