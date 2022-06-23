package net.shonx.lib.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import net.shonx.lib.Settings;
import net.shonx.lib.SweetieLib;

/**
 * Class for SQL connection
 *
 * @author sweetie
 *
 */
public class ConnectionSQL implements ConnectionManager {

    /**
     * The single connection that everyone uses.
     */
    private Connection connection;
    /**
     * The connection string that connects to the database.
     */
    private String connectString;
    /**
     * Logger from SweetieLib
     */
    private Logger logger;
    /**
     * SweetieLib's settings
     */
    private Settings settings;

    public ConnectionSQL(SweetieLib plugin, Settings s) throws SQLException {
        logger = plugin.getLogger();
        settings = s;
        connectString = new String("jdbc:mysql://" + settings.getDbHost() + ":" + settings.getDbPort() + "/" + settings.getDbDatabase() + "?" + settings.getdbExtraSettings());
        getConnection();
    }

    /**
     * Closes this connection
     *
     * @throws IOException if a database access error occurs
     */
    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSet executeQuery(String query) throws SQLException {
        if (settings.isShowQuery())
            logger.info(query);
        getConnection();
        return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int executeUpdate(String update) throws SQLException {
        if (settings.isShowQuery())
            logger.info(update);
        getConnection();
        return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate(update);
    }

    /**
     * Private method for refreshing our connection if it is necessary.
     *
     * @throws SQLException if we cannot reconnect to the database for whatever
     *                      reason.
     */
    private void getConnection() throws SQLException {
        if (connection == null || connection.isClosed() || !connection.isValid(0)) {
            logger.info("Connecting to " + settings.getDbUser() + "@" + connectString + "...");
            connection = DriverManager.getConnection(connectString, settings.getDbUser(), settings.getDbPass());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PreparedStatement getStatement(String statement) throws SQLException {
        if (settings.isShowQuery())
            getConnection();
        return connection.prepareStatement(statement);
    }
}
