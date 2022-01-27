// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
import nws.lithiumdev.budplaza.software.mod.Globals;

public class SpawnCommand implements ICommand {
    @Override
    public void register() {
        new CommandAPICommand("spawn")
                .executesEntity((sender, args) -> {
                    sender.teleport(sender.getWorld().getSpawnLocation());
                    sender.playSound(Globals.SOUND_EXP_PICKUP);
                })
                .withPermission("budplaza.commands.spawn")
                .withHelp("Teleports you back to the world spawn.", "Warps you back to the spawn point of this world.")
                .register();
    }
}
