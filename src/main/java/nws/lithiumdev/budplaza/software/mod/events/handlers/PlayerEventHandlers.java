// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.events.handlers;

// The one provided by paper use Kyori Adventure API which did not provide
// any method for comparison.
import io.gitlab.budplaza.calamity.plugin.config.Messages;
import io.gitlab.budplaza.calamity.plugin.util.CPlayerUtil;
import io.papermc.paper.event.player.AsyncChatEvent;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import nws.lithiumdev.budplaza.software.players.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerEventHandlers implements Listener {
    private static final Map<UUID, Long> mentionCoolDown
            = new HashMap<>();

    @EventHandler
    public void OnPlayerJoined(PlayerJoinEvent event) {
        // Get player and send welcome message
        PlayerUtil.blipPlayer(event.getPlayer());
        event.getPlayer().sendMessage(ConfigUtil.getComponentMessage("welcome"));
    }

    @EventHandler
    public void OnPlayerChatAsync(AsyncPlayerChatEvent chatEvent)
    {
        var message = chatEvent.getMessage();

        if (!chatEvent.isAsynchronous() || !message.contains("@"))
        {
            return;
        }

        var playerId = chatEvent.getPlayer().getUniqueId();

        if (mentionCoolDown.containsKey(playerId)
            && System.currentTimeMillis() - mentionCoolDown.get(playerId) > 25000)
        {
            chatEvent.getPlayer().sendMessage(Messages.Get("chat.mention.cool"));
            chatEvent.setCancelled(true);
            return;
        }

        var messages = message.split(" ");
        var players = Bukkit.getServer().getOnlinePlayers();

        for (var msg:
             messages)
        {
            if (!msg.startsWith("@")) continue;
            var finMsg = msg.replace("@", "");

            for (var player : players)
            {
                if (player.getName().equals(finMsg))
                {
                    CPlayerUtil.BlipPlayer(player);
                }
            }
        }
    }
}
