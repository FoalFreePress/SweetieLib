package org.sweetiebelle.lib;

import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.sweetiebelle.lib.exceptions.NoConnectionException;
import org.sweetiebelle.lib.exceptions.NoLuckPermsException;
import org.sweetiebelle.lib.exceptions.SweetieLibNotLoadedException;

public class SweetieLib extends JavaPlugin {

    public final static UUID CONSOLE_UUID = UUID.fromString("3c879ef9-95c2-44d1-98f9-2824610477c8");
    public final static String NO_PERMISSION = ChatColor.RED + "You do not have permission to use this command.";
    private static NoConnectionException connectionException;
    private static ConnectionManager connectionManager;
    private static NoLuckPermsException luckException;
    private static LuckPermsManager luckManager;

    public static ConnectionManager getConnection() throws NoConnectionException, SweetieLibNotLoadedException {
        if (connectionManager == null) {
            if (connectionException == null)
                throw new SweetieLibNotLoadedException();
            throw connectionException;
        }
        return connectionManager;
    }

    public static LuckPermsManager getLuckPerms() throws NoLuckPermsException, SweetieLibNotLoadedException {
        if (luckManager == null) {
            if (luckException == null)
                throw new SweetieLibNotLoadedException();
            throw luckException;
        }
        return luckManager;
    }

    private Settings s;
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
            connectionManager = new ConnectionManager(this, s);
        } catch (Exception e) {
            connectionException = new NoConnectionException(e);
            connectionManager = null;
            e.printStackTrace();
        }
        try {
            luckManager = new LuckPermsManager();
        } catch (Exception e) {
            luckException = new NoLuckPermsException(e);
            luckManager = null;
            e.printStackTrace();
        }
        if (connectionManager != null)
            Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new KeepAliveTask(connectionManager), 0, tickDelay);
    }
}
