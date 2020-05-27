package org.sweetiebelle.lib.permission;

import java.util.UUID;

/**
 * Interface for all of the Permissions-Chat integrations.
 *
 * @author sweetie
 *
 */
public interface PermissionManager {

    /**
     * Gets the complete group prefix plus player prefix.
     * <p>
     * Equal to calling <code>{@link#getPlayerPrefix(UUID)} + {@link#getGroupPrefix(UUID)}</code>
     * </p>
     *
     * @param player
     *            the player's UUID
     * @return the complete prefix
     */
    String getCompletePlayerPrefix(UUID player);

    /**
     * Gets the complete group suffix plus player suffix.
     * <p>
     * Equal to calling <code>{@link#getPlayerSuffix(UUID)} + {@link#getGrouprSuffix(UUID)}</code>
     * </p>
     *
     * @param player
     *            the player's UUID
     * @return the complete suffix
     */
    String getCompletePlayerSuffix(UUID player);

    /**
     * Returns the group's prefix for the player. Group Prefix weight must be 5 or less or this won't work.
     *
     * @param player
     * @return the prefix
     */
    String getGroupPrefix(UUID player);

    /**
     * Returns the group's suffix for the player. Group Prefix weight must be 5 or less or this won't work.
     *
     * @param player
     * @return the suffix
     */
    String getGroupSuffix(UUID player);

    /**
     * Returns the player's prefix. Player prefix weight must be 6 for this to work.
     *
     * @param player
     * @return the prefix
     */
    String getPlayerPrefix(UUID player);

    /**
     * Returns the player's suffix. Player suffix weight must be 6 for this to work.
     *
     * @param player
     * @return the suffix
     */
    String getPlayerSuffix(UUID player);

    /**
     * Returns the player's primary group name.
     *
     * @param player
     * @return the name of the group
     */
    String getPrimaryGroup(UUID player);
}
