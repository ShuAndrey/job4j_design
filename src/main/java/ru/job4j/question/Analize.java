package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
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
        int added = current.size();
        int changed = 0;
        int deleted = previous.size();
        Map<Integer, String> prevTemp = new HashMap<>();
        for (User prev : previous) {
            prevTemp.put(prev.getId(), prev.getName());
        }
        Map<Integer, String> currTemp = new HashMap<>();
        for (User curr : current) {
            currTemp.put(curr.getId(), curr.getName());
        }
        for (Map.Entry<Integer, String> map : prevTemp.entrySet()) {
            if (currTemp.containsKey(map.getKey()) && !currTemp.get(map.getKey()).equals(map.getValue())) {
                changed++;
                deleted--;
                added--;
            } else if (currTemp.containsValue(map.getValue())) {
                deleted--;
                added--;
            }
        }
        return new Info(added, changed, deleted);
    }
}
