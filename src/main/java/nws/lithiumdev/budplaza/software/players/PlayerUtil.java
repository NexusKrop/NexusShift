package nws.lithiumdev.budplaza.software.players;

import nws.lithiumdev.budplaza.software.BudPlazaEntry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class PlayerUtil {
    private PlayerUtil() {}

    public static final String[] CONSTANT_PERFS = {
      "target.HitMessage"
    };

    public static final ArrayList<String> perfs = new ArrayList<>();

    /**
     * Plays a sound effect to the specified player.
     * @param player The player to blip.
     * @throws NullPointerException Thrown when player is null.
     */
    public static void blipPlayer(@NotNull Player player) {
        Objects.requireNonNull(player).playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
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
                .setMetadata("perf." + Objects.requireNonNull(key), new FixedMetadataValue(BudPlazaEntry.INSTANCE, value));
    }

    public static Location getHome(@NotNull Player player) {
        if (!player.hasMetadata("home-x")
        || !player.hasMetadata("home-y")
        || !player.hasMetadata("home-z")
        || !player.hasMetadata("home-dim")) {
            return null;
        }

        var x = player.getMetadata("home-x").get(0).asDouble();
        var y = player.getMetadata("home-y").get(0).asDouble();
        var z = player.getMetadata("home-z").get(0).asDouble();
        var dim = player.getMetadata("home-dim").get(0).asString();

        var world = Bukkit.getServer().getWorld(dim);

        return new Location(world, x, y, z);
    }

    public static void setHome(@NotNull Player player, @NotNull Location loc) {
        player.setMetadata("home-x", new FixedMetadataValue(BudPlazaEntry.INSTANCE, loc.getBlockX()));
        player.setMetadata("home-y", new FixedMetadataValue(BudPlazaEntry.INSTANCE, loc.getBlockY()));
        player.setMetadata("home-z", new FixedMetadataValue(BudPlazaEntry.INSTANCE, loc.getBlockZ()));
        player.setMetadata("home-dim", new FixedMetadataValue(BudPlazaEntry.INSTANCE, loc.getWorld().getName()));
    }

    /**
     * Gets a setting.
     * @param player The player.
     * @param key The key of the setting.
     * @return If not exists or set as, true; if set as, 'false'.
     */
    public static boolean getSetting(@NotNull Player player, @NotNull String key) {
        if (!Objects.requireNonNull(player).hasMetadata("perf." + key)) {
            return true;
        }

        List<MetadataValue> data = player.getMetadata("perf." + key);

        for (MetadataValue dataItem:
             data) {
            if (dataItem.getOwningPlugin() instanceof BudPlazaEntry) {
                return dataItem.asBoolean();
            }
        }

        return true;
    }
}
