/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift;

import io.github.nexuskrop.shift.util.init.LoadingUtil;
import nws.lithiumdev.budplaza.software.mod.Globals;
import nws.lithiumdev.budplaza.software.mod.commands.CommandManager;
import nws.lithiumdev.budplaza.software.mod.events.EventHandlerManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Defines the entry point of the NexusShift.
 */
public final class NexusShift extends JavaPlugin {
    private static final Logger LOG = LogManager.getLogger("NexusShift-Main");

    private static NexusShift instance;

    public NexusShift() {
        instance = this;
    }

    public static NexusShift getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Plugin not yet initialised.");
        }

        return instance;
    }

    @Override
    public void onEnable() {
        LOG.info("Enabled plugin");

        Globals.initFileConfiguration(this.getConfig());
        this.saveConfig();

        LoadingUtil.addDefaultMessages();

        CommandManager.registerAll();
        EventHandlerManager.registerHandlers(this);
    }
}
