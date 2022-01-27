// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import nws.lithiumdev.budplaza.software.mod.Globals;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public final class ConfigUtil {
    private ConfigUtil() {}

    private static final HashMap<String, Component> parsed = new HashMap<>();

    public static void initConfig() {
        Globals.addDefaultConfig("messageGroups.target_block_summary", "Target");
        Globals.addDefaultConfig("messageGroups.hp", "HP");
        Globals.addDefaultConfig("messageGroups.welcome", "{\"text\":\"Welcome!\", \"color\":\"green\"}");
        Globals.addDefaultConfig("messageGroups.reloadConfigDone", "{\"text\":\"Configuration reloaded.\", \"color\":\"green\"}");
        Globals.addDefaultConfig("messageGroups.reloadConfig", "{\"text\":\"Reloading config, please wait...\", \"color\":\"gold\"}");
        addDefaultMessage("expected.subcommand", "Expected subcommand but found '%s'");
        addDefaultMessage("expected.subcommand_none", "Expected subcommand");
        addDefaultMessage("expected.nothing", "Expected nothing but found data");
        addDefaultMessage("commands.no_permission", "You do not have the permission to run this command");
        addDefaultMessage("version", "Running BudPlaza v1.0-SNAPSHOT");
        addDefaultMessage("commands.blip.from_console", "Console operator wants your attention.");
        addDefaultMessage("commands.blip.from_player", " wants your attention.");
        addDefaultMessage("commands.perf.no_such_perf", "No such preference.");
        addDefaultMessage("commands.perf.no_such_perf", "Preference '%s' was set to '%b'.");
        addDefaultMessage("commands.generic.requires_player", "You are '%s' but the command can only be executed by player.");
        addDefaultMessage("commands.home.no_home", "You have no home set. Use '/sethome' to set your home.");
        addDefaultMessage("commands.bed.no_bed", "You have no personal spawn point, or it was blocked.");
        addDefaultMessage("commands.bed.stuck", "You are stuck in a block. Get out and try again.");
        addDefaultMessage("ui.target.entity", "Target: ");
        addDefaultMessage("commands.tps.result", "TPS is %i");
        addDefaultMessage("commands.version.license", "Licensed Under: ");
        addDefaultMessage("commands.version.sources.prefix", "Get Sources: ");
        addDefaultMessage("generic.dev.git_repo", "Git Repository");
    }

    private static void addDefaultMessage(@NotNull String groupName, @Nullable String value) {
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
