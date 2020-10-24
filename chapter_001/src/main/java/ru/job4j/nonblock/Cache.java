package ru.job4j.nonblock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class Cache.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 21.10.2020
 */
public class Cache {
    private final Map<Integer, Base> cache = new ConcurrentHashMap<>();

    /**
     * Add.
     *
     * @param model the model
     */
    public void add(Base model) {
        cache.putIfAbsent(model.getId(), model);
    }

    /**
     * Update.
     *
     * @param model the model
     */
    public void update(Base model) {
        cache.computeIfPresent(model.getId(), (k, v) -> {
            if (model.getVersion() != v.getVersion()) {
                throw new OptimisticException("Versions do not match");
            }
            v.setVersion();
            v.setName(model.getName());
            return v;
        });
    }

    /**
     * Delete boolean.
     *
     * @param model the model
     * @return the boolean
     */
    public boolean delete(Base model) {
        return cache.remove(model.getId(), model);
    }

    @Override
    public String toString() {
        return "Cache{"
                + "cache=" + cache
                + '}';
    }
}
