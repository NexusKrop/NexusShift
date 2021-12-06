package nws.lithiumdev.budplaza.software.mod.events.handlers;

import io.papermc.paper.event.block.TargetHitEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import nws.lithiumdev.budplaza.software.mod.Globals;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import nws.lithiumdev.budplaza.software.players.PlayerUtil;
import nws.lithiumdev.budplaza.software.ui.util.VariableSound;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.projectiles.ProjectileSource;

public class BlockEventHandlers implements Listener {
    @EventHandler
    public void onTargetHit(TargetHitEvent event) {
        try {
            ProjectileSource projectileOwner = event.getEntity().getShooter();

            if (projectileOwner instanceof Player p) {
                PlayerUtil.blipPlayer(p);
                p.sendActionBar(Component.text(ConfigUtil.getMessage("target_block_summary") + ": ")
                        .color(NamedTextColor.GOLD)
                        .append(
                                Component.text(event.getSignalStrength()).color(NamedTextColor.AQUA)
                        )
                        .append(Component.text("/").color(NamedTextColor.WHITE))
                        .append(Component.text("15").color(NamedTextColor.GREEN)));
            }
        } catch (Exception ex) {
            Globals.logger.error("Exception caught when processing onTargetHit: ", ex);
        }
    }

    public void onPlace(BlockPlaceEvent event) {

    }
}
