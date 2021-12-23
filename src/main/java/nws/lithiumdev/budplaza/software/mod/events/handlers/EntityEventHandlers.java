package nws.lithiumdev.budplaza.software.mod.events.handlers;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.sound.Sound;
import nws.lithiumdev.budplaza.software.mod.Globals;
import nws.lithiumdev.budplaza.software.mod.util.MessageUtil;
import nws.lithiumdev.budplaza.software.players.PlayerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Objects;

public class EntityEventHandlers implements Listener {
    private static final Logger logger = LogManager.getLogger("BP-EntityEvents");

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getHitEntity() == null || !(event.getEntity().getShooter() instanceof Player p)) return;

        if (event.getHitEntity() instanceof Mob m) {
            if (m.isDead()) {
                p.playSound(Sound.sound(Key.key("entity.experience_orb.pickup"), Sound.Source.MASTER, 1f, 1f));
                p.sendActionBar(Component.text("Target: ")
                        .append(Component.text(m.getName()).color(NamedTextColor.GRAY)
                                .decorate(TextDecoration.ITALIC))
                        .append(Component.text("( KILLED )")
                                .color(NamedTextColor.RED)));
            } else {
                PlayerUtil.blipPlayer(p);
                p.sendActionBar(Component.text("Target: ")
                        .append(Component.text(m.getName()).color(NamedTextColor.GOLD))
                        .append(Component.text("( ")
                                .color(NamedTextColor.WHITE))
                        .append(Component.text(Double.toString(m.getHealth()))).color(NamedTextColor.AQUA)
                        .append(Component.text(" / ").color(NamedTextColor.WHITE))
                        .append(Component.text(Objects.requireNonNull(m.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()).color(NamedTextColor.RED))
                        .append(Component.text(" )").color(NamedTextColor.WHITE)));
            }
        }
    }

    @EventHandler
    public void onEntityDie(EntityDeathEvent event) {
        try {
            if (event.getEntity().getKiller() != null) {
                Player p = event.getEntity().getKiller();
                p.playSound(Sound.sound(Key.key("ui.loom.select_pattern"), Sound.Source.PLAYER, 10, 0.5f));
                p.sendActionBar(Component.text("Target: ")
                        .append(Component.text(event.getEntity().getName()).color(NamedTextColor.GRAY)
                                .decorate(TextDecoration.ITALIC))
                        .append(Component.text("( KILLED )")
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
            event.getEntity().getWorld().createExplosion(event.getLocation(), 3.5f, false, false);
            event.setCancelled(true);
        } catch (Exception ex) {
            logger.error("Error when handling explosion: ", ex);
        }
    }

    @EventHandler
    public void onEntityDamagedByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player p) {
            p.playSound(Sound.sound(Key.key("item.trident.throw"), Sound.Source.PLAYER, 1f, 1.3f));
        }
    }
}
