package nws.lithiumdev.budplaza.software.mod.commands.framework;

import dev.jorel.commandapi.executors.PlayerCommandExecutor;

public abstract class GenericCommand extends OOCommand {
    public abstract void onExecute(PlayerCommandExecutor executor, String[] args);
}
