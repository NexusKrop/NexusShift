// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

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
                .withHelp(ConfigUtil.getMessage("commands.bed.help_short"), ConfigUtil.getMessage("commands.bed.help_long"))
                .withSubcommand(
                        new CommandAPICommand("here")
                                .executesPlayer(((sender, args) -> {
                                    if (sender.getWorld().getBlockAt(sender.getLocation()).isSolid()) {
                                        CommandUtil.feedbackFault(sender, ConfigUtil.getMessage("commands.bed.stuck"));
                                        return;
                                    }

                                    sender.setBedSpawnLocation(sender.getLocation(), true);
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
