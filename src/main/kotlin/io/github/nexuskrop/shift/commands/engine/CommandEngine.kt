/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift.commands.engine

import com.mojang.brigadier.CommandDispatcher
import io.github.nexuskrop.shift.util.Common
import net.minecraft.commands.CommandSourceStack

/**
 * Provides methods to manipulate the Minecraft Command Engine.
 */
object CommandEngine {
    init {
        throw IllegalStateException("No CommandEngine instances for you!")
    }

    /**
     * Gets the dispatcher.
     */
    @JvmStatic
    fun getDispatcher(): CommandDispatcher<CommandSourceStack> {
        return Common.getVanillaServer().vanillaCommandDispatcher.dispatcher;
    }
}