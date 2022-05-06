package io.github.nexuskrop.nexusshift.command;

import io.github.nexuskrop.nexusshift.command.exceptions.CommandExecutionException;
import io.github.nexuskrop.nexusshift.command.exceptions.CommandSyntaxException;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public abstract class BaseCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        try {
            run(sender, args, command);
        } catch (CommandExecutionException exException) {
            SenderUtil.sendFailure(sender, exException.getMessage());
            return true;
        } catch (CommandSyntaxException syntaxException) {
            SenderUtil.sendFailure(sender, syntaxException.getMessage());
            return false;
        }

        return true;
    }

    public abstract void run(@NotNull CommandSender sender, @NotNull String[] args, @NotNull Command command)
            throws CommandExecutionException, CommandSyntaxException;
}
