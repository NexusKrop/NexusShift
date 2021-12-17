package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class BedCommand implements ICommand {
    @Override
    public void register() {
        new CommandAPICommand("bed")
                .withPermission("budplaza.commands.bed")
                .executesPlayer(((sender, args) -> {
                    if (sender.getBedSpawnLocation() == null) {
                        sender.sendMessage(Component.translatable("block.minecraft.spawn.not_valid")
                                .color(NamedTextColor.RED));
                        return;
                    }

                    sender.teleport(sender.getBedSpawnLocation());
                }))
                .register();
    }
}
