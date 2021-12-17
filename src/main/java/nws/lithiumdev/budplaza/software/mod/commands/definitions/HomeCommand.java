package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
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
                        CommandUtil.feedbackFault(sender, ConfigUtil.getMessage(NO_HOME));
                        return;
                    }
                })
                .withPermission("budplaza.commands.home")
                .register();
    }
}
