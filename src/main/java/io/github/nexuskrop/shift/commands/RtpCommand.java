/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift.commands;

import dev.jorel.commandapi.CommandAPICommand;
import io.github.nexuskrop.shift.ui.Messages;
import net.kyori.adventure.text.Component;
import nws.lithiumdev.budplaza.software.mod.commands.definitions.ICommand;
import org.bukkit.Location;

import java.util.Random;

/**
 * Provides implementation of the <c>rtp</c> command。
 */
public class RtpCommand implements ICommand {
    private static final Random random = new Random();
    private final Component leaveVehicleWarning = Messages.getParsed("commands.rtp.in_vehicle");

    @Override
    public void register() {
        new CommandAPICommand("rtp")
                .withPermission("calamity.commands.rtp")
                .withHelp("随机传送。", "随机传送执行者至世界的任何地方。如果执行时正在骑乘载具则失败。")
                .executesEntity(((sender, args) -> {
                    if (sender.isInsideVehicle()) {
                        // 执行失败
                        sender.sendMessage(leaveVehicleWarning);
                        return -1;
                    }

                    var orig = sender.getLocation();

                    // 取原位置
                    var x = orig.getX() + random.nextDouble(-1230, 1230.5);
                    var z = orig.getZ() + random.nextDouble(-1230, 1230.1);

                    // 取该二维坐标处最高可站方块
                    var y = sender.getWorld().getHighestBlockYAt((int)x, (int)z);

                    // 合成Location，传送
                    var loc = new Location(sender.getWorld(), x, y, z);
                    sender.teleport(loc);
                    return 0;
                }))
                .register();
    }
}
