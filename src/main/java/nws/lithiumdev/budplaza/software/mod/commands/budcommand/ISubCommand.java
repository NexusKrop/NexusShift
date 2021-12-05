package nws.lithiumdev.budplaza.software.mod.commands.budcommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Represents a subcommand, triggered by a {@link RootCommand}.
 */
public interface ISubCommand {
    /**
     * Executes the logic of this command.
     * @param player The sender of this command.
     * @param command The command instance.
     * @param args The arguments, including the subcommand.
     * @return Whether the command succeeds. If the command was unsuccessful, the usage of the command will be sent to the {@link CommandSender}.
     */
    boolean onCommand(CommandSender player, Command command, String[] args);

    /**
     * Gets the permission required to execute this subcommand.
     * @return The permission required to execute this subcommand.
     */
    @NotNull
    String getPermission();

    /**
     * Gets the usage of this subcommand.
     * @return The usage of this subcommand.
     */
    @NotNull
    String getUsage();

    List<String> onTabComplete(CommandSender sender, Command command, String[] args);
}
