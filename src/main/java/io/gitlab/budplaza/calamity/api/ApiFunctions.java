/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.gitlab.budplaza.calamity.api;

import nws.lithiumdev.budplaza.software.players.PlayerUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Provide access to internal API methods safely.
 */
@SuppressWarnings("unused")
public final class ApiFunctions {
    @Contract(value = " -> fail", pure = true)
    private ApiFunctions() {
        throw new IllegalStateException("爬");
    }

    /**
     * Gets the location of the home of {@code home}.
     * @return An instance of {@link Location} if player has a home set; otherwise, {@code null}.
     * @param player The player to get the location from.
     */
    @Nullable
    public static Location getPlayerHome(@NotNull Player player) {
        return PlayerUtil.getHome(Objects.requireNonNull(player));
    }

    /**
     * Sets the home location of the specified player.
     * @param player The player to set the home.
     * @param location The location to set the home to.
     */
    public static void setPlayerHome(@NotNull Player player, @NotNull Location location) {
        PlayerUtil.setHome(Objects.requireNonNull(player),
                Objects.requireNonNull(location));
    }
}
