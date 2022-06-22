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

    public User(String name, int children) {
        this.name = name;
        this.children = children;
        this.birthday = new Calendar() {
            @Override
            protected void computeTime() {

            }

            @Override
            protected void computeFields() {

            }

            @Override
            public void add(int field, int amount) {

            }

            @Override
            public void roll(int field, boolean up) {

            }

            @Override
            public int getMinimum(int field) {
                return 0;
            }

            @Override
            public int getMaximum(int field) {
                return 0;
            }

            @Override
            public int getGreatestMinimum(int field) {
                return 0;
            }

            @Override
            public int getLeastMaximum(int field) {
                return 0;
            }
        };
    }
}
