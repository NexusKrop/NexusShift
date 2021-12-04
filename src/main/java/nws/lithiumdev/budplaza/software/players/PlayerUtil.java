package nws.lithiumdev.budplaza.software.players;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class PlayerUtil {
    private PlayerUtil() {}

    /**
     * Plays a sound effect to the specified player.
     * @param player The player to blip.
     * @throws NullPointerException Thrown when player is null.
     */
    public static void blipPlayer(@NotNull Player player) {
        Objects.requireNonNull(player).playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
    }
}
