package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import nws.lithiumdev.budplaza.software.mod.Globals;
import org.bukkit.Bukkit;

public class SetSpawnCommand implements ICommand {
    @Override
    public void register() {
        new CommandAPICommand("setspawn")
                .executesPlayer((sender, args) -> {
                    var loc = sender.getLocation();

                   sender.getWorld().setSpawnLocation(loc);
                   sender.sendMessage(Component.translatable("commands.setworldspawn.success").args(
                           Component.text(loc.getBlockX()),
                           Component.text(loc.getBlockY()),
                           Component.text(loc.getBlockZ()),
                           Component.text(loc.getYaw())
                   ).color(NamedTextColor.GOLD));
                   Globals.logger.info(String.format("%s set spawn of %s to %s, %s, %s [%s]", sender.getName(), loc.getBlockX(), loc.getBlockY(),
                           loc.getBlockZ(), loc.getYaw()));
                });
    }
}
