package nws.lithiumdev.budplaza.software.ui.util;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

/**
 * @deprecated Use methods in {@link Key}.
 */
@Deprecated(since = "1pre-20211217", forRemoval = true)
public class Identifier implements Key {
    public Identifier(@NotNull String namespace, @NotNull String value)
    {
        this.namespace = namespace;
        this.value = value;
    }

    private String namespace;
    private String value;

    @Override
    public @NotNull String namespace() {
        return namespace;
    }

    @Override
    public @NotNull String value() {
        return value;
    }

    @Override
    public @NotNull String asString() {
        return namespace + ":" + value;
    }
}
