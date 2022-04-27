// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod;

import io.github.nexuskrop.shift.NexusShift;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.configuration.file.FileConfiguration;

import java.text.DecimalFormat;

public final class Globals {
    private Globals() {}

    public static final String SOURCES_REPO = "https://gitlab.com/budplaza/budplaza-software-paper/";

    public static final Sound SOUND_EXP_PICKUP = Sound.sound(Key.key("entity.experience_orb.pickup"), Sound.Source.PLAYER, 1f, 1f);

    public static void reloadConfig() {
        configuration = NexusShift.getInstance().getConfig();
    }

    public static void initFileConfiguration(FileConfiguration config) {
        if (Globals.configuration == null) {
            Globals.configuration = config;

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
