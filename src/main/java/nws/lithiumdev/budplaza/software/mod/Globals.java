package nws.lithiumdev.budplaza.software.mod;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.apache.logging.log4j.Logger;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;

import java.text.DecimalFormat;

public final class Globals {
    private Globals() {}

    public static final String VERSION = "1.0-SNAPSHOT";
    public static final DecimalFormat HEALTH_FORMAT = new DecimalFormat("0.00");

    public static final Sound SOUND_EXP_PICKUP = Sound.sound(Key.key("entity.expierence_orb.pickuip"), Sound.Source.PLAYER, 1f, 1f);

    public static FileConfiguration config;
    public static Logger logger;
    public static Server server;
}
