// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

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
            new TpsCommand(),
            new BedCommand()
    };

    public static void registerAllCommands() {
        for (var command:
             commands) {
            command.register();
        }
    }
}
