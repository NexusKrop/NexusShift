package nws.lithiumdev.budplaza.software.mod.events.handlers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import nws.lithiumdev.budplaza.software.mod.Globals;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Objects;

public class EntityEventHandlers implements Listener {
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getHitEntity() == null || !(event.getEntity().getShooter() instanceof Player p)) return;

        if (event.getHitEntity() instanceof Mob m) {
            if (m.isDead()) {
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 0.7f, 0.92f);
                p.sendActionBar(Component.text("Target: ")
                        .append(Component.text(m.getName()).color(NamedTextColor.GRAY)
                                .decorate(TextDecoration.ITALIC))
                        .append(Component.text("( KILLED )")
                                .color(NamedTextColor.RED)));
            } else {
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                p.sendActionBar(Component.text("Target: ")
                        .append(Component.text(m.getName()).color(NamedTextColor.GOLD))
                        .append(Component.text("( ")
                                .color(NamedTextColor.WHITE))
                        .append(Component.text(Double.valueOf(m.getHealth()).toString())).color(NamedTextColor.AQUA)
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
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 0.7f, 0.92f);
                p.sendActionBar(Component.text("Target: ")
                        .append(Component.text(event.getEntity().getName()).color(NamedTextColor.GRAY)
                                .decorate(TextDecoration.ITALIC))
                        .append(Component.text("( KILLED )")
                                .color(NamedTextColor.RED)));
            }
        } catch (Exception ex) {
            Globals.logger.error("Error when handling entity death: ", ex);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        try {
            event.getEntity().getWorld().createExplosion(event.getLocation(), 3.5f, false, false);
            event.setCancelled(true);
        } catch (Exception ex) {
            Globals.logger.error("Error when handling explosion: ", ex);
        }
    }
}
