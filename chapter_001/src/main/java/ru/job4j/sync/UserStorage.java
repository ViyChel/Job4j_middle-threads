package ru.job4j.sync;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Class UserStorage.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 10.10.2020
 */

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        boolean result = false;
        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    public synchronized boolean update(User user) {
        boolean result = false;
        if (users.containsKey(user.getId())) {
            users.get(user.getId()).setAmount(user.getAmount());
            result = true;
        }
        return result;
    }

    public synchronized boolean delete(User user) {
        boolean result = false;
        if (users.containsKey(user.getId())) {
            users.remove(user.getId());
            result = true;
        }
        return result;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User userFrom = findById(fromId);
        User userTo = findById(toId);
        if (userFrom != null && userTo != null && userFrom.getAmount() >= amount) {
            userFrom.setAmount(userFrom.getAmount() - amount);
            userTo.setAmount(userTo.getAmount() + amount);
            result = true;
        }
        return result;
    }

    public synchronized User findById(int id) {
        return users.get(id);
    }
}
