package nws.lithiumdev.budplaza.software.mod.events.handlers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

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
                        .append(Component.text(m.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()).color(NamedTextColor.RED))
                        .append(Component.text(" )").color(NamedTextColor.WHITE)));
            }
        }
    }
}
