package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;

public class SpawnCommand implements ICommand {
    @Override
    public void register() {
        new CommandAPICommand("spawn")
                .executesEntity((sender, args) -> {
                    sender.teleport(sender.getWorld().getSpawnLocation());
                })
                .withPermission(CommandPermission.NONE)
                .withHelp("Teleports you back to the world spawn.", "Warps you back to the spawn point of this world.")
                .register();
    }
}
