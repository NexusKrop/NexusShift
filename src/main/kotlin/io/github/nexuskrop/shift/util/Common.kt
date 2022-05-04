/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift.util

import io.github.nexuskrop.shift.NexusShift
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.minecraft.server.MinecraftServer
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_18_R2.CraftServer
import java.io.File
import java.util.UUID

object Common {
    init {
        throw IllegalStateException("No Common instances for you!")
    }

    private var dataFolderCache : File?

    @JvmField
    val SOUND_EXPERIENCE_ORB_PICKUP = Sound.sound(Key.key("entity.experience_orb.pickup"), Sound.Source.PLAYER, 1f, 1f)

    @JvmField val UUID_ZERO: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000")

    @JvmStatic
    fun getVanillaServer(): MinecraftServer {
        val cs = Bukkit.getServer() as CraftServer
        return cs.server
    }

    @JvmStatic
    fun getDataFolder(): File {
        if (dataFolderCache == null) {
            dataFolderCache = NexusShift.getInstance().dataFolder
        }

        return dataFolderCache as File
    }
}