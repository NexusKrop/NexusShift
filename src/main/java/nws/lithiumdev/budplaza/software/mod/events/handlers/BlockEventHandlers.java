// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.events.handlers;

import io.gitlab.budplaza.calamity.plugin.config.Messages;
import io.papermc.paper.event.block.TargetHitEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
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
            if (projectileOwner instanceof Player p) {
                PlayerUtil.blipPlayer(p);
                p.sendActionBar(MiniMessage.miniMessage().deserialize(Messages.get("ui.target_block_indicator"),
                        Placeholder.unparsed("strength", Integer.toString(event.getSignalStrength())),
                        Placeholder.unparsed("max", "15")));
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
