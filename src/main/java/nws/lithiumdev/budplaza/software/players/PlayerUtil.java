package nws.lithiumdev.budplaza.software.players;

import nws.lithiumdev.budplaza.software.BudPlazaEntry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class PlayerUtil {
    private PlayerUtil() {}

    /**
     * @deprecated Deprecated. For hint use lists.
     */
    @Deprecated(since = "1pre-20211223", forRemoval = true)
    public static final String[] CONSTANT_PERFS = {
      "target.HitMessage"
    };

    public static final String METADATA_PREF_HEADER = "perf.";
    private static final String METADATA_HOME_X = "home-x";
    private static final String METADATA_HOME_Y = "home-y";
    private static final String METADATA_HOME_Z = "home-z";
    private static final String METADATA_HOME_DIM = "home-dim";

    private static final List<String> perfs = new ArrayList<>();

    /**
     * Plays a sound effect to the specified player.
     * @param player The player to blip.
     * @throws NullPointerException Thrown when player is null.
     */
    public static void blipPlayer(@NotNull Player player) {
        Objects.requireNonNull(player).playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
    }

    public static String[] getPreferenceOptions() {
        return perfs.toArray(new String[0]);
    }

    /**
     * Determines whether the specified perf was registered.
     * @param key The perf to check.
     * @return {@code true} if the perf was registered; otherwise, {@code false}.
     * @apiNote You can <i>still</i> set a perf even if it was <i>not registered</i>.
     * @throws NullPointerException Thrown if key was {@code null}.
     */
    @Contract(pure = true)
    public static boolean isPrefRegistered(@NotNull String key) {
        return perfs.contains(Objects.requireNonNull(key));
    }

    /**
     * Registers a perf to the perf directory.
     * @param key The key to register.
     * @throws NullPointerException Thrown if key was {@code null}.
     */
    public static void registerPref(@NotNull String key) {
        perfs.add(Objects.requireNonNull(key));
    }

    /**
     * Writes a setting to a specified player's metadata.
     * @param player The player to write.
     * @param key The key of the setting to write.
     * @param value The value.
     * @throws NullPointerException Thrown when player or the key is null.
     */
    public static void writeSetting(@NotNull Player player, @NotNull String key, boolean value) {
        Objects.requireNonNull(player)
                .setMetadata(METADATA_PREF_HEADER + Objects.requireNonNull(key), new FixedMetadataValue(BudPlazaEntry.getInstance(), value));
    }

    public static Location getHome(@NotNull Player player) {
        if (!player.hasMetadata(METADATA_HOME_X)
        || !player.hasMetadata(METADATA_HOME_Y)
        || !player.hasMetadata(METADATA_HOME_Z)
        || !player.hasMetadata(METADATA_HOME_DIM)) {
            return null;
        }

        var x = player.getMetadata(METADATA_HOME_X).get(0).asDouble();
        var y = player.getMetadata(METADATA_HOME_Y).get(0).asDouble();
        var z = player.getMetadata(METADATA_HOME_Z).get(0).asDouble();
        var dim = player.getMetadata(METADATA_HOME_DIM).get(0).asString();

        var world = Bukkit.getServer().getWorld(dim);

        return new Location(world, x, y, z);
    }

    public static void setHome(@NotNull Player player, @NotNull Location loc) {
        player.setMetadata(METADATA_HOME_X, new FixedMetadataValue(BudPlazaEntry.getInstance(), loc.getBlockX()));
        player.setMetadata(METADATA_HOME_Y, new FixedMetadataValue(BudPlazaEntry.getInstance(), loc.getBlockY()));
        player.setMetadata(METADATA_HOME_Z, new FixedMetadataValue(BudPlazaEntry.getInstance(), loc.getBlockZ()));
        player.setMetadata(METADATA_HOME_DIM, new FixedMetadataValue(BudPlazaEntry.getInstance(), loc.getWorld().getName()));
    }

    /**
     * Gets a setting.
     * @param player The player.
     * @param key The key of the setting.
     * @return If not exists or set as, true; if set as, 'false'.
     */
    public static boolean getSetting(@NotNull Player player, @NotNull String key) {
        if (!Objects.requireNonNull(player).hasMetadata(METADATA_PREF_HEADER + key)) {
            return true;
        }

        List<MetadataValue> data = player.getMetadata(METADATA_PREF_HEADER + key);

        for (MetadataValue dataItem:
             data) {
            if (dataItem.getOwningPlugin() instanceof BudPlazaEntry) {
                return dataItem.asBoolean();
            }
        }

        return true;
    }
}
