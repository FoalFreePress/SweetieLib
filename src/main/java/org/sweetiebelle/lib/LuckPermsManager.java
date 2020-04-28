package org.sweetiebelle.lib;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.context.ContextManager;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import net.luckperms.api.query.QueryOptions;

public class LuckPermsManager {

    private LuckPerms provider;

    LuckPermsManager() throws ClassNotFoundException {
        provider = (LuckPerms) Bukkit.getServicesManager().getRegistration(Class.forName("net.luckperms.api.LuckPerms")).getProvider();
    }

    public String getCompletePlayerPrefix(UUID player) {
        return getPlayerPrefix(player) + getGroupPrefix(player);
    }

    public String getCompletePlayerSuffix(UUID player) {
        return getPlayerSuffix(player) + getGroupSuffix(player);
    }

    public String getGroupPrefix(UUID player) {
        String prefix = "";
        for (int i = 5; i > 0; i--) {
            prefix = this.getPlayerPrefix(getMetaData(player), i);
            if (prefix != "")
                return ChatColor.translateAlternateColorCodes('&', prefix);
        }
        return ChatColor.translateAlternateColorCodes('&', prefix);
    }

    public String getGroupSuffix(UUID player) {
        String suffix = "";
        for (int i = 5; i > 0; i--) {
            suffix = this.getPlayerSuffix(getMetaData(player), i);
            if (suffix != "")
                return ChatColor.translateAlternateColorCodes('&', suffix);
        }
        return ChatColor.translateAlternateColorCodes('&', suffix);
    }

    public String getPlayerPrefix(UUID player) {
        return this.getPlayerPrefix(getMetaData(player), 6);
    }

    public String getPlayerSuffix(UUID player) {
        return this.getPlayerSuffix(getMetaData(player), 6);
    }

    public String getPrimaryGroup(UUID player) {
        User user = provider.getUserManager().getUser(player);
        if (user == null)
            try {
                user = provider.getUserManager().loadUser(player).get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        return user.getPrimaryGroup();
    }

    private CachedMetaData getMetaData(UUID playerUUID) {
        UserManager userManager = provider.getUserManager();
        User user = userManager.getUser(playerUUID);
        if (user == null)
            try {
                user = userManager.loadUser(playerUUID).get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        ContextManager contextManager = provider.getContextManager();
        return user.getCachedData().getMetaData(QueryOptions.contextual(contextManager.getContext(user).orElseGet(contextManager::getStaticContext)));
    }

    private String getPlayerPrefix(CachedMetaData data, int priority) {
        String prefix = data.getPrefixes().get(priority);
        return prefix != null ? prefix : "";
    }

    private String getPlayerSuffix(CachedMetaData data, int priority) {
        String suffix = data.getSuffixes().get(priority);
        return suffix != null ? suffix : "";
    }
}
