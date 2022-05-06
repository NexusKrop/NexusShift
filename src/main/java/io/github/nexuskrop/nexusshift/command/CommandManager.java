package io.github.nexuskrop.nexusshift.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandManager {
    private static final Logger LOGGER = LogManager.getLogger("NS-CommandMgr");

    private CommandManager() {
        throw new IllegalStateException("No CommandManager instances for you!");
    }

    private static final BaseCommand[] commands = {
            // Add your commands here
    };

    public static void registerAll(JavaPlugin plugin) {
        for (var cmd : commands) {
            var name = cmd.getName();
            LOGGER.info("Registering command {}", name);
            plugin.getCommand(name).setExecutor(cmd);
        }
    }
}
