/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.nexuskrop.shift.commands.engine.INativeCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class RespawnCommand implements INativeCommand
{
    @Override
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("respawn")
                .then(Commands.argument("player", EntityArgument.player())
                        .requires(source -> source.hasPermission(Commands.LEVEL_ADMINS))
                        .executes(this::runAs))
                .requires(source -> source.getEntity() instanceof Player)
                .executes(this::run));
    }

    private int runAs(CommandContext<CommandSourceStack> commandContext) {
        Player player;

        try {
            player = EntityArgument.getPlayer(commandContext, "player");
        } catch (CommandSyntaxException cse) {
            commandContext.getSource().sendFailure(
                    new TextComponent(cse.getMessage())
            );
            return -1;
        }

        player.remove(Entity.RemovalReason.UNLOADED_WITH_PLAYER);
        player.respawn();
        return 1;
    }

    private int run(CommandContext<CommandSourceStack> commandContext) {
        if (!(commandContext.getSource().getEntity() instanceof Player player)) {
            return -1;
        }

        player.remove(Entity.RemovalReason.UNLOADED_WITH_PLAYER);
        player.respawn();
        return 0;
    }
}
