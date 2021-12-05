package nws.lithiumdev.budplaza.software.mod.commands;

import jdk.nashorn.internal.runtime.Version;
import nws.lithiumdev.budplaza.software.mod.commands.budcommand.ReloadSubCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CommandManager {
    private CommandManager() {}

    public static void registerAllCommands(JavaPlugin plugin) {
        registerCommand(plugin, "blip", new BlipCommand());

        // Needs extra care
        VersionCommand cmd = new VersionCommand();
        cmd.registerCommand("reload", new ReloadSubCommand());
        registerCommand(plugin, "budplaza", cmd);
    }

    public static void registerCommand(JavaPlugin plugin, String name, CommandExecutor executor) {
        Objects.requireNonNull(plugin.getCommand(name)).setExecutor(executor);
    }
}
