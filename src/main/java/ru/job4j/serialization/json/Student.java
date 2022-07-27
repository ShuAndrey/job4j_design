package ru.job4j.serialization.json;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Модель данных.
 *
 * @author Andrey Shulgin
 */
public class Student {
    private final int year;
    private final String group;
    private final boolean headman;
    private final Info info;
    private final int[] grades;

    public Student(int year, String group, boolean headman, Info info, int... grades) {
        this.year = year;
        this.group = group;
        this.headman = headman;
        this.info = info;
        this.grades = grades;
    }

    /**
     * Преобразование POJO объекта в JSONObject  и json-строку.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        final Student student = new Student(
                3, "4-B", false, new Info("Andrey", 18), 5, 3, 4
        );
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("year", student.getYear());
        jsonObject.put("group", student.getGroup());
        jsonObject.put("headman", student.isHeadman());
        jsonObject.put("info", student.getInfo());
        jsonObject.put("grades", student.getGrades());
        System.out.println(jsonObject);
        System.out.println(new JSONObject(student));
    }

    @Override
    public String toString() {
        return "Student{"
                + "year=" + year
                + ", group='" + group + '\''
                + ", headman=" + headman
                + ", info=" + info
                + ", grades=" + Arrays.toString(grades)
                + '}';
    }

    public int getYear() {
        return year;
    }

    public String getGroup() {
        return group;
    }

    public boolean isHeadman() {
        return headman;
    }

    public Info getInfo() {
        return info;
    }

    public int[] getGrades() {
        return grades;
    }
}
