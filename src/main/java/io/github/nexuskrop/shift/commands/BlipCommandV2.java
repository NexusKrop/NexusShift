/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import io.github.nexuskrop.shift.ui.Messages;
import io.github.nexuskrop.shift.util.client.PlayerUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import nws.lithiumdev.budplaza.software.mod.commands.definitions.ICommand;
import org.bukkit.entity.Player;

public class BlipCommandV2 implements ICommand {

    private final Component consoleBlipMessage = Messages.getParsed("commands.blip.from_console");

    @Override
    public void register() {
        new CommandAPICommand("blip")
                .withPermission("nexuskrop.commands.blip")
                .withHelp(Messages.get("commands.blip.help_short"),
                        Messages.get("commands.blip.help_long"))
                .withArguments(new PlayerArgument("target"))
                .executesConsole((sender, args) -> {
                    if (!(args[0] instanceof Player p)) {
                        return;
                    }

                    p.sendMessage(consoleBlipMessage);
                    PlayerUtil.blipPlayer(p);
                })
                .executes((sender, args) -> {
                    if (!(args[0] instanceof Player p)) {
                        return;
                    }

                    p.sendMessage(MiniMessage.miniMessage().deserialize(Messages.get("commands.blip.from_player"),
                            Placeholder.component("from", sender.name())));
                })
                .register();
    }
}
