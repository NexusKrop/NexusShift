package nws.lithiumdev.budplaza.software;

import nws.lithiumdev.budplaza.software.mod.Globals;
import nws.lithiumdev.budplaza.software.mod.commands.CommandManager;
import nws.lithiumdev.budplaza.software.mod.events.EventHandlerManager;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class BudPlazaEntry extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getLog4JLogger().info("Plugin Instantiated");
        Globals.logger = this.getLog4JLogger();

        Globals.config = this.getConfig();

        Globals.config.addDefault("noPermissionText", "§4Not sufficient permissions");
        ConfigUtil.initConfig();
        Globals.config.options().copyDefaults(true);
        Globals.server = getServer();
        saveConfig();

        CommandManager.registerAllCommands(this);
        EventHandlerManager.registerHandlers(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
