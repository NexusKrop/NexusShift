/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift.util.level;

import net.minecraft.world.entity.Entity;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftEntity;

import java.util.Objects;

public final class EntityUtil {
    private EntityUtil() {
        throw new IllegalStateException("No EntityUtil instances for you!");
    }

    /**
     * Converts a Bukkit {@link org.bukkit.entity.Entity} to a Minecraft vanilla {@link Entity}.
     * @param entity The bukkit entity to convert from.
     * @return The Minecraft Entity.
     * @throws IllegalArgumentException Entity is not {@link CraftEntity}.
     */
    public static Entity toMinecraftEntity(org.bukkit.entity.Entity entity) {
        if (!(Objects.requireNonNull(entity) instanceof CraftEntity ce)) {
            throw new IllegalArgumentException("Entity is not CraftEntity.");
        }

        return ce.getHandle();
    }
}
