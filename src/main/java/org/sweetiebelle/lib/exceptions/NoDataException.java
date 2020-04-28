package org.sweetiebelle.lib.exceptions;

/**
 * Throw to indicate either no {@link Account} was found, or if something is null when it shouldn't be.
 *
 */
public class NoDataException extends Exception {

    private static final long serialVersionUID = 3422799336114476332L;

    public NoDataException() {
        super();
    }

    public NoDataException(String message) {
        super(message);
    }

    public NoDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDataException(Throwable cause) {
        super(cause);
    }
}
