package org.sweetiebelle.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.logging.Logger;

public class ConnectionManager {

    private Connection connection;
    private String connectString;
    private Logger logger;
    private Settings settings;

    ConnectionManager(SweetieLib plugin, Settings s) throws SQLException {
        logger = plugin.getLogger();
        settings = s;
        connectString = new String("jdbc:mysql://" + settings.dbHost + ":" + settings.dbPort + "/" + settings.dbDatabase + "?autoReconnect=true&useSSL=false");
        getConnection();
    }

    /**
     * Executes the given SQL statement, which returns a single <code>ResultSet</code> object.
     * <p>
     * <strong>Note:</strong>This method cannot be called on a <code>PreparedStatement</code> or <code>CallableStatement</code>.
     *
     * @param sql
     *            an SQL statement to be sent to the database, typically a static SQL <code>SELECT</code> statement
     * @return a <code>ResultSet</code> object that contains the data produced by the given query; never <code>null</code>
     * @exception SQLException
     *                if a database access error occurs, this method is called on a closed <code>Statement</code>, the given SQL statement produces anything other than a single <code>ResultSet</code> object, the method is called on a <code>PreparedStatement</code> or <code>CallableStatement</code>
     * @throws SQLTimeoutException
     *             when the driver has determined that the timeout value that was specified by the {@code setQueryTimeout} method has been exceeded and has at least attempted to cancel the currently running {@code Statement}
     */
    public ResultSet executeQuery(String query) throws SQLException {
        if (settings.showQuery)
            logger.info(query);
        getConnection();
        return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query);
    }

    /**
     * Executes the given SQL statement, which may be an <code>INSERT</code>, <code>UPDATE</code>, or <code>DELETE</code> statement or an SQL statement that returns nothing, such as an SQL DDL statement.
     * <p>
     * <strong>Note:</strong>This method cannot be called on a <code>PreparedStatement</code> or <code>CallableStatement</code>.
     *
     * @param sql
     *            an SQL Data Manipulation Language (DML) statement, such as <code>INSERT</code>, <code>UPDATE</code> or <code>DELETE</code>; or an SQL statement that returns nothing, such as a DDL statement.
     *
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
     *
     * @exception SQLException
     *                if a database access error occurs, this method is called on a closed <code>Statement</code>, the given SQL statement produces a <code>ResultSet</code> object, the method is called on a <code>PreparedStatement</code> or <code>CallableStatement</code>
     * @throws SQLTimeoutException
     *             when the driver has determined that the timeout value that was specified by the {@code setQueryTimeout} method has been exceeded and has at least attempted to cancel the currently running {@code Statement}
     */
    public int executeUpdate(String update) throws SQLException {
        if (settings.showQuery)
            logger.info(update);
        getConnection();
        return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate(update);
    }

    /**
     * Creates a <code>PreparedStatement</code> object for sending parameterized SQL statements to the database.
     * <P>
     * A SQL statement with or without IN parameters can be pre-compiled and stored in a <code>PreparedStatement</code> object. This object can then be used to efficiently execute this statement multiple times.
     *
     * <P>
     * <B>Note:</B> This method is optimized for handling parametric SQL statements that benefit from precompilation. If the driver supports precompilation, the method <code>prepareStatement</code> will send the statement to the database for precompilation. Some drivers may not support precompilation. In this case, the statement may not be sent to the database until the <code>PreparedStatement</code> object is executed. This has no direct effect on users; however, it does affect which methods throw certain <code>SQLException</code> objects.
     * <P>
     * Result sets created using the returned <code>PreparedStatement</code> object will by default be type <code>TYPE_FORWARD_ONLY</code> and have a concurrency level of <code>CONCUR_READ_ONLY</code>. The holdability of the created result sets can be determined by calling {@link #getHoldability}.
     *
     * @param sql
     *            an SQL statement that may contain one or more '?' IN parameter placeholders
     * @return a new default <code>PreparedStatement</code> object containing the pre-compiled SQL statement
     * @exception SQLException
     *                if a database access error occurs or this method is called on a closed connection
     */
    public PreparedStatement getStatement(String statement) throws SQLException {
        if (settings.showQuery)
            getConnection();
        return connection.prepareStatement(statement);
    }

    void closeConnection() throws SQLException {
        connection.close();
    }

    private void getConnection() throws SQLException {
        if (connection == null || connection.isClosed() || !connection.isValid(0)) {
            logger.info("Connecting to " + settings.dbUser + "@" + connectString + "...");
            connection = DriverManager.getConnection(connectString, settings.dbUser, settings.dbPass);
        }
    }
}
