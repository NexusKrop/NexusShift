/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.gitlab.budplaza.calamity.plugin.util;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class PlayerUtil
{
    private PlayerUtil()
    {
        throw new IllegalStateException("# Supernatural Mobs, we are undefeatable");
    }

    public static void BlipPlayer(@NotNull Player player)
    {
        if (!Objects.requireNonNull(player).isOnline())
        {
            throw new IllegalArgumentException("Player was not online.");
        }

        // Send audio
        player.playSound(Sound.sound(Key.key("minecraft:block.note_block.harp"), Sound.Source.MASTER, 100f, 0.8f));
    }
}
