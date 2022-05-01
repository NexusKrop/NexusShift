// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import io.gitlab.budplaza.calamity.plugin.config.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import nws.lithiumdev.budplaza.software.mod.Globals;
import org.bukkit.entity.Player;

public class BlipCommandA implements ICommand {
    @Override
    public void register() {
        new CommandAPICommand("blip")
                .withPermission("budplaza.commands.blip")
                .withHelp(Messages.Get("commands.blip.help_short"),
                        Messages.Get("commands.blip.help_long"))
                .withArguments(new PlayerArgument("target"))
                .executesConsole((sender, args) -> {
                    if (!(args[0] instanceof Player p)) {
                        return;
                    }

                    // Send the actual message
                    p.sendMessage(
                            Component.text(Messages.Get("commands.blip.from_console"))
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
                                    Component.text(Messages.Get("commands.blip.from_player"))
                                            .color(NamedTextColor.GRAY)
                                            .decorate(TextDecoration.ITALIC)
                            )
                    );
                    p.playSound(Globals.SOUND_EXP_PICKUP);
                }))
                .register();
    }
}
