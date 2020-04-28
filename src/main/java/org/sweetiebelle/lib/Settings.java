package org.sweetiebelle.lib;

import org.bukkit.configuration.file.FileConfiguration;

class Settings {

    String dbDatabase;
    String dbHost;
    String dbPass;
    String dbPort;
    String dbPrefix;
    String dbUser;
    boolean recOnJoin;
    boolean showQuery;
    boolean stackTraces;
    boolean useDebug;
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
        stackTraces = config.getBoolean("general.printStackTraces");
        showQuery = config.getBoolean("general.showquery");
        dbHost = config.getString("database.host");
        dbPort = config.getString("database.port");
        dbUser = config.getString("database.username");
        dbPass = config.getString("database.password");
        dbDatabase = config.getString("database.database");
        dbPrefix = config.getString("database.prefix");
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