package mek.backend.auth.exception;

import java.io.Serial;

/**
 * Exception class named {@link UserNotActivatedException } thrown when the specified user is not found.
 */
public class UserNotActivatedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6226206922525682121L;

    private static final String DEFAULT_MESSAGE = """
            User not found!
            """;

    /**
     * Constructs a new {@link UserNotActivatedException} with the default message.
     */
    public UserNotActivatedException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new {@link UserNotActivatedException} with the default message and an additional message.
     *
     * @param message the additional message to include.
     */
    public UserNotActivatedException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
