package ru.job4j.map;

import java.util.Calendar;

/**
 * Модель данных.
 *
 * @author Andrey Shulgin
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
