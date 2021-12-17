package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import nws.lithiumdev.budplaza.software.mod.Globals;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import org.bukkit.entity.Player;

public class BlipCommandA implements ICommand {
    @Override
    public void register() {
        new CommandAPICommand("blip")
                .withPermission("budplaza.commands.blip")
                .withHelp("Blips someone.", "Blips a user in hope that this will grab their attention.")
                .withArguments(new PlayerArgument("target"))
                .executesConsole((sender, args) -> {
                    if (!(args[0] instanceof Player p)) {
                        return;
                    }

                    // Send the actual message
                    p.sendMessage(
                            Component.text(ConfigUtil.getMessage("commands.blip.from_console"))
                                    .color(NamedTextColor.GRAY)
                                    .decorate(TextDecoration.ITALIC)
                    );
                    p.playSound(Globals.SOUND_EXP_PICKUP);
                })
                .executesPlayer(((sender, args) -> {
                    if (!(args[0] instanceof Player p)) {
                        return;
                    }

                    p.sendMessage(
                            sender.name().append(
                                    Component.text(ConfigUtil.getMessage("commands.blip.from_player"))
                                            .color(NamedTextColor.GRAY)
                                            .decorate(TextDecoration.ITALIC)
                            )
                    );
                    p.playSound(Globals.SOUND_EXP_PICKUP);
                }))
                .register();
    }
}
