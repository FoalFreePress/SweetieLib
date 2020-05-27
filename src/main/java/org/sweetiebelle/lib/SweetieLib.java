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

/**
 * SweetieLib provides common code and fields for all of my other plugins.
 *
 * @author sweetie
 *
 */
public class SweetieLib extends JavaPlugin {

    /**
     * Static UUID to represent the server Console.
     * <p>
     * This UUID should <i>hopefully</i> never be used by a player.
     * </p>
     * <p>
     * This is the NIL UUID, which is equal to <code>00000000-0000-0000-0000-000000000000</code>
     * </p>
     */
    public final static UUID CONSOLE_UUID = new UUID(0, 0);
    /**
     * Default message to provide to a player that they do not have permission to execute a command.
     */
    public final static String NO_PERMISSION = ChatColor.RED + "You do not have permission to use this command.";
    private static SweetieLib plugin;
    private NoConnectionException connectionException;
    private ConnectionSQL connectionManager;
    private NoPermissionException luckException;
    private PermissionManager permissionManager;
    private Settings s;

    /**
     * Gets a copy of our plugin.
     *
     * @return the SweetieLib instance.
     * @throws SweetieLibNotLoadedException
     *             if SweetieLib isn't actually loaded.
     */
    public static SweetieLib getPlugin() throws SweetieLibNotLoadedException {
        if (plugin == null)
            throw new SweetieLibNotLoadedException();
        return plugin;
    }

    /**
     * The delay in ticks when our {@link KeepAliveTask#run()} task should call its run method
     * <p>
     * Current value is every hour in ticks.
     * </p>
     *
     * @see KeepAliveTask#run()
     */
    private final long tickDelay = 72000;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDisable() {
        try {
            connectionManager.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * Gets a copy of our {@link PermissionManager}
     *
     * @return the current permission manager
     * @throws NoPermissionException
     *             if no Permission Manager is currently loaded.
     * @see PermissionManager
     */
    public PermissionManager getPermission() throws NoPermissionException {
        if (permissionManager == null)
            throw luckException;
        return permissionManager;
    }

    /**
     * Gets a copy of our {@link ConnectionManager}
     *
     * @return the current connection manager
     * @throws NoConnectionException
     *             if no connection manager is currently loaded
     * @see ConnectionManager
     */
    public ConnectionManager getConnection() throws NoConnectionException {
        if (connectionManager == null) {
            if (connectionException == null)
                throw new SweetieLibNotLoadedException();
            throw connectionException;
        }
        return connectionManager;
    }
}
