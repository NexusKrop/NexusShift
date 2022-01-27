// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class MessageUtil {
    private MessageUtil() {}

    private static final String SHOT_AND_KILLED = "ui.broadcast.shot_and_killed";
    private static final String KILLED_MELEE = "ui.broadcast.killed_melee";

    public static Component getDeathComponent(@NotNull EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null) throw new IllegalArgumentException("Entity was not killed by a player.");

        Component result = Component.text(event.getEntity().getKiller().getName())
                .color(NamedTextColor.RED);

        Entity victim = event.getEntity();
        if (Objects.requireNonNull(victim.getLastDamageCause()).getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            result = result.append(Component.text(" "))
                    .append(Component.text(ConfigUtil.getMessage(SHOT_AND_KILLED)).color(NamedTextColor.GRAY))
                    .append(Component.text(" "));
        } else {
            result = result.append(Component.text(" "))
                    .append(Component.text(ConfigUtil.getMessage(KILLED_MELEE)).color(NamedTextColor.GRAY))
                    .append(Component.text(" "));
        }

        result = result.append(Component.text(victim.getName()).color(NamedTextColor.DARK_AQUA).decorate(TextDecoration.ITALIC));

        return result;
    }
}
