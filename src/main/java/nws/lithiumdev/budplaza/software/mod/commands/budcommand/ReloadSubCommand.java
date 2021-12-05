package nws.lithiumdev.budplaza.software.mod.commands.budcommand;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadSubCommand implements ISubCommand {

    @Override
    public void onCommand(CommandSender player, Command command, String[] args) {
        player.sendMessage(ConfigUtil.getMessage("reloadConfig"));

        ConfigUtil.reloadConfig();
        player.sendMessage(ConfigUtil.getMessage("reloadConfigDone"));
    }

    @Override
    public String getPermission() {
        return "budplaza.commands.reload";
    }
}
