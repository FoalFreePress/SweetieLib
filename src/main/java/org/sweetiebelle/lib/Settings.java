package org.sweetiebelle.lib;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Our settings. Lots of useful database information is stored here.
 *
 * @author sweetie
 */
public class Settings {

    /**
     * Our private config file.
     */
    private FileConfiguration config;
    /**
     * The database name.
     * <p>
     * Also known as the schema name.
     * </p>
     */
    private String dbDatabase;
    /**
     * The hostname to connect to the database.
     */
    private String dbHost;
    /**
     * The password to connect to the database.
     *
     * @see #dbUser
     */
    private String dbPass;
    /**
     * The port to connect to the database.
     */
    private String dbPort;
    /**
     * The username to connect to the databse.
     */
    private String dbUser;
    /**
     * The plugin instance.
     */
    private final SweetieLib plugin;
    /**
     * Should all queries be saved?
     */
    private boolean showQuery;

    /**
     * Default constructor to initialize our class.
     * <p>
     * Only we should initialize this class
     * </p>
     *
     * @param plugin
     *            our plugin
     */
    Settings(final SweetieLib plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
        readSettings();
    }

    /**
     * Gets the database name.
     * <p>
     * Also known as the schema name.
     * </p>
     *
     * @return the database name
     */
    public String getDbDatabase() {
        return dbDatabase;
    }

    /**
     * Gets the database hostname
     *
     * @return the database hostname
     */
    public String getDbHost() {
        return dbHost;
    }

    /**
     * Gets the database password for our username
     *
     * @return the database password
     * @see #getDbUser()
     */
    public String getDbPass() {
        return dbPass;
    }

    /**
     * Gets the database port.
     *
     * @return the database port
     */
    public String getDbPort() {
        return dbPort;
    }

    /**
     * Gets the username to connect to the database.
     *
     * @return the username to connect to the database
     */
    public String getDbUser() {
        return dbUser;
    }

    /**
     * Should all queries be showed?
     *
     * @return should the queries be showed
     */
    public boolean isShowQuery() {
        return showQuery;
    }

    /**
     * Reads settings
     *
     */
    void readSettings() {
        showQuery = config.getBoolean("general.showquery");
        dbHost = config.getString("database.host");
        dbPort = config.getString("database.port");
        dbUser = config.getString("database.username");
        dbPass = config.getString("database.password");
        dbDatabase = config.getString("database.database");
    }

    /**
     * Reloads settings
     */
    void reloadSettings() {
        plugin.reloadConfig();
        config = plugin.getConfig();
        readSettings();
    }
}