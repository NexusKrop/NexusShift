// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
import io.gitlab.budplaza.calamity.plugin.config.Messages;
import nws.lithiumdev.budplaza.software.mod.util.CommandUtil;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import nws.lithiumdev.budplaza.software.players.PlayerUtil;

public class HomeCommand implements ICommand {
    private static final String NO_HOME = "commands.home.no_home";

    @Override
    public void register() {
        new CommandAPICommand("home")
                .executesPlayer((sender, args) -> {
                    var home = PlayerUtil.getHome(sender);

                    if (home == null) {
                        CommandUtil.feedbackFault(sender, Messages.Get(NO_HOME));
                    }
                })
                .withPermission("budplaza.commands.home")
                .register();
    }
}
