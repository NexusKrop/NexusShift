// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.events;

import nws.lithiumdev.budplaza.software.mod.events.handlers.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * An util class to manage event handlers.
 * @author WithLithum
 */
public final class EventHandlerManager {
    private static final Logger logger = LogManager.getLogger("BP-EventHandler");
    private EventHandlerManager() {}

    private static boolean registered;

    /**
     * Registers all available event handlers. <br />
     * <b>Only register once!</b>
     * @param plugin The java plugin yourself.
     * @throws IllegalStateException Thrown when attempting to register twice.
     */
    public static void registerHandlers(JavaPlugin plugin) {
        if (registered) {
            throw new IllegalStateException("Illegal plugin!");
        }

        registered = true;
        logger.info("Initializing events");
        PluginManager manager = plugin.getServer().getPluginManager();

        manager.registerEvents(new BlockEventHandlers(), plugin);
        manager.registerEvents(new EntityEventHandlers(), plugin);
        manager.registerEvents(new PlayerEventHandlers(), plugin);
    }
}
