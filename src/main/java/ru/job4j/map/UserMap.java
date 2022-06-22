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
        User second = new User("Alex", 1);
        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());
        for (User user : map.keySet()) {
            System.out.println("Key: " + user + " Value: " + map.get(user));
        }
    }
}
