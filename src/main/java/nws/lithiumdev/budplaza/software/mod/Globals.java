package nws.lithiumdev.budplaza.software.mod;

import org.apache.logging.log4j.Logger;
import org.bukkit.configuration.file.FileConfiguration;

import java.text.DecimalFormat;

public final class Globals {
    private Globals() {}

    public static final String VERSION = "1.0-SNAPSHOT";
    public static final DecimalFormat HEALTH_FORMAT = new DecimalFormat("0.00");

    public static FileConfiguration config;
    public static Logger logger;
}
