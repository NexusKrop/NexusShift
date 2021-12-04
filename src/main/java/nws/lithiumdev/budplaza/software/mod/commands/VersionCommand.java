package nws.lithiumdev.budplaza.software.mod.commands;

import nws.lithiumdev.budplaza.software.mod.Globals;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class VersionCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp())
        {
            sender.sendMessage(Globals.config.getString("noPermissionText"));
            return false;
        }

        sender.sendMessage("Running LithiumDev BudPlaza Software " + Globals.VERSION);
        return true;
    }
}
