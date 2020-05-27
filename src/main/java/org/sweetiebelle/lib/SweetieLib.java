package org.sweetiebelle.lib;

import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.sweetiebelle.lib.connection.ConnectionManager;
import org.sweetiebelle.lib.connection.ConnectionSQL;
import org.sweetiebelle.lib.exceptions.NoConnectionException;
import org.sweetiebelle.lib.exceptions.NoPermissionException;
import org.sweetiebelle.lib.exceptions.SweetieLibNotLoadedException;
import org.sweetiebelle.lib.permission.PermissionLuckPerms;
import org.sweetiebelle.lib.permission.PermissionManager;

public class SweetieLib extends JavaPlugin {

    public final static UUID CONSOLE_UUID = new UUID(0, 0);
    public final static String NO_PERMISSION = ChatColor.RED + "You do not have permission to use this command.";
    private static SweetieLib plugin;
    private NoConnectionException connectionException;
    private ConnectionSQL connectionManager;
    private NoPermissionException luckException;
    private PermissionManager permissionManager;
    private Settings s;
    
    public static SweetieLib getPlugin() throws SweetieLibNotLoadedException {
        if(plugin == null)
            throw new SweetieLibNotLoadedException();
        return plugin;
    }
    /**
     * 20 ticks in a second, 60 seconds in a minute, 60 minutes per hour. So every hour
     */
    private final long tickDelay = 20 * 60 * 60;

    @Override
    public void onDisable() {
        try {
            connectionManager.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onEnable() {
        s = new Settings(this);
        try {
            connectionManager = new ConnectionSQL(this, s);
        } catch (Exception e) {
            connectionException = new NoConnectionException(e);
            connectionManager = null;
        }
        try {
            permissionManager = new PermissionLuckPerms();
        } catch (Exception e) {
            luckException = new NoPermissionException(e);
            permissionManager = null;
        }
        if (connectionManager != null)
            Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new KeepAliveTask(connectionManager), 0, tickDelay);
        plugin = this;
    }

    public PermissionManager getPermission() throws NoPermissionException {
        if (permissionManager == null) {
            throw luckException;
        }
        return permissionManager;
    }

    public ConnectionManager getConnection() throws NoConnectionException {
        if (connectionManager == null) {
            if (connectionException == null)
                throw new SweetieLibNotLoadedException();
            throw connectionException;
        }
        return connectionManager;
    }
}
