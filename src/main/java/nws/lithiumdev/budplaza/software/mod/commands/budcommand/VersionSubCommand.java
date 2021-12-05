package nws.lithiumdev.budplaza.software.mod.commands.budcommand;

import nws.lithiumdev.budplaza.software.mod.util.CommandUtil;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VersionSubCommand implements ISubCommand {
    @Override
    public boolean onCommand(CommandSender player, Command command, String[] args) {
        if (args.length > 1) {
            CommandUtil.feedbackExpectNothing(player);
            return false;
        }

        player.sendMessage(ConfigUtil.getMessage("version"));
        return true;
    }

    @Override
    public @NotNull String getPermission() {
        return "budplaza.commands.version";
    }

    @Override
    public @NotNull String getUsage() {
        return "/budplaza version";
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String[] args) {
        return null;
    }
}
