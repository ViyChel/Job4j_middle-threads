package ru.job4j.pool;

import ru.job4j.pool.models.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class EmailNotification.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 26.10.2020
 */
public class EmailNotification {
    /**
     * The Pool.
     */
    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Email to.
     *
     * @param user the user
     */
    public void emailTo(User user) {
        this.pool.submit(
                () -> {
                    String subject = String.format("Notification {%s} to email {%s}", user.getName(), user.getEmail());
                    String body = String.format("Add a new event to {%s}", user.getName());
                    send(subject, body, user.getEmail());
                }
        );
    }

    /**
     * Close.
     */
    public void close() {
        this.pool.shutdown();
    }

    /**
     * Send.
     *
     * @param subject the subject
     * @param body    the body
     * @param email   the email
     */
    public void send(String subject, String body, String email) {

    }
}
