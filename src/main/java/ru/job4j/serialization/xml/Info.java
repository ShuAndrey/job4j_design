package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Модель данных.
 *
 * @author Andrey Shulgin
 */
@XmlRootElement(name = "info")
public class Info {
    @XmlAttribute
    private String name = "Alex";
    @XmlAttribute
    private int age = 18;

    public Info() {

    }

    public Info(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Info{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }
}
