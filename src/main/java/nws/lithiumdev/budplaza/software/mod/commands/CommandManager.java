// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.commands;

import io.github.nexuskrop.shift.commands.*;
import nws.lithiumdev.budplaza.software.mod.commands.definitions.*;

public final class CommandManager {
    private CommandManager() {}

    // 欲注册的指令
    private static final ICommand[] commands = {
            new HomeCommand(),
            new SetSpawnCommand(),
            new FireballCommand(),
            new SpawnCommand(),
            new BlipCommandV2(),
            new VersionCommandA(),
            new TpsCommand(),
            // Calamity
            new BedCommandV2(),
            new RtpCommand()
    };

    /**
     * 注册全部本插件内的指令。
     */
    public static void registerAll() {
        for (var command:
             commands) {
            command.register();
        }
    }
}
