package nws.lithiumdev.budplaza.software.mod.commands;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import org.bukkit.command.CommandSender;

@Command("budplaza")
public class VersionCommand {
    @Default
    public static void defaultCommand(CommandSender sender) {
        sender.sendMessage("BudPlaza :)");
    }

    @Subcommand("reload")
    @Permission("budplaza.commands.reload")
    public static void reloadCommand(CommandSender sender) {
        sender.sendMessage(ConfigUtil.getComponentMessage("reloadConfig"));
        ConfigUtil.reloadConfig();
        sender.sendMessage(ConfigUtil.getComponentMessage("reloadConfigDone"));
    }

    @Subcommand("version")
    public static void versionCommand(CommandSender sender) {
        sender.sendMessage(ConfigUtil.getMessage("version"));
    }
}
