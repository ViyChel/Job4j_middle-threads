package ru.job4j.nonblock;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class Base.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 21.10.2020
 */
public class Base {
    private final int id;
    private final AtomicInteger version = new AtomicInteger();
    private String name;

    /**
     * Instantiates a new Base.
     *
     * @param id   the id
     * @param name the name
     */
    public Base(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public int getVersion() {
        return this.version.get();
    }

    /**
     * Sets version.
     */
    public void setVersion() {
        this.version.getAndIncrement();
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id
                && Objects.equals(version, base.version)
                && Objects.equals(name, base.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, name);
    }

    @Override
    public String toString() {
        return "Base{"
                + "id=" + id
                + ", version=" + version
                + ", name='" + name + '\''
                + '}';
    }
}
