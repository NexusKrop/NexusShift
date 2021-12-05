package nws.lithiumdev.budplaza.software.mod.commands.budcommand;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class RootCommand implements CommandExecutor {
    private final Map<String, ISubCommand> commands = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1 || !commands.containsKey(args[0])) {
            sender.sendMessage(
                    Component.translatable("command.unknown.command").color(NamedTextColor.RED)
            );
            sender.sendMessage("Usage: ");
            return false;
        }

        commands.get(args[0]).onCommand(sender, command, args);
        return true;
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
