// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.events.handlers;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.sound.Sound;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import nws.lithiumdev.budplaza.software.mod.util.MessageUtil;
import nws.lithiumdev.budplaza.software.players.PlayerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

import java.util.Objects;

public class EntityEventHandlers implements Listener {
    private static final Logger logger = LogManager.getLogger("BP-EntityEvents");
    public static final String MESSAGE_TARGET_ENTITY = "ui.target.entity";

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getHitEntity() == null || !(event.getEntity().getShooter() instanceof Player p)) return;

        if (event.getHitEntity() instanceof Mob mob) {
            if (mob.isDead()) {
                p.playSound(Sound.sound(Key.key("entity.experience_orb.pickup"), Sound.Source.MASTER, 1f, 1f));
                p.sendActionBar(Component.text(ConfigUtil.getMessage(MESSAGE_TARGET_ENTITY))
                        .append(Component.text(mob.getName()).color(NamedTextColor.GRAY)
                                .decorate(TextDecoration.ITALIC))
                        .append(Component.text(" [DEAD]")
                                .color(NamedTextColor.RED)));
            } else {
                PlayerUtil.blipPlayer(p);
            }
        }
    }

    @EventHandler
    public void onEntityDie(EntityDeathEvent event) {
        try {
            if (event.getEntity().getKiller() != null) {
                Player p = event.getEntity().getKiller();
                p.playSound(Sound.sound(Key.key("ui.loom.select_pattern"), Sound.Source.PLAYER, 10, 0.5f));
                p.sendActionBar(Component.text(ConfigUtil.getMessage(MESSAGE_TARGET_ENTITY))
                        .append(Component.text(event.getEntity().getName()).color(NamedTextColor.GRAY)
                                .decorate(TextDecoration.ITALIC))
                        .append(Component.text(" [DEAD]")
                                .color(NamedTextColor.RED)));
                Bukkit.getServer().broadcast(MessageUtil.getDeathComponent(event));
            }
        } catch (Exception ex) {
            logger.error("Error when handling entity death: ", ex);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        try {
            var ent = event.getEntity();
            Entity source = null;

            if (ent instanceof TNTPrimed tnt) {
                source = tnt.getSource();

                // Make sure source is valid
                if (source != null && !source.isValid()) {
                    source = null;
                }
            }

            event.getEntity().getWorld().createExplosion(event.getLocation(), 4f, false, false, source);
            event.setCancelled(true);
        } catch (Exception ex) {
            logger.error("Error when handling explosion: ", ex);
        }
    }

    @EventHandler
    public void onEntityDamagedByEntity(EntityDamageByEntityEvent event) {
        var entity = event.getEntity();

        // If melee
        if (event.getDamager() instanceof Player p) {
            p.playSound(Sound.sound(Key.key("item.trident.throw"), Sound.Source.PLAYER, 1f, 1.3f));
        }

        if (!(entity instanceof LivingEntity living)) {
            return;
        }

        var maxHealth = living.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (maxHealth == null) {
            return;
        }

        if (event.getDamager() instanceof Projectile projectile && projectile.getShooter() instanceof Player shooter && !entity.isDead()) {
            shooter.sendActionBar(PlayerUtil.getDamageComponent(event, living, maxHealth));
        }

        if (event.getDamager() instanceof Player player) {
            player.sendActionBar(PlayerUtil.getDamageComponent(event, living, maxHealth));
        }
    }
}
