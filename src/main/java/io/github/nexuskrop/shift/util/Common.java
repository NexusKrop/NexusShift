/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift.util;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R2.CraftServer;

public final class Common {
    private Common() {
        throw new IllegalStateException("No Common instances for you!");
    }

    public static final Sound SOUND_EXPERIENCE_ORB_PICKUP = Sound.sound(Key.key("entity.experience_orb.pickup"), Sound.Source.PLAYER, 1f, 1f);

    public static MinecraftServer getVanillaServer() {
        var cs = (CraftServer) Bukkit.getServer();
        return cs.getServer();
    }
}
