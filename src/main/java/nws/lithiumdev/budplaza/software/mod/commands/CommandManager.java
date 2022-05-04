// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.commands;

import io.github.nexuskrop.shift.commands.*;
import io.github.nexuskrop.shift.commands.engine.CommandEngine;
import io.github.nexuskrop.shift.commands.engine.INativeCommand;
import nws.lithiumdev.budplaza.software.mod.commands.definitions.*;

public final class CommandManager {
    private CommandManager() {}

    // 欲注册的指令
    private static final ICommand[] commands = {
            new HomeCommand(),
            new SetSpawnCommand(),
            new FireballCommand(),
            new SpawnCommand(),
            new VersionCommandA()
    };

    private static final INativeCommand[] nativeCommands = {
            new RespawnCommand(),
            new RtpCommand(),
            new TpsCommandV2(),
            new BlipCommandV3()
    };

    /**
     * 注册全部本插件内的指令。
     */
    public static void registerAll() {
        for (var command:
             commands) {
            command.register();
        }

        var dispatcher = CommandEngine.getDispatcher();

        for (var nativeCmd: nativeCommands) {
            nativeCmd.register(dispatcher);
        }
    }
}
