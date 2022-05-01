// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.events.handlers;

// The one provided by paper use Kyori Adventure API which did not provide
// any method for comparison.
import io.github.nexuskrop.shift.ui.Messages;
import io.github.nexuskrop.shift.util.client.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerEventHandlers implements Listener {
    private static final Map<UUID, Long> MENTION_COOL_DOWN
            = new HashMap<>();

    @EventHandler
    public void onPlayerJoined(PlayerJoinEvent event) {
        // Get player and send welcome message
        PlayerUtil.blipPlayer(event.getPlayer());
        event.getPlayer().sendMessage(Messages.get("ui.welcome"));
    }

    @EventHandler
    public void onPlayerChatAsync(AsyncPlayerChatEvent chatEvent) {
        var message = chatEvent.getMessage();

        if (!chatEvent.isAsynchronous() || !message.contains("@")) {
            return;
        }

        var playerId = chatEvent.getPlayer().getUniqueId();

        if (MENTION_COOL_DOWN.containsKey(playerId)
            && System.currentTimeMillis() - MENTION_COOL_DOWN.get(playerId) > 25000) {
            chatEvent.getPlayer().sendMessage(Messages.get("chat.mention.cool"));
            chatEvent.setCancelled(true);
            return;
        }

        var messages = message.split(" ");
        var players = Bukkit.getServer().getOnlinePlayers();

        for (var msg:
             messages) {
            if (!msg.startsWith("@")) continue;
            var finMsg = msg.replace("@", "");

            for (var player : players) {
                if (player.getName().equals(finMsg)) {
                    PlayerUtil.blipPlayer(player);
                }
            }
        }
    }
}
