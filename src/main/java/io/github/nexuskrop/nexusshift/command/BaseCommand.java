package io.github.nexuskrop.nexusshift.command;

import io.github.nexuskrop.nexusshift.command.exceptions.CommandExecutionException;
import io.github.nexuskrop.nexusshift.command.exceptions.CommandSyntaxException;
import io.github.nexuskrop.nexusshift.locale.MessageSet;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public abstract class BaseCommand implements CommandExecutor {
    private static final String TOO_MANY_ARGUMENTS = "commands.generic.too_many_arguments";
    private static final String TOO_FEW_ARGUMENTS = "commands.generic.too_few_arguments";

    protected static final String NO_SUCH_PLAYER = "commands.generic.no_such_player";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < requiredArguments()) {
            SenderUtil.sendFailure(sender, MessageSet.get(TOO_FEW_ARGUMENTS));
            return false;
        }

        var maxArgs = maximumArguments();
        if (maxArgs > 0 && args.length > maxArgs) {
            SenderUtil.sendFailure(sender, MessageSet.get(TOO_MANY_ARGUMENTS));
            return false;
        }

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

    public abstract String getName();
    public int requiredArguments() {
        return 0;
    }

    public int maximumArguments() {
        return -1;
    }

    public abstract void run(@NotNull CommandSender sender, @NotNull String[] args, @NotNull Command command)
            throws CommandExecutionException, CommandSyntaxException;
}
