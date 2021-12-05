package nws.lithiumdev.budplaza.software.mod.commands.budcommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface ISubCommand {
    void onCommand(CommandSender player, Command command, String[] args);
    String getPermission();
}
