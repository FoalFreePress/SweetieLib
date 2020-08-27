package org.sweetiebelle.lib.exceptions;

/**
 * Thrown when SweetieLib isn't loaded. This class extends RuntimeException,
 * since programmers who call <code>SweetieLib#getPlugin()</code> early get this
 * exception.
 *
 * @author sweetie
 * @see org.sweetiebelle.lib.SweetieLib#getPlugin()
 */
public class SweetieLibNotLoadedException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -1764668877959505649L;

    /**
     * Constructs a new SweetieLibNotLoadedException with {@code null} as its detail
     * message. The cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     */
    public SweetieLibNotLoadedException() {
    }

    /**
     * Constructs a new SweetieLibNotLoadedException with the specified detail
     * message. The cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the {@link #getMessage()} method.
     */
    public SweetieLibNotLoadedException(String message) {
        super(message);
    }

    /**
     * Constructs a new SweetieLibNotLoadedException with the specified detail
     * message and cause.
     * <p>
     * Note that the detail message associated with {@code cause} is <i>not</i>
     * automatically incorporated in this SweetieLibNotLoadedException's detail
     * message.
     *
     * @param message the detail message (which is saved for later retrieval by the
     *                {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method). (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public SweetieLibNotLoadedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new SweetieLibNotLoadedException with the specified cause and a
     * detail message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>). This
     * constructor is useful for runtime exceptions that are little more than
     * wrappers for other throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method). (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.4
     */
    public SweetieLibNotLoadedException(Throwable cause) {
        super(cause);
    }
}
