package nws.lithiumdev.budplaza.software.mod;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import nws.lithiumdev.budplaza.software.BudPlazaEntry;
import nws.lithiumdev.budplaza.software.mod.util.ConfigUtil;
import org.apache.logging.log4j.Logger;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;

import java.text.DecimalFormat;

public final class Globals {
    private Globals() {}

    public static final String VERSION = "1.0-SNAPSHOT";
    public static final DecimalFormat HEALTH_FORMAT = new DecimalFormat("0.00");

    public static final Sound SOUND_EXP_PICKUP = Sound.sound(Key.key("entity.expierence_orb.pickuip"), Sound.Source.PLAYER, 1f, 1f);

    public static void reloadConfig() {
        BudPlazaEntry.getInstance().getConfig();
    }

    public static void initFileConfiguration(FileConfiguration config) {
        if (Globals.configuration == null) {
            Globals.configuration = config;
            ConfigUtil.initConfig();

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

    /**
     * Deprecated. Will be cleaned before release.
     * @deprecated Please use accessor & initializer.
     */
    @Deprecated(since = "1pre-20211223", forRemoval = true)
    public static final FileConfiguration config = null;

    /**
     * Deprecated. Will be cleaned before release.
     * @deprecated Get your own logger via {@link org.apache.logging.log4j.LogManager} instead. This will throw {@link NullPointerException}
     */
    @Deprecated(since = "1pre-20211223", forRemoval = true)
    public static final Logger logger = null;

    /**
     * Deprecated. Will be cleaned before release.
     * @deprecated You can easily get server via {@link org.bukkit.Bukkit}.
     */
    @Deprecated(since = "1pre-20211223", forRemoval = true)
    public static final Server server = null;
}
