// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandExecutor;
import io.gitlab.budplaza.calamity.plugin.config.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;

public class VersionCommandA implements ICommand {
    private final Component creditsComponent = MiniMessage.miniMessage().deserialize(
            Messages.get("ui.copyright")
    );

    @Override
    public void register() {
        new CommandAPICommand("budplaza")
                .withSubcommand(new CommandAPICommand("version")
                        .withHelp("Queries BPS version.", "Shows the version of the BudPlaza Software installed.")
                        .executes((CommandExecutor) (sender, args) -> sender.sendMessage(creditsComponent)))
                .withSubcommand(new CommandAPICommand("reload")
                        .withHelp("Reloads config.", "Reloads the configuration file of BudPlaza Software.")
                        .withPermission("budplaza.commands.reload")
                        .executes(((sender, args) -> {
                            sender.sendMessage(Messages.get("commands.reload.reloadConfig"));
                            ConfigUtil.reloadConfig();
                            sender.sendMessage(Messages.get("commands.reload.complete"));
                        })))
                .register();
    }
}
