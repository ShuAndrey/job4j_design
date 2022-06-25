package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class SimpleMapTest {
    @Test
    public void whenAddNonNull() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Andrey", 30);
        assertThat(map.get("Andrey"), is(30));
    }

    @Test
    public void whenRemove() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Andrey", 30);
        assertTrue(map.remove("Andrey"));
        assertFalse(map.remove("Alex"));
    }

    @Test
    public void whenAddNull() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put(null, 0);
        int i = map.get(null);
        assertThat(i, is(0));
    }

    @Test
    public void whenAddDuplicateKey() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Andrey", 30);
        assertFalse(map.put("Andrey", 25));
    }

    @Test
    public void whenGetThenNotFound() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Andrey", 30);
        assertNull(map.get("Alex"));
    }

    @Test
    public void whenCheckIterator() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Andrey", 30);
        Iterator<String> it = map.iterator();
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertThat(it.next(), is("Andrey"));
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyMapThenNextThrowException() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        Iterator<String> it = map.iterator();
        map.put("Andrey", 30);
        it.next();
    }
}