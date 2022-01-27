// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.commands.framework;

import dev.jorel.commandapi.executors.PlayerCommandExecutor;

public abstract class GenericCommand implements OOCommand {
    public abstract void onExecute(PlayerCommandExecutor executor, String[] args);
}
