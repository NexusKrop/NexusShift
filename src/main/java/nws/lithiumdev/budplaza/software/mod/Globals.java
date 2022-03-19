// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import nws.lithiumdev.budplaza.software.BudPlazaEntry;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import org.bukkit.configuration.file.FileConfiguration;

import java.text.DecimalFormat;

public final class Globals {
    private Globals() {}

    public static final String VERSION = "1.0-SNAPSHOT";
    public static final DecimalFormat HEALTH_FORMAT = new DecimalFormat("0.00");
    public static final String SOURCES_REPO = "https://gitlab.com/budplaza/budplaza-software-paper/";

    public static final Sound SOUND_EXP_PICKUP = Sound.sound(Key.key("entity.experience_orb.pickup"), Sound.Source.PLAYER, 1f, 1f);

    public static void reloadConfig() {
        configuration = BudPlazaEntry.getInstance().getConfig();
    }

    public static void initFileConfiguration(FileConfiguration config) {
        if (Globals.configuration == null) {
            Globals.configuration = config;
            ConfigUtil.AddDefaultMessages();

        } else {
            throw new IllegalStateException("Config was ALREADY set!");
        }
    }

    /**
     * Adds a config to the default configuration.
     * @param path The path.
     * @param value The value.
     */
    public static void addDefaultConfig(String path, String value) {
        configuration.addDefault(path, value);
    }

    public static FileConfiguration getConfiguration() {
        return configuration;
    }

    private static FileConfiguration configuration;
}
