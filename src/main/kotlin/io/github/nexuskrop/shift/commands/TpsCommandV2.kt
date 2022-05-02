/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import io.github.nexuskrop.shift.commands.engine.INativeCommand
import io.github.nexuskrop.shift.ui.Messages
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.TextComponent
import org.bukkit.Bukkit

class TpsCommandV2 : INativeCommand {
    override fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(Commands.literal("tps")
                .executes(this::execute))
    }

    private fun execute(context: CommandContext<CommandSourceStack>) : Int {
        context.source.sendSuccess(TextComponent(Messages.get("commands.tps.prefix")).append(
                Bukkit.getServer().tps.toString()
        ), false)

        return 1
    }
}