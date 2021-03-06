// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.players;

import io.github.nexuskrop.shift.NexusShift;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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
    private static final String DOUBLE_FORMAT_HEALTH = "%.2f";

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

    public static Component getDamageComponent(EntityDamageByEntityEvent event, LivingEntity living, AttributeInstance maxHealth) {
        // Calculates the correct health after damage
        var finHealth = living.getHealth() - event.getDamage();

        if (maxHealth == null) {
            throw new IllegalStateException("No MAX HEALTH?");
        }

        // Make sure dead entities get the correct health
        if (finHealth < 0) finHealth = 0;

        // Produces something like "Villager HP <current/max> [-12]
        return Component.text()
                .content(living.getName())
                .color(NamedTextColor.AQUA)
                .append(Component.text(" HP").color(NamedTextColor.LIGHT_PURPLE))
                .append(Component.text("<").color(NamedTextColor.WHITE))
                .append(Component.text(String.format(DOUBLE_FORMAT_HEALTH, finHealth)))
                .append(Component.text("/").color(NamedTextColor.RED))
                .append(Component.text(maxHealth.getValue()))
                .append(Component.text("> [").color(NamedTextColor.WHITE))
                .append(Component.text("-").color(NamedTextColor.GRAY))
                .append(Component.text(String.format(DOUBLE_FORMAT_HEALTH, event.getDamage())).color(NamedTextColor.DARK_RED))
                .append(Component.text(" ]").color((NamedTextColor.WHITE)))
                .build();
    }
}
