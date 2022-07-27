package ru.job4j.serialization.json;

/**
 * Модель данных.
 *
 * @author Andrey Shulgin
 */
public class Info {
    private final String name;
    private final int age;

    public Info(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Info{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }
}
