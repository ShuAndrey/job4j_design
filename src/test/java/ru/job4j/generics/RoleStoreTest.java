package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class RoleStoreTest {
    @Test
    public void whenAddAndFindThenRoleIsMage() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Mage"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleIsMage() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        store.add(new Role("1", "Warrior"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Mage"));
    }

    @Test
    public void whenReplaceThenRoleIsWarrior() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        store.replace("1", new Role("1", "Warrior"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Warrior"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        store.replace("10", new Role("10", "Warrior"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Mage"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleIsMage() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Mage"));
    }
}