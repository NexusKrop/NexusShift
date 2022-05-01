/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.jorel.commandapi.CommandAPICommand;
import io.github.nexuskrop.shift.commands.engine.INativeCommand;
import io.github.nexuskrop.shift.ui.Messages;
import io.github.nexuskrop.shift.ui.NativeComponentSerializer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import nws.lithiumdev.budplaza.software.mod.commands.definitions.ICommand;
import org.bukkit.Location;

import java.util.Random;

/**
 * Provides implementation of the <c>rtp</c> command。
 */
public class RtpCommand implements INativeCommand {
    private static final Random random = new Random();
    private final Component leaveVehicleWarning = NativeComponentSerializer.nativeComponentSerializer()
            .serialize(Messages.getParsed("commands.rtp.in_vehicle"));

    @Override
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("rtp")
                .requires(source -> source.getEntity() != null)
                .executes(this::run));
    }

    private int run(CommandContext<CommandSourceStack> context) {
        var entity = context.getSource().getEntity();

        // Thrown if not entity
        if (entity == null) {
            context.getSource().sendFailure(REQUIRES_ENTITY_MESSAGE);
            return 0;
        }

        // Thrown if sitting in anything
        if (entity.isPassenger()) {
            context.getSource().sendFailure(leaveVehicleWarning);
            return 0;
        }

        // Get a random location based on entity current location
        var x = entity.getX() + random.nextDouble(-1230, 1230.5);
        var z = entity.getZ() + random.nextDouble(-1230, 1230.1);

        // Get the highest block at that location and teleport
        var y = context.getSource().getLevel().getWorld().getHighestBlockYAt((int) x, (int) z);
        entity.teleportTo(x, y, z);

        return 1;
    }
}
