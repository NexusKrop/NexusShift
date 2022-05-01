// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
import io.gitlab.budplaza.calamity.plugin.config.Messages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SetSpawnCommand implements ICommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void register() {
        new CommandAPICommand("setspawn")
                .executesPlayer((sender, args) -> {
                    var loc = sender.getLocation();

                   sender.getWorld().setSpawnLocation(loc);
                   sender.sendMessage(Messages.getParsed("commands.setspawn.success"));
                   logger.info(String.format("%s set spawn of %s to %s, %s, %s [%s]", sender.getName(), sender.getWorld().getName(), loc.getBlockX(), loc.getBlockY(),
                           loc.getBlockZ(), loc.getYaw()));
                })
                .withPermission("budplaza.commands.setspawn")
                .withHelp("Sets spawn point, equivalent to setworldspawn.", "Sets the spawn position of the current world to your current position")
                .register();
    }
}
