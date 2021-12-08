package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;

public interface ICommand {
    CommandAPICommand command = null;

    void register();
}
