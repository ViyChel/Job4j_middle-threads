package ru.job4j.sync;

import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;


/**
 * Class UserStorageTest.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 14.10.2020
 */
public class UserStorageTest {

    @Test
    public void whenAdd() {
        UserStorage storage = new UserStorage();
        User user = new User(1, 100);
        storage.add(user);
        User expected = storage.findById(user.getId());
        assertThat(expected, is(user));
    }

    @Test
    public void whenDelete() {
        UserStorage storage = new UserStorage();
        User user = new User(1, 100);
        storage.add(user);
        storage.delete(user);
        User expected = storage.findById(user.getId());
        assertThat(expected, is(nullValue()));
    }

    @Test
    public void whenAddSameUsers() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(1, 100);
        assertTrue(storage.add(user1));
        assertFalse(storage.add(user2));
    }

    @Test
    public void whenTransferBetweenUsersIsTrue() {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 200));
        assertTrue(storage.transfer(2, 1, 150));
    }

    @Test
    public void whenTransferBetweenUsersIsFalse() {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 200));
        assertFalse(storage.transfer(2, 1, 220));
    }

    @Test
    public void whenTransferBetweenUsersFiftyAmount() {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 200));
        storage.transfer(2, 1, 50);
        assertThat(storage.findById(1).getAmount(), is(150));
        assertThat(storage.findById(2).getAmount(), is(150));
    }
}