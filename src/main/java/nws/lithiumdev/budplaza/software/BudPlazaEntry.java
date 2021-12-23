package nws.lithiumdev.budplaza.software;

import nws.lithiumdev.budplaza.software.mod.Globals;
import nws.lithiumdev.budplaza.software.mod.commands.CommandManager;
import nws.lithiumdev.budplaza.software.mod.events.EventHandlerManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public final class BudPlazaEntry extends JavaPlugin {

    private static BudPlazaEntry instance;
    private static final Logger budLog = LogManager.getLogger("BudPlaza-Main");

    /**
     * Gets the instance of the current plugin.
     * @return An instance of {@link BudPlazaEntry}.
     */
    public static BudPlazaEntry getInstance() {
        if (instance == null) {
            throw new IllegalStateException("BudPlaza has not yet been instantiated.");
        }

        return instance;
    }

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
        // Plugin shutdown logic
    }
}
