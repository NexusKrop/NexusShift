// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.BooleanArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.jorel.commandapi.executors.CommandExecutor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import nws.lithiumdev.budplaza.software.mod.Globals;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import nws.lithiumdev.budplaza.software.players.PlayerUtil;

public class VersionCommandA implements ICommand {
    @Override
    public void register() {
        new CommandAPICommand("budplaza")
                .withSubcommand(new CommandAPICommand("version")
                        .withHelp("Queries BPS version.", "Shows the version of the BudPlaza Software installed.")
                        .executes((CommandExecutor) (sender, args) -> {
                            sender.sendMessage("Powered by BudPlaza Software");

                            final var copyright = Component.text()
                                    .content("Licensed under ")
                                    .append(Component.text("GNU AGPL v3 (any later version applicable)")
                                            .clickEvent(ClickEvent.openUrl("https://gitlab.com/budplaza/budplaza-software-paper/-/blob/main/LICENSE")))
                                    .append(Component.newline())
                                    .append(Component.text(ConfigUtil.getMessage("commands.version.sources.prefix")))
                                    .append(Component.text(ConfigUtil.getMessage("generic.dev.git_repo"))
                                            .clickEvent(ClickEvent.openUrl(Globals.SOURCES_REPO))
                                            .color(NamedTextColor.BLUE)
                                            .decorate(TextDecoration.ITALIC))
                                    .build();
                            sender.sendMessage(copyright);
                        }))
                .withSubcommand(new CommandAPICommand("reload")
                        .withHelp("Reloads config.", "Reloads the configuration file of BudPlaza Software.")
                        .withPermission("budplaza.commands.reload")
                        .executes(((sender, args) -> {
                            sender.sendMessage(ConfigUtil.getComponentMessage("reloadConfig"));
                            ConfigUtil.reloadConfig();
                            sender.sendMessage(ConfigUtil.getComponentMessage("reloadConfigDone"));
                        })))
                .register();
    }
}
