package nws.lithiumdev.budplaza.software.mod.commands;

import dev.jorel.commandapi.annotations.*;
import dev.jorel.commandapi.annotations.arguments.ABooleanArgument;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import nws.lithiumdev.budplaza.software.mod.util.CommandUtil;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import nws.lithiumdev.budplaza.software.players.PlayerUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("budplaza")
@Deprecated
public class VersionCommand {
    @Default
    public static void defaultCommand(CommandSender sender) {
        sender.sendMessage("BudPlaza :)");
    }

    @Subcommand("reload")
    @Permission("budplaza.commands.reload")
    @NeedsOp
    public static void reloadCommand(CommandSender sender) {
        sender.sendMessage(ConfigUtil.getComponentMessage("reloadConfig"));
        ConfigUtil.reloadConfig();
        sender.sendMessage(ConfigUtil.getComponentMessage("reloadConfigDone"));
    }

    @Subcommand("version")
    public static void versionCommand(CommandSender sender) {
        sender.sendMessage(ConfigUtil.getMessage("version"));
    }

    @Subcommand("perf")
    public static void perfCommand(CommandSender sender, @AStringArgument String key, @ABooleanArgument boolean value) {
        if (!PlayerUtil.perfs.contains(key)) {
            sender.sendMessage(Component.text(ConfigUtil.getMessage("commands.perf.no_such_perf"))
                    .color(NamedTextColor.RED));
            return;
        }

        if (!(sender instanceof Player player)) {
            CommandUtil.feedbackFault(sender, String.format(ConfigUtil.getMessage("commands.generic.requires_player"),
                    sender.getClass().getSimpleName()));
            return;
        }

        PlayerUtil.writeSetting(player, key, value);
    }
}
