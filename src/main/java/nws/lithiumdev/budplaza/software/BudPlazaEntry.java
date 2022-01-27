// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software;

import nws.lithiumdev.budplaza.software.mod.Globals;
import nws.lithiumdev.budplaza.software.mod.commands.CommandManager;
import nws.lithiumdev.budplaza.software.mod.events.EventHandlerManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;

/**
 * Defines the entry point of the plugin.
 */
public final class BudPlazaEntry extends JavaPlugin {

    private static BudPlazaEntry instance;
    private static final Logger budLog = LogManager.getLogger("BudPlaza-Main");

    /**
     * Gets the instance of the current plugin.
     * @return An instance of {@link BudPlazaEntry}.
     */
    @Contract(pure = true)
    public static BudPlazaEntry getInstance() {
        if (instance == null) {
            throw new IllegalStateException("BudPlaza has not yet been instantiated.");
        }

        return instance;
    }

    /**
     * Initializes this instance and it's processes.
     */
    @Override
    public void onEnable() {
        // I'll have to leave it here: (if you have S2696)
        // A lot of things needs the BudPlaza instance.
        instance = this;
        // Plugin startup logic
        budLog.info("BUDPLAZA START");
        var cfg = this.getConfig();
        Globals.initFileConfiguration(cfg);
        this.saveConfig();

        CommandManager.registerAllCommands();
        EventHandlerManager.registerHandlers(this);
    }

    @Override
    public void onDisable() {
        // Reserved
    }
}
