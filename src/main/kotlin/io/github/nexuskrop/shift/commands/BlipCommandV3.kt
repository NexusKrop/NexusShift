/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import io.github.nexuskrop.shift.commands.engine.INativeCommand
import io.github.nexuskrop.shift.locale.MessageSet
import io.github.nexuskrop.shift.ui.NativeComponentSerializer
import io.github.nexuskrop.shift.util.Common
import io.github.nexuskrop.shift.util.client.PlayerUtil
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.network.chat.ChatType
import net.minecraft.network.chat.TextComponent
import java.util.*

class BlipCommandV3 : INativeCommand {
    override fun register(dispatcher: CommandDispatcher<CommandSourceStack>?) {
        dispatcher?.register(Commands.literal("blip")
                .then(Commands.argument("victim", EntityArgument.player()))
                .executes(this::execute))
    }

    private fun execute(context : CommandContext<CommandSourceStack>): Int {
        try {
            // Gets the player
            val player = EntityArgument.getPlayer(context, "player")

            // Creates a message and blip the player
            PlayerUtil.blipPlayer(player)
            val mini = MiniMessage.miniMessage().deserialize(MessageSet.get("commands.blip.victim"),
            Placeholder.component("from", player.`adventure$displayName`))

            // Get uuid for sender (if no uuid use zero)
            var uuid = Common.UUID_ZERO
            if (context.source.entity != null) uuid = context.source.entity!!.uuid

            // Sends the message
            player.sendMessage(NativeComponentSerializer.nativeComponentSerializer().serialize(
                    mini
            ), ChatType.SYSTEM, uuid)

            return 1
        } catch (cse: CommandSyntaxException) {
            if (cse.message != null) {
                val message : String = cse.message!!

                context.source.sendFailure(
                        TextComponent(message)
                )
            } else {
                context.source.sendFailure(MessageSet.getMini("commands.generic.unknown_failure"))
            }
            return -1
        }
    }
}