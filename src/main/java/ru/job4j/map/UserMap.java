package ru.job4j.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Карта HashMap.
 *
 * @author Andrey Shulgin
 */
public class UserMap {
    /**
     * Метод main.
     *
     * @param args - args.
     */
    public static void main(String[] args) {
        User first = new User("Andrey", 0);
        System.out.println(first.hashCode());
        User second = new User("Andrey", 0);
        System.out.println(second.hashCode());
        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());
        for (User user : map.keySet()) {
            System.out.println("Key: " + user + " Value: " + map.get(user));
        }
    }
}
