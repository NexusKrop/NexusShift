/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift.util.init;

import io.github.nexuskrop.shift.ui.Messages;

public final class LoadingUtil {
    private LoadingUtil() {
        throw new IllegalStateException("No LoadingUtil instances for you!");
    }

    public static void addDefaultMessages() {
        // Bed Command
        Messages.addDefault("commands.bed.invalid_here", "<red>Your position cannot be set as your personal spawn point.</red>");
        Messages.addDefault("ui.copyright", "This plugin is licensed under <a:https://github.com/NexusKrop/NexusShift/blob/main/LICENSE>GNU AGPLv3 or later</a>.<br>- <b>Source Code</b>: <a:https://github.com/NexusKrop/NexusShift>https://github.com/NexusKrop/NexusShift/blob/main/LICENSE</a><br>- <b>Developer</b>: WithLithum");

        Messages.addDefault("ui.target.block", "Target");
        Messages.addDefault("generic.hp", "HP");
        Messages.addDefault("ui.welcome", "<green>Welcome!</green>");
        Messages.addDefault("reloadConfigDone", "<green>Configuration reloaded.</green>");
        Messages.addDefault("reloadConfig", "<gold>Reloading configuration...</gold>");
        Messages.addDefault("expected.subcommand", "Expected subcommand but found '%s'");
        Messages.addDefault("expected.subcommand_none", "Expected subcommand");
        Messages.addDefault("expected.nothing", "Expected nothing but found data");
        Messages.addDefault("commands.no_permission", "You do not have the permission to run this command");
        Messages.addDefault("version", "Running BudPlaza v1.0-SNAPSHOT");

        Messages.addDefault("ui.target.entity", "Target: ");
        Messages.addDefault("generic.dev.git_repo", "Git Repository");
        Messages.addDefault("ui.broadcast.shot_and_killed", "shot and killed");
        Messages.addDefault("ui.broadcast.killed_melee", "killed");

        // Commands
        Messages.addDefault("commands.blip.from_console", "Console operator wants your attention.");
        Messages.addDefault("commands.blip.from_player", " wants your attention.");
        Messages.addDefault("commands.perf.no_such_perf", "No such preference.");
        Messages.addDefault("commands.perf.no_such_perf", "Preference '{pref}' was set to '{value}'.");
        Messages.addDefault("commands.generic.requires_player", "You are '%s' but the command can only be executed by player.");
        Messages.addDefault("commands.home.no_home", "<red>You have no home set. Use '/sethome' to set your home point.</red>");
        Messages.addDefault("commands.bed.no_bed", "You have no personal spawn point, or it was blocked.");
        Messages.addDefault("commands.bed.stuck", "You are stuck in a block. Get out and try again.");
        Messages.addDefault("commands.tps.result", "TPS is %i");
        Messages.addDefault("commands.version.license", "Licensed Under: ");
        Messages.addDefault("commands.version.sources.prefix", "Get Sources: ");
        Messages.addDefault("commands.setspawn.success", "<green>Successfully set world spawn.</green>");
        Messages.addDefault("commands.rtp.in_vehicle", "<red>Leave your vehicle.</red>");

        // 帮助信息
        Messages.addDefault("commands.bed.help_short", "Gets or sets the location of your own respawn point.");
        Messages.addDefault("commands.bed.help_long", "Go to or manipulate your respawn point. Adding \"here\" after the command will set your respawn point.");

        Messages.addDefault("commands.blip.help_short", "Blips someone.");
        Messages.addDefault("commands.blip.help_long", "Displays a message in chat, and plays a ding to the specified player. Useful if you want to nag someone about some issues.");

        Messages.addDefault("commands.spawn.help_short", "Go to spawn.");
        Messages.addDefault("commands.spawn.help_long", "Go to the world spawn point.");

        Messages.addDefault("commands.tps.help_short", "Displays the TPS.");
        Messages.addDefault("commands.tps.help_long", "Displays three recent Ticks Per Second sample in the order of 1min, 5min and 15min.");

        Messages.addDefault("ui.hit_indicator", "<green><health></green>/<red><maxHealth></red> HP [<gold>-<damage><gray>]");
        Messages.addDefault("ui.death_indicator", "Target <gray><target></gray> [<red>DEAD</red>]");
        Messages.addDefault("ui.death_broadcast", "<red><prep></red> <gray><means></gray> <green><victim></green>");
        Messages.addDefault("ui.target_block_indicator", "<gold>Target</gold>: <aqua><strength></aqua>/<green><max></green>");
    }
}
