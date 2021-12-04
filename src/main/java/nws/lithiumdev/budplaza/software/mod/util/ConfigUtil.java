package nws.lithiumdev.budplaza.software.mod.util;

import nws.lithiumdev.budplaza.software.mod.Globals;

public final class ConfigUtil {
    private ConfigUtil() {}

    public static void initConfig() {
        Globals.config.addDefault("messageGroups.target_block_summary", "Target");
        Globals.config.addDefault("messageGroups.hp", "HP");
    }

    public static String getMessage(String groupName) {
        return Globals.config.getString("messageGroups." + groupName);
    }

}
