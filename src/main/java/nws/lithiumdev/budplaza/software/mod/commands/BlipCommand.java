package nws.lithiumdev.budplaza.software.mod.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import nws.lithiumdev.budplaza.software.mod.Globals;
import org.bukkit.Sound;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BlipCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 1) {
            sender.sendMessage(Component.text("Parameters fail").color(NamedTextColor.RED));
            return false;
        }

        Player player = Globals.server.getPlayerExact(args[0]);
        if (player == null) {
            sender.sendMessage(Component.text("No such player: ").color(NamedTextColor.RED)
                    .append(Component.text(args[0]).color(NamedTextColor.GRAY).decorate(TextDecoration.ITALIC)));
            return false;
        }

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

        if (sender instanceof ConsoleCommandSender) {
            player.sendMessage(Component.text("Console operator wants your attention.").color(NamedTextColor.GRAY).decorate(TextDecoration.ITALIC));
        } else if (sender instanceof Player) {
            player.sendMessage(sender.name().append(Component.text(" wants your attention.")
                    .color(NamedTextColor.GRAY).decorate(TextDecoration.ITALIC)));
        }

        return true;
    }
}
