package ru.job4j.generics;

/**
 * Модель данных.
 *
 * @author Andrey Shulgin
 */
public class User extends Base {

    private final String username;

    public User(String id, String name) {
        super(id);
        this.username = name;
    }

    public String getUsername() {
        return username;
    }
}