package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.BooleanArgument;
import dev.jorel.commandapi.arguments.MultiLiteralArgument;
import dev.jorel.commandapi.executors.CommandExecutor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import nws.lithiumdev.budplaza.software.players.PlayerUtil;

public class VersionCommandA implements ICommand {
    @Override
    public void register() {
        new CommandAPICommand("budplaza")
                .withSubcommand(new CommandAPICommand("version")
                        .withHelp("Queries BPS version.", "Shows the version of the BudPlaza Software installed.")
                        .executes((CommandExecutor) (sender, args) -> sender.sendMessage("Powered by BudPlaza Software")))
                .withSubcommand(new CommandAPICommand("reload")
                        .withHelp("Reloads config.", "Reloads the configuration file of BudPlaza Software.")
                        .withPermission("budplaza.commands.reload")
                        .executes(((sender, args) -> {
                            sender.sendMessage(ConfigUtil.getComponentMessage("reloadConfig"));
                            ConfigUtil.reloadConfig();
                            sender.sendMessage(ConfigUtil.getComponentMessage("reloadConfigDone"));
                        })))
                .withSubcommand(new CommandAPICommand("pref")
                        .withHelp("Modifies preference.", "Alters your preference.")
                        .withPermission("budplaza.commands.pref")
                        .withArguments(new MultiLiteralArgument(PlayerUtil.getPreferenceOptions()), new BooleanArgument("toggle"))
                        .executesPlayer(((sender, args) -> {
                            if (!(args[0] instanceof String key)) {
                                return;
                            }

                            if (!PlayerUtil.isPrefRegistered(key)) {
                                // Feedback
                                sender.sendMessage(Component.text(ConfigUtil.getMessage("commands.perf.no_such_perf"))
                                        .color(NamedTextColor.RED));
                                return;
                            }

                            PlayerUtil.writeSetting(sender, key, (boolean)args[1]);
                        })))
                .register();
    }
}
