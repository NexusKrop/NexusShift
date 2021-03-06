// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
import io.github.nexuskrop.shift.ui.Messages;
import net.kyori.adventure.text.Component;
import nws.lithiumdev.budplaza.software.players.PlayerUtil;

public class HomeCommand implements ICommand {
    private final Component noHomeMessage = Messages.getParsed("commands.home.no_home");

    @Override
    public void register() {
        new CommandAPICommand("home")
                .executesPlayer((sender, args) -> {
                    var home = PlayerUtil.getHome(sender);

                    if (home == null) {
                        sender.sendMessage(noHomeMessage);
                    }
                })
                .withPermission("budplaza.commands.home")
                .register();
    }
}
