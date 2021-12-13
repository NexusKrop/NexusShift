package nws.lithiumdev.budplaza.software.mod.commands;

import dev.jorel.commandapi.CommandAPI;
import nws.lithiumdev.budplaza.software.mod.commands.definitions.FireballCommand;
import nws.lithiumdev.budplaza.software.mod.commands.definitions.HomeCommand;
import nws.lithiumdev.budplaza.software.mod.commands.definitions.ICommand;
import nws.lithiumdev.budplaza.software.mod.commands.definitions.SetSpawnCommand;

public final class CommandManager {
    private CommandManager() {}

    private static final ICommand[] commands = {
            new HomeCommand(),
            new SetSpawnCommand(),
            new FireballCommand()
    };

    public static void registerAllCommands() {
        CommandAPI.registerCommand(BlipCommand.class);
        CommandAPI.registerCommand(VersionCommand.class);

        for (var command:
             commands) {
            command.register();
        }
    }
}
