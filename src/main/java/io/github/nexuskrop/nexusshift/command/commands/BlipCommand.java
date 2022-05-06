package io.github.nexuskrop.nexusshift.command.commands;

import io.github.nexuskrop.nexusshift.command.BaseCommand;
import io.github.nexuskrop.nexusshift.command.exceptions.CommandExecutionException;
import io.github.nexuskrop.nexusshift.command.exceptions.CommandSyntaxException;
import io.github.nexuskrop.nexusshift.game.PlayerUtil;
import io.github.nexuskrop.nexusshift.locale.MessageSet;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * Provides logic for the {@code blip} command.
 */
public class BlipCommand extends BaseCommand {
    @Override
    public String getName() {
        return "blip";
    }

    @Override
    public int requiredArguments() {
        return 1;
    }

    @Override
    public int maximumArguments() {
        return 1;
    }

    @Override
    public void run(@NotNull CommandSender sender, @NotNull String[] args, @NotNull Command command) throws CommandExecutionException, CommandSyntaxException {
        var player = Bukkit.getPlayer(args[0]);

        if (player == null || !player.isOnline()) {
            throw new CommandSyntaxException(MessageSet.get(NO_SUCH_PLAYER));
        }

        var component = MiniMessage.miniMessage().deserialize(MessageSet.get("commands.blip.victim"),
                Placeholder.component("source", sender.name()));
        var success = MiniMessage.miniMessage().deserialize(MessageSet.get("commands.blip.success"),
                Placeholder.component("victim", player.name()));

        PlayerUtil.blipPlayer(player);
        player.sendMessage(component);
        sender.sendMessage(success);
    }
}
