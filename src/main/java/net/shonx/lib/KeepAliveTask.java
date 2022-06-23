package net.shonx.lib;

import java.sql.SQLException;

import net.shonx.lib.connection.ConnectionManager;

/**
 * Default class in order to keep our connection alive.
 *
 * @author sweetie
 *
 */
class KeepAliveTask implements Runnable {

    /**
     * Our connection
     */
    private ConnectionManager connection;

    /**
     * Default constructor so only we can initialize this class.
     *
     * @param connection the connection
     */
    KeepAliveTask(ConnectionManager connection) {
        this.connection = connection;
    }

    /**
     * Runs <code>SELECT 1</code> on the connection.
     * <p>
     * If there is an exception, it throws it wrapped in a {@link RuntimeException}
     * </p>
     */
    @Override
    public void run() {
        try {
            connection.executeQuery("SELECT 1");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
