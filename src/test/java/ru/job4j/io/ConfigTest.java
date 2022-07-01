package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithCommentAndNullLines() {
        String path = "./data/pair_with_comment_and_null_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithViolations() {
        String path = "./data/pair_with_violations.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenPairWithSeveralEqualities() {
        String path = "./data/pair_with_several_equalities.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("=Petr Arsentev="));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }
}