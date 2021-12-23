package nws.lithiumdev.budplaza.software.mod.commands.framework;

import dev.jorel.commandapi.CommandAPICommand;
import nws.lithiumdev.budplaza.software.mod.commands.definitions.ICommand;
import org.bukkit.command.CommandSender;

public abstract class OOCommand implements ICommand {
    public abstract String getName();
    public abstract OOCommand addSubcommand(OOCommand command);
}
