package io.github.nexuskrop.nexusshift.game;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.entity.Player;

public final class PlayerUtil {
    private PlayerUtil() {}

    private static final Sound blipSound = Sound.sound(Key.key("minecraft:entity.experience_orb.pickup"), Sound.Source.MASTER,
            1f, 1f);

    public static void blipPlayer(Player player) {
        player.playSound(blipSound);
    }
}
