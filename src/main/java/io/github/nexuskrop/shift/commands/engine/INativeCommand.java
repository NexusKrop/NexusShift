/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift.commands.engine;

import com.mojang.brigadier.CommandDispatcher;
import io.github.nexuskrop.shift.ui.Messages;
import io.github.nexuskrop.shift.ui.NativeComponentSerializer;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import static io.github.nexuskrop.shift.ui.NativeComponentSerializer.nativeComponentSerializer;

/**
 * Represents a native command.
 */
public interface INativeCommand {
    void register(CommandDispatcher<CommandSourceStack> dispatcher);

    Component NO_SUCH_ENTITY_MESSAGE = nativeComponentSerializer().serialize(
            Messages.getParsed("commands.generic.no_such_entity")
    );

    Component REQUIRES_ENTITY_MESSAGE = nativeComponentSerializer().serialize(
            Messages.getParsed("commands.generic.requires_entity")
    );
}
