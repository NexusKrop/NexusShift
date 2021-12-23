package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import nws.lithiumdev.budplaza.software.mod.util.CommandUtil;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;

public class BedCommand implements ICommand {
    @Override
    public void register() {
        new CommandAPICommand("bed")
                .withPermission("budplaza.commands.bed")
                .withSubcommand(
                        new CommandAPICommand("here")
                                .executesPlayer(((sender, args) -> {
                                    if (sender.getWorld().getBlockAt(sender.getLocation()).isSolid()) {
                                        CommandUtil.feedbackFault(sender, ConfigUtil.getMessage("commands.bed.stuck"));
                                        return;
                                    }

                                    sender.setBedSpawnLocation(sender.getLocation());
                                    sender.sendMessage(Component.translatable("block.minecraft.set_spawn"));
                                }))
                )
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
