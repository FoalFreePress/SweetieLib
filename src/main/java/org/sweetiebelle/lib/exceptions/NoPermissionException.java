package org.sweetiebelle.lib.exceptions;

public class NoPermissionException extends Exception {

    private static final long serialVersionUID = 4127174553063985458L;

    public NoPermissionException() {
    }

    public NoPermissionException(String message) {
        super(message);
    }

    public NoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPermissionException(Throwable cause) {
        super(cause);
    }
}
