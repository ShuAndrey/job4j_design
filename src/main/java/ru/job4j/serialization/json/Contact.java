package ru.job4j.serialization.json;

/**
 * Модель данных.
 *
 * @author Andrey Shulgin
 */
public class Contact {
    private final String phone;

    public Contact(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }
}