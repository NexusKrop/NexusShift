package nws.lithiumdev.budplaza.software.mod.commands;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Help;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

@Command("blip")
@Help("Blips the target player in hope that it brings their attention.")
public class BlipCommand {
    @Default
    @Permission("blip")
    public static void command(CommandSender sender, @APlayerArgument Player player) {
        if (sender instanceof ConsoleCommandSender) {
            player.sendMessage(
                    Component.text(ConfigUtil.getMessage("commands.blip.from_console"))
                            .color(NamedTextColor.GRAY)
                            .decorate(TextDecoration.ITALIC)
            );
            return;
        }

        if (sender instanceof Player senderP) {
            player.sendMessage(
                    senderP.name().append(
                            Component.text(ConfigUtil.getMessage("commands.blip.from_player"))
                                    .color(NamedTextColor.GRAY)
                                    .decorate(TextDecoration.ITALIC)
                    )
            );
        }
    }
}
