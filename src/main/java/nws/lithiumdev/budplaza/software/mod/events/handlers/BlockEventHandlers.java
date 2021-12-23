package nws.lithiumdev.budplaza.software.mod.events.handlers;

import io.papermc.paper.event.block.TargetHitEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import nws.lithiumdev.budplaza.software.mod.Globals;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import nws.lithiumdev.budplaza.software.players.PlayerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.projectiles.ProjectileSource;

public class BlockEventHandlers implements Listener {
    private static final Logger logger = LogManager.getLogger("BP-BlockEvents");

    public BlockEventHandlers() {
        super();
        PlayerUtil.registerPref("target.HitMessage");
    }

    @EventHandler
    public void onTargetHit(TargetHitEvent event) {
        try {
            ProjectileSource projectileOwner = event.getEntity().getShooter();
            if (projectileOwner instanceof Player p && PlayerUtil.getSetting(p, "target.HitMessage")) {
                PlayerUtil.blipPlayer(p);
                p.sendActionBar(Component.text(ConfigUtil.getMessage("target_block_summary") + ": ")
                        .color(NamedTextColor.GOLD)
                        .append(Component.text(event.getSignalStrength()).color(NamedTextColor.AQUA))
                        .append(Component.text("/").color(NamedTextColor.WHITE))
                        .append(Component.text("15").color(NamedTextColor.GREEN)));
            }
        } catch (Exception ex) {
            logger.error("Exception caught when processing onTargetHit: ", ex);
        }
    }

    @EventHandler
    public void onExplode(BlockExplodeEvent event) {
        var block = event.getBlock();
        block.getWorld().createExplosion(block.getLocation(), 2f, false, false);
        event.setCancelled(true);
    }
}
