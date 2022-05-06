package io.github.nexuskrop.nexusshift;

import io.github.nexuskrop.nexusshift.command.CommandManager;
import io.github.nexuskrop.nexusshift.locale.MessageSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NexusShift extends JavaPlugin {
    private static final Logger LOG = LogManager.getLogger("NexusShift");

    @Override
    public void onEnable() {
        LOG.info("Initialisation called");
        LOG.info("Loading messages");
        MessageSet.load();

        LOG.info("Registering commands");
        CommandManager.registerAll(this);
    }
}
