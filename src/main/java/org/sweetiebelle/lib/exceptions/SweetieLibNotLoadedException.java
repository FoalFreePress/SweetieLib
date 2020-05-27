package org.sweetiebelle.lib.exceptions;

public class SweetieLibNotLoadedException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -1764668877959505649L;

    public SweetieLibNotLoadedException() {
    }

    public SweetieLibNotLoadedException(String message) {
        super(message);
    }

    public SweetieLibNotLoadedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SweetieLibNotLoadedException(Throwable cause) {
        super(cause);
    }
}
