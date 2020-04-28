package org.sweetiebelle.lib.exceptions;

public class NoLuckPermsException extends Exception {

    private static final long serialVersionUID = 4127174553063985458L;

    public NoLuckPermsException() {
    }

    public NoLuckPermsException(String message) {
        super(message);
    }

    public NoLuckPermsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoLuckPermsException(Throwable cause) {
        super(cause);
    }
}
