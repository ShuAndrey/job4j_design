package ru.job4j.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Демонстрация работы Log4j.
 *
 * @author Andrey Shulgin
 */
public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte age = 30;
        short weight = 97;
        int height = 185;
        long count = 8800;
        char gender = 'm';
        boolean studies = true;
        float flt = 1.55f;
        double dbl = 1.85d;
        LOG.debug("Info weight : {}, age : {}, height : {}, count : {},"
                        + " gender : {}, studies : {}, flt : {}, dbl : {}",
                weight, age, height, count, gender, studies, flt, dbl);
    }
}