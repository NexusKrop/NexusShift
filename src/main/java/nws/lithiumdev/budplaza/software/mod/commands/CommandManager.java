package nws.lithiumdev.budplaza.software.mod.commands;

import nws.lithiumdev.budplaza.software.mod.commands.definitions.*;

public final class CommandManager {
    private CommandManager() {}

    private static final ICommand[] commands = {
            new HomeCommand(),
            new SetSpawnCommand(),
            new FireballCommand(),
            new SpawnCommand(),
            new BlipCommandA(),
            new VersionCommandA(),
            new TpsCommand()
    };

    public static void registerAllCommands() {
        for (var command:
             commands) {
            command.register();
        }
    }
}
