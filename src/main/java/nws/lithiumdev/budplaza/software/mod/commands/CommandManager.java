package nws.lithiumdev.budplaza.software.mod.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CommandManager {
    private CommandManager() {}

    public static void registerAllCommands(JavaPlugin plugin) {
        registerCommand(plugin, "blip", new BlipCommand());
        registerCommand(plugin, "budplaza", new VersionCommand());
    }

    public static void registerCommand(JavaPlugin plugin, String name, CommandExecutor executor) {
        Objects.requireNonNull(plugin.getCommand(name)).setExecutor(executor);
    }
}
