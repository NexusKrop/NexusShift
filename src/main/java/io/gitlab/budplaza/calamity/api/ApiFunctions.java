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
 * 提供受保护的对内部 API 方法的安全访问。如果需要从外部调用内部方法，则建议调用此类。
 */
@SuppressWarnings("unused")
public final class ApiFunctions {
    @Contract(value = " -> fail", pure = true)
    private ApiFunctions() {
        throw new IllegalStateException("爬");
    }

    /**
     * 获取某个玩家的 {@code home} 设置位置。
     * @return 如果玩家设置了有效的 Home，则为一个表示该 Home 位置的 {@link Location} 实例；否则为 {@code null}。
     * @param player 欲设置位置的玩家。
     */
    @Nullable
    public static Location getPlayerHome(@NotNull Player player) {
        return PlayerUtil.getHome(Objects.requireNonNull(player));
    }

    /**
     * 设置指定玩家的 Home 位置。
     * @param player 欲设置 Home 位置的玩家。
     * @param location 欲设置 Home 到的位置。
     */
    public static void setPlayerHome(@NotNull Player player, @NotNull Location location) {
        PlayerUtil.setHome(Objects.requireNonNull(player),
                Objects.requireNonNull(location));
    }
}
