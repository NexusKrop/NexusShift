package nws.lithiumdev.budplaza.software.mod.commands;

import dev.jorel.commandapi.CommandAPI;

public final class CommandManager {
    private CommandManager() {}

    public static void registerAllCommands() {
        CommandAPI.registerCommand(BlipCommand.class);
        CommandAPI.registerCommand(VersionCommand.class);
    }
}
