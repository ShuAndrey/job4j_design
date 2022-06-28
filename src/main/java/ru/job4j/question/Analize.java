package ru.job4j.question;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс для вычисления количества изменений в множествах.
 *
 * @author Andrey Shulgin
 */
public class Analize {
    /**
     * Метод вычисляет количество изменений в множествах.
     *
     * @param previous множество до изменений.
     * @param current  множество после изменений.
     * @return объект класса Info, который показывает количество изменений.
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        Set<User> prevTemp = new HashSet<>(previous);
        Set<User> currTemp = new HashSet<>(current);
        for (User prev : previous) {
            for (User curr : current) {
                if (prev.getId() == curr.getId() && !prev.getName().equals(curr.getName())) {
                    changed++;
                    prevTemp.remove(prev);
                    currTemp.remove(curr);
                } else if (prev.equals(curr)) {
                    prevTemp.remove(prev);
                    currTemp.remove(curr);
                }
            }
        }
        return new Info(currTemp.size(), changed, prevTemp.size());
    }
}
