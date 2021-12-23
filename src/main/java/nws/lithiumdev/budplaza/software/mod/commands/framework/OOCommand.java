package nws.lithiumdev.budplaza.software.mod.commands.framework;

import nws.lithiumdev.budplaza.software.mod.commands.definitions.ICommand;

public interface OOCommand extends ICommand {
    String getName();
    OOCommand addSubcommand(OOCommand command);
}
