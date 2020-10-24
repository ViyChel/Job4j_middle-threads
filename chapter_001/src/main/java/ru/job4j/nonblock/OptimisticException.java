package ru.job4j.nonblock;

/**
 * Class OptimisticException.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 21.10.2020
 */
public class OptimisticException extends RuntimeException {
    /**
     * Instantiates a new Optimistic exception.
     *
     * @param message the message
     */
    public OptimisticException(String message) {
        super(message);
    }
}
