// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.Bukkit;

import java.util.Arrays;

public class TpsCommand implements ICommand{

    @Override
    public void register() {
        new CommandAPICommand("tps")
                .withPermission("budplaza.commands.tps")
                .executes((sender, args) -> {
                    var tps = Bukkit.getServer().getTPS();
                    sender.sendMessage("TPS-es: " + Arrays.toString(tps));
                    return (int)tps[0];
                })
                .register();
    }
}
