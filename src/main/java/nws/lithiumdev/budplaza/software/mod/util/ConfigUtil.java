package nws.lithiumdev.budplaza.software.mod.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import nws.lithiumdev.budplaza.software.mod.Globals;

import java.util.HashMap;

public final class ConfigUtil {
    private ConfigUtil() {}

    private static final HashMap<String, Component> parsed = new HashMap<>();

    public static void initConfig() {
        Globals.config.addDefault("messageGroups.target_block_summary", "Target");
        Globals.config.addDefault("messageGroups.hp", "HP");
        Globals.config.addDefault("messageGroups.welcome", "{\"text\":\"Welcome!\", \"color\":\"green\"}");
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
}
