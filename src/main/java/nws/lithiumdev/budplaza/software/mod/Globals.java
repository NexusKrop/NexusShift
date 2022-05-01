// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod;

import io.github.nexuskrop.shift.NexusShift;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.configuration.file.FileConfiguration;

public final class Globals {
    private Globals() {}

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
