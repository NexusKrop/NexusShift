// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.util;

import io.gitlab.budplaza.calamity.plugin.config.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import nws.lithiumdev.budplaza.software.mod.Globals;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public final class ConfigUtil {
    private ConfigUtil() {}

    private static final HashMap<String, Component> parsed = new HashMap<>();

    public static void AddDefaultMessages() {
        // Bed Command
        AddDefaultMessage("commands.bed.invalid_here", "<red>Your position cannot be set as your personal spawn point.</red>");

        AddDefaultMessage("ui.target.block", "Target");
        AddDefaultMessage("generic.hp", "HP");
        AddDefaultMessage("messageGroups.welcome", "<green>Welcome!</green>");
        AddDefaultMessage("messageGroups.reloadConfigDone", "<green>Configuration reloaded.</green>");
        AddDefaultMessage("messageGroups.reloadConfig", "<gold>Reloading configuration...</gold>");
        AddDefaultMessage("expected.subcommand", "Expected subcommand but found '%s'");
        AddDefaultMessage("expected.subcommand_none", "Expected subcommand");
        AddDefaultMessage("expected.nothing", "Expected nothing but found data");
        AddDefaultMessage("commands.no_permission", "You do not have the permission to run this command");
        AddDefaultMessage("version", "Running BudPlaza v1.0-SNAPSHOT");
        AddDefaultMessage("commands.blip.from_console", "Console operator wants your attention.");
        AddDefaultMessage("commands.blip.from_player", " wants your attention.");
        AddDefaultMessage("commands.perf.no_such_perf", "No such preference.");
        AddDefaultMessage("commands.perf.no_such_perf", "Preference '{pref}' was set to '{value}'.");
        AddDefaultMessage("commands.generic.requires_player", "You are '%s' but the command can only be executed by player.");
        AddDefaultMessage("commands.home.no_home", "You have no home set. Use '/sethome' to set your home.");
        AddDefaultMessage("commands.bed.no_bed", "You have no personal spawn point, or it was blocked.");
        AddDefaultMessage("commands.bed.stuck", "You are stuck in a block. Get out and try again.");
        AddDefaultMessage("ui.target.entity", "Target: ");
        AddDefaultMessage("commands.tps.result", "TPS is %i");
        AddDefaultMessage("commands.version.license", "Licensed Under: ");
        AddDefaultMessage("commands.version.sources.prefix", "Get Sources: ");
        AddDefaultMessage("generic.dev.git_repo", "Git Repository");
        AddDefaultMessage("ui.broadcast.shot_and_killed", "shot and killed");
        AddDefaultMessage("ui.broadcast.killed_melee", "killed");

        // 帮助信息
        AddDefaultMessage("commands.bed.help_short", "Gets or sets the location of your own respawn point.");
        AddDefaultMessage("commands.bed.help_long", "Go to or manipulate your respawn point. Adding \"here\" after the command will set your respawn point.");

        AddDefaultMessage("commands.blip.help_short", "Blips someone.");
        AddDefaultMessage("commands.blip.help_long", "Displays a message in chat, and plays a ding to the specified player. Useful if you want to nag someone about some issues.");

        AddDefaultMessage("commands.spawn.help_short", "Go to spawn.");
        AddDefaultMessage("commands.spawn.help_long", "Go to the world spawn point.");

        AddDefaultMessage("commands.tps.help_short", "Displays the TPS.");
        AddDefaultMessage("commands.tps.help_long", "Displays three recent Ticks Per Second sample in the order of 1min, 5min and 15min.");

        AddDefaultMessage("commands.rtp.in_vehicle", "请先离开载具。");
    }

    private static void AddDefaultMessage(@NotNull String groupName, @Nullable String value) {
        Messages.AddDefault(groupName, value);
        Globals.addDefaultConfig("messageGroups." + groupName, value);
    }

    public static String getMessage(String groupName) {
        return Globals.getConfiguration().getString("messageGroups." + groupName);
    }

    public static Component getComponentMessage(String groupName) {
        return parsed.computeIfAbsent(groupName, v -> GsonComponentSerializer.gson().deserialize(getMessage(groupName)));
    }

    /**
     * Performs a full reload by clearing all parsed configurations, then reload the config file.
     */
    public static void reloadConfig() {
        parsed.clear();
        Globals.reloadConfig();
    }
}
