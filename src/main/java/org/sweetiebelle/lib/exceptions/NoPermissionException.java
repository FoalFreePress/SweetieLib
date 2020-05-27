package org.sweetiebelle.lib.exceptions;
/**
 * This Exception is thrown when there is no <code>PermissionManager</code> present.
 * 
 * @author sweetie
 * @see org.sweetiebelle.lib.permission.PermissionManager
 */
public class NoPermissionException extends Exception {

    private static final long serialVersionUID = 4127174553063985458L;

    /**
     * Constructs a new NoPermissionException with {@code null} as its detail message. The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
     */
    public NoPermissionException() {
    }

    /**
     * Constructs a new NoPermissionException with the specified detail message. The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message
     *            the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     */
    public NoPermissionException(String message) {
        super(message);
    }

    /**
     * Constructs a new NoPermissionException with the specified detail message and cause.
     * <p>
     * Note that the detail message associated with {@code cause} is <i>not</i> automatically incorporated in this exception's detail message.
     *
     * @param message
     *            the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param cause
     *            the cause (which is saved for later retrieval by the {@link #getCause()} method). (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or unknown.)
     * @since 1.4
     */
    public NoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new NoPermissionException with the specified cause and a detail message of <tt>(cause==null ? null : cause.toString())</tt> (which typically contains the class and detail message of <tt>cause</tt>). This constructor is useful for exceptions that are little more than wrappers for other throwables (for example, {@link java.security.PrivilegedActionException}).
     *
     * @param cause
     *            the cause (which is saved for later retrieval by the {@link #getCause()} method). (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or unknown.)
     * @since 1.4
     */
    public NoPermissionException(Throwable cause) {
        super(cause);
    }
}
