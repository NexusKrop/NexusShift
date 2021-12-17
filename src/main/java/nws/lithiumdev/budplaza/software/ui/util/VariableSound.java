package nws.lithiumdev.budplaza.software.ui.util;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.sound.SoundStop;
import nws.lithiumdev.budplaza.software.mod.Globals;
import org.apache.commons.lang.NullArgumentException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A pre-made class to play sound based on Kyori Adventures library.
 * @author WithLithum
 * @see net.kyori.adventure.sound.Sound
 * @see nws.lithiumdev.budplaza.software.ui.util.Identifier
 */
@Deprecated
public class VariableSound implements Sound {
    /**
     * Initializes a new instance of the <b>VariableSound</b> class.
     * @param id The sound identifier.
     * @param source The sound source.
     */
    @Contract(pure = true)
    public VariableSound(@NotNull Identifier id, @NotNull Source source)
    {
        this(id, source, 1, 1);
    }

    /**
     * Initializes a new instance of the <b>VariableSound</b> class.
     * @param id The sound identifier.
     * @param source The sound source.
     * @param volume The volume.
     * @param pitch The pitch of the sound.
     * @apiNote You should probably use parameters you would use when you are using the vanilla <c>playsound</c> command.
     */
    @Contract(pure = true)
    public VariableSound(@NotNull Identifier id, @NotNull Source source, float volume, float pitch) {
        if (id == null) {
            throw new NullArgumentException("id");
        }

        this.identifier = id;
        this.source = source;
        this.volume = volume;
        this.pitch = pitch;

        Globals.logger.debug("Created variable sound: " + this.identifier.asString());
    }

    /**
     * Initializes a new instance of the <b>VariableSound</b> class.
     * @param bukkitSound The sound.
     * @apiNote Pitch and volume will be 1.
     */
    public VariableSound(@NotNull org.bukkit.Sound bukkitSound) {
        this(new Identifier(bukkitSound.getKey().getKey(), bukkitSound.getKey().value()), Source.PLAYER);
    }

    private Identifier identifier;
    private Source source;
    private float volume;
    private float pitch;

    @Override
    public @NotNull Key name() {
        return identifier;
    }

    @Override
    public @NotNull Source source() {
        return source;
    }

    @Override
    public float volume() {
        return volume;
    }

    @Override
    public float pitch() {
        return pitch;
    }

    @Override
    public @NotNull SoundStop asStop() {
        return new SoundStop() {
            @Override
            public @Nullable Key sound() {
                return identifier;
            }

            @Override
            public @Nullable Source source() {
                return source;
            }
        };
    }
}
