/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.gitlab.budplaza.calamity.plugin.commands;

import dev.jorel.commandapi.CommandAPICommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import nws.lithiumdev.budplaza.software.mod.commands.definitions.ICommand;

public class BedCommandV2 implements ICommand
{
    @Override
    public void register()
    {
        new CommandAPICommand("bed")
                .withPermission("calamity.command.bed")
                .executesPlayer(((sender, args) -> {
                    var pos = sender.getBedSpawnLocation();
                    if (pos == null)
                    {
                        sender.sendMessage(Component.translatable("block.minecraft.spawn.not_valid")
                                .color(NamedTextColor.RED));
                        return;
                    }

                    sender.teleport(pos);
                }));
    }
}
