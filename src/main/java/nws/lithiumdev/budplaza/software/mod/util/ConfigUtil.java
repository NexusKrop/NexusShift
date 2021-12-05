package nws.lithiumdev.budplaza.software.mod.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import nws.lithiumdev.budplaza.software.BudPlazaEntry;
import nws.lithiumdev.budplaza.software.mod.Globals;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public final class ConfigUtil {
    private ConfigUtil() {}

    private static final HashMap<String, Component> parsed = new HashMap<>();

    public static void initConfig() {
        Globals.config.addDefault("messageGroups.target_block_summary", "Target");
        Globals.config.addDefault("messageGroups.hp", "HP");
        Globals.config.addDefault("messageGroups.welcome", "{\"text\":\"Welcome!\", \"color\":\"green\"}");
        Globals.config.addDefault("messageGroups.reloadConfigDone", "{\"text\":\"Configuration reloaded.\", \"color\":\"green\"}");
        Globals.config.addDefault("messageGroups.reloadConfig", "{\"text\":\"Reloading config, please wait...\", \"color\":\"gold\"}");
        addDefaultMessage("expected.subcommand", "Expected subcommand but found '%s'");
        addDefaultMessage("expected.subcommand_none", "Expected subcommand");
        addDefaultMessage("expected.nothing", "Expected nothing but found data");
        addDefaultMessage("commands.no_permission", "You do not have the permission to run this command");
    }

    private static void addDefaultMessage(@NotNull String groupName, @Nullable String value) {
        Globals.config.addDefault("messageGroups." + groupName, value);
    }

    public static String getMessage(String groupName) {
        return Globals.config.getString("messageGroups." + groupName);
    }

    public static Component getComponentMessage(String groupName) {
        if (!parsed.containsKey(groupName)) {
            parsed.put(groupName, GsonComponentSerializer.gson().deserialize(getMessage(groupName)));
        }

        return parsed.get(groupName);
    }

    public static void reloadConfig() {
        parsed.clear();

        BudPlazaEntry.INSTANCE.reloadConfig();
        Globals.config = BudPlazaEntry.INSTANCE.getConfig();
    }
}
