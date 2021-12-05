package nws.lithiumdev.budplaza.software.mod.commands;

import dev.jorel.commandapi.CommandAPI;
import jdk.nashorn.internal.runtime.Version;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CommandManager {
    private CommandManager() {}

    public static void registerAllCommands() {
        CommandAPI.registerCommand(BlipCommand.class);
        CommandAPI.registerCommand(VersionCommand.class);
    }
}
