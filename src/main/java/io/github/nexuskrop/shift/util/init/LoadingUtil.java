/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift.util.init;

import io.gitlab.budplaza.calamity.plugin.config.Messages;

public final class LoadingUtil {
    private LoadingUtil() {
        throw new IllegalStateException("No LoadingUtil instances for you!");
    }

    public static void addDefaultMessages() {
        // Bed Command
        Messages.AddDefault("commands.bed.invalid_here", "<red>Your position cannot be set as your personal spawn point.</red>");
        Messages.AddDefault("ui.copyright", "This plugin is licensed under <a:https://github.com/NexusKrop/NexusShift/blob/main/LICENSE>GNU AGPLv3 or later</a>.<br>- <b>Source Code</b>: <a:https://github.com/NexusKrop/NexusShift>https://github.com/NexusKrop/NexusShift/blob/main/LICENSE</a><br>- <b>Developer</b>: WithLithum");

        Messages.AddDefault("ui.target.block", "Target");
        Messages.AddDefault("generic.hp", "HP");
        Messages.AddDefault("ui.welcome", "<green>Welcome!</green>");
        Messages.AddDefault("reloadConfigDone", "<green>Configuration reloaded.</green>");
        Messages.AddDefault("reloadConfig", "<gold>Reloading configuration...</gold>");
        Messages.AddDefault("expected.subcommand", "Expected subcommand but found '%s'");
        Messages.AddDefault("expected.subcommand_none", "Expected subcommand");
        Messages.AddDefault("expected.nothing", "Expected nothing but found data");
        Messages.AddDefault("commands.no_permission", "You do not have the permission to run this command");
        Messages.AddDefault("version", "Running BudPlaza v1.0-SNAPSHOT");
        Messages.AddDefault("commands.blip.from_console", "Console operator wants your attention.");
        Messages.AddDefault("commands.blip.from_player", " wants your attention.");
        Messages.AddDefault("commands.perf.no_such_perf", "No such preference.");
        Messages.AddDefault("commands.perf.no_such_perf", "Preference '{pref}' was set to '{value}'.");
        Messages.AddDefault("commands.generic.requires_player", "You are '%s' but the command can only be executed by player.");
        Messages.AddDefault("commands.home.no_home", "You have no home set. Use '/sethome' to set your home.");
        Messages.AddDefault("commands.bed.no_bed", "You have no personal spawn point, or it was blocked.");
        Messages.AddDefault("commands.bed.stuck", "You are stuck in a block. Get out and try again.");
        Messages.AddDefault("ui.target.entity", "Target: ");
        Messages.AddDefault("commands.tps.result", "TPS is %i");
        Messages.AddDefault("commands.version.license", "Licensed Under: ");
        Messages.AddDefault("commands.version.sources.prefix", "Get Sources: ");
        Messages.AddDefault("generic.dev.git_repo", "Git Repository");
        Messages.AddDefault("ui.broadcast.shot_and_killed", "shot and killed");
        Messages.AddDefault("ui.broadcast.killed_melee", "killed");

        // 帮助信息
        Messages.AddDefault("commands.bed.help_short", "Gets or sets the location of your own respawn point.");
        Messages.AddDefault("commands.bed.help_long", "Go to or manipulate your respawn point. Adding \"here\" after the command will set your respawn point.");

        Messages.AddDefault("commands.blip.help_short", "Blips someone.");
        Messages.AddDefault("commands.blip.help_long", "Displays a message in chat, and plays a ding to the specified player. Useful if you want to nag someone about some issues.");

        Messages.AddDefault("commands.spawn.help_short", "Go to spawn.");
        Messages.AddDefault("commands.spawn.help_long", "Go to the world spawn point.");

        Messages.AddDefault("commands.tps.help_short", "Displays the TPS.");
        Messages.AddDefault("commands.tps.help_long", "Displays three recent Ticks Per Second sample in the order of 1min, 5min and 15min.");

        Messages.AddDefault("commands.rtp.in_vehicle", "请先离开载具。");
        Messages.AddDefault("ui.hit_indicator", "<green><health></green>/<red><maxHealth></red> HP [<gold>-<damage><gray>]");
        Messages.AddDefault("ui.death_indicator", "Target <gray><target></gray> [<red>DEAD</red>]");
        Messages.AddDefault("ui.death_broadcast", "<red><prep></red> <gray><means></gray> <green><victim></green>");
        Messages.AddDefault("ui.target_block_indicator", "<gold>Target</gold>: <aqua><strength></aqua>/<green><max></green>");
    }
}
