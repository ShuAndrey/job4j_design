package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

/**
 * Модель данных.
 *
 * @author Andrey Shulgin
 */
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    @XmlAttribute
    private int year;
    @XmlAttribute
    private String group;
    @XmlAttribute
    private boolean headman;
    private Info info;
    @XmlElementWrapper(name = "grades")
    @XmlElement(name = "grade")
    private int[] grades;

    public Student() {

    }

    public Student(int year, String group, boolean headman, Info info, int... grades) {
        this.year = year;
        this.group = group;
        this.headman = headman;
        this.info = info;
        this.grades = grades;
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
}
