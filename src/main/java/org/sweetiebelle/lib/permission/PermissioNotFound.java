package org.sweetiebelle.lib.permission;

import java.util.UUID;

import org.bukkit.ChatColor;

public class PermissioNotFound implements PermissionManager {

	@Override
	public String getCompletePlayerPrefix(UUID player) {
		return ChatColor.AQUA + "";
	}

	@Override
	public String getCompletePlayerSuffix(UUID player) {
		return ChatColor.RESET + "";
	}

	@Override
	public String getGroupPrefix(UUID player) {
		return ChatColor.AQUA + "";
	}

	@Override
	public String getGroupSuffix(UUID player) {
		return ChatColor.RESET + "";
	}

	@Override
	public String getPlayerPrefix(UUID player) {
		return ChatColor.AQUA + "";
	}

	@Override
	public String getPlayerSuffix(UUID player) {
		return ChatColor.RESET + "";
	}

	@Override
	public String getPrimaryGroup(UUID player) {
		return "";
	}

}
