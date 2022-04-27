/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift.util.client;

import io.github.nexuskrop.shift.NexusShift;
import io.gitlab.budplaza.calamity.plugin.config.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class PlayerUtil {
    private PlayerUtil() {
        throw new IllegalStateException("No PlayerUtil instances for you!");
    }

    private static final String METADATA_HOME_X = "home-x";
    private static final String METADATA_HOME_Y = "home-y";
    private static final String METADATA_HOME_Z = "home-z";
    private static final String METADATA_HOME_DIM = "home-dim";
    private static final String DOUBLE_FORMAT_HEALTH = "%.2f";

    /**
     * Plays a sound effect to the specified player.
     * @param player The player to blip.
     * @throws NullPointerException Thrown when player is null.
     */
    public static void blipPlayer(@NotNull Player player) {
        Objects.requireNonNull(player).playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
    }

    /**
     * Gets the home of the specified player.
     * @param player The player.
     * @return The home of the specified player; if not found, returns {@code null}.
     */
    @Nullable
    public static Location getHome(@NotNull Player player) {
        if (!player.hasMetadata(METADATA_HOME_X)
                || !player.hasMetadata(METADATA_HOME_Y)
                || !player.hasMetadata(METADATA_HOME_Z)
                || !player.hasMetadata(METADATA_HOME_DIM)) {
            return null;
        }

        var x = player.getMetadata(METADATA_HOME_X).get(0).asDouble();
        var y = player.getMetadata(METADATA_HOME_Y).get(0).asDouble();
        var z = player.getMetadata(METADATA_HOME_Z).get(0).asDouble();
        var dim = player.getMetadata(METADATA_HOME_DIM).get(0).asString();

        var world = Bukkit.getServer().getWorld(dim);

        return new Location(world, x, y, z);
    }

    public static void setHome(@NotNull Player player, @NotNull Location loc) {
        var plg = NexusShift.getInstance();

        player.setMetadata(METADATA_HOME_X, new FixedMetadataValue(plg, loc.getBlockX()));
        player.setMetadata(METADATA_HOME_Y, new FixedMetadataValue(plg, loc.getBlockY()));
        player.setMetadata(METADATA_HOME_Z, new FixedMetadataValue(plg, loc.getBlockZ()));
        player.setMetadata(METADATA_HOME_DIM, new FixedMetadataValue(plg, loc.getWorld().getName()));
    }

    public static Component getDamageComponent(EntityDamageByEntityEvent event, LivingEntity living, AttributeInstance maxHealth) {
        // Calculates the correct health after damage
        var finHealth = living.getHealth() - event.getDamage();

        if (maxHealth == null) {
            throw new IllegalStateException("No MAX HEALTH?");
        }

        // Make sure dead entities get the correct health
        if (finHealth < 0) finHealth = 0;

        return MiniMessage.miniMessage()
                .deserialize(Messages.Get("ui.hit_indicator"),
                        Placeholder.unparsed("target", living.getName()),
                        Placeholder.unparsed("damage", String.format(DOUBLE_FORMAT_HEALTH, event.getDamage())),
                        Placeholder.unparsed("health", String.format(DOUBLE_FORMAT_HEALTH, finHealth)),
                        Placeholder.unparsed("maxHealth", String.format(DOUBLE_FORMAT_HEALTH, maxHealth.getValue()))
                );
    }
}
