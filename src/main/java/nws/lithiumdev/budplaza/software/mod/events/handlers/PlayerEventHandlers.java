package nws.lithiumdev.budplaza.software.mod.events.handlers;

import net.kyori.adventure.text.Component;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import nws.lithiumdev.budplaza.software.players.PlayerUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerEventHandlers implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PlayerUtil.blipPlayer(event.getPlayer());
        event.getPlayer().sendMessage(ConfigUtil.getComponentMessage("welcome"));
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack stack = event.getItemDrop().getItemStack();
    }
}
