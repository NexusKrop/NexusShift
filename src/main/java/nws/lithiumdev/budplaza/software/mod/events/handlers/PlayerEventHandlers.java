package nws.lithiumdev.budplaza.software.mod.events.handlers;

import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import nws.lithiumdev.budplaza.software.players.PlayerUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEventHandlers implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Get player and send welcome message
        PlayerUtil.blipPlayer(event.getPlayer());
        event.getPlayer().sendMessage(ConfigUtil.getComponentMessage("welcome"));
    }
}
