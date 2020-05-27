package org.sweetiebelle.lib;

import org.bukkit.configuration.file.FileConfiguration;

public class Settings {

    private String dbDatabase;
    private String dbHost;
    private String dbPass;
    private String dbPort;
    private String dbUser;
    private boolean showQuery;
    private FileConfiguration config;
    private final SweetieLib plugin;

    Settings(final SweetieLib plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
        readSettings();
    }

    /**
     * Reads settings
     *
     * @param pConfig
     *            the config
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

    public boolean isShowQuery() {
        return showQuery;
    }

    /**
     * @return the dbDatabase
     */
    public String getDbDatabase() {
        return dbDatabase;
    }

    /**
     * @return the dbHost
     */
    public String getDbHost() {
        return dbHost;
    }

    /**
     * @return the dbPass
     */
    public String getDbPass() {
        return dbPass;
    }

    /**
     * @return the dbPort
     */
    public String getDbPort() {
        return dbPort;
    }

    /**
     * @return the dbUser
     */
    public String getDbUser() {
        return dbUser;
    }
}