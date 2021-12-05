package nws.lithiumdev.budplaza.software.mod.commands.budcommand;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import nws.lithiumdev.budplaza.software.mod.util.CommandUtil;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Represents a command that served as a root of {@link ISubCommand}s.
 */
public abstract class RootCommand implements CommandExecutor, TabCompleter {
    private final Map<String, ISubCommand> commands = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) {
            sender.sendMessage(Component.text(ConfigUtil.getMessage("expected.subcommand_none")).color(NamedTextColor.RED));
            return false;
        }

        if (!commands.containsKey(args[0])) {
            sender.sendMessage(
                    Component.text(String.format(ConfigUtil.getMessage("expected.subcommand"), args[0])).color(NamedTextColor.RED)
            );
            return false;
        }

        ISubCommand subcommand = commands.get(args[0]);
        if (!sender.hasPermission(subcommand.getPermission())) {
            CommandUtil.feedbackNoPermission(sender);
            return false;
        }

        if (!commands.get(args[0]).onCommand(sender, command, args)) {
            sender.sendMessage(commands.get(args[0]).getUsage());
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length >= 2) {
            if (commands.containsKey(args[0])) {
                return commands.get(args[0]).onTabComplete(sender, command, args);
            }
            return null;
        }

        return Arrays.asList((commands.keySet().toArray(new String[0])));
    }

    /**
     * Registers a new subcommand.
     * The command will fail silently if it was already added.
     * @param command The name of the command.
     * @param instance The command instance.
     * @throws NullPointerException Thrown when any parameter is null.
     */
    public void registerCommand(@NotNull String command, @NotNull ISubCommand instance) {
        commands.putIfAbsent(Objects.requireNonNull(command), Objects.requireNonNull(instance));
    }
}
