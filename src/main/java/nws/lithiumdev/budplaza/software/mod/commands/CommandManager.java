package nws.lithiumdev.budplaza.software.mod.commands;

import dev.jorel.commandapi.CommandAPI;
import nws.lithiumdev.budplaza.software.mod.commands.definitions.*;

public final class CommandManager {
    private CommandManager() {}

    private static final ICommand[] commands = {
            new HomeCommand(),
            new SetSpawnCommand(),
            new FireballCommand(),
            new SpawnCommand(),
            new BlipCommandA(),
            new VersionCommandA()
    };

    public static void registerAllCommands() {
        for (var command:
             commands) {
            command.register();
        }
    }
}
