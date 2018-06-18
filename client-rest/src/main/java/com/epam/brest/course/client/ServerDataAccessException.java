package com.epam.brest.course.client;

/**
 * Server data access exception.
 */
public class ServerDataAccessException extends RuntimeException {

    /**
     * Constructor.
     * @param message - error message.
     */
    public ServerDataAccessException(final String message) {
        super(message);
    }
}
