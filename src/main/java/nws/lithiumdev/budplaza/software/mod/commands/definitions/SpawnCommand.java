// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
import io.gitlab.budplaza.calamity.plugin.config.Messages;
import nws.lithiumdev.budplaza.software.mod.Globals;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;

public class SpawnCommand implements ICommand {
    @Override
    public void register() {
        new CommandAPICommand("spawn")
                .executesEntity((sender, args) -> {
                    sender.teleport(sender.getWorld().getSpawnLocation());
                    sender.playSound(Globals.SOUND_EXP_PICKUP);
                })
                .withPermission("budplaza.commands.spawn")
                .withHelp(Messages.Get("commands.spawn.help_short"),
                        Messages.Get("commands.spawn.help_long"))
                .register();
    }
}
