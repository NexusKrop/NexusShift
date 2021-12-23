package nws.lithiumdev.budplaza.software.mod.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class CommandUtil {
    /**
     * Throws an exception.
     * @throws IllegalStateException Always throw.
     */
    @Contract(value = " -> fail", pure = true)
    private CommandUtil() {
        throw new IllegalStateException("No CommandUtil instances for you!");
    }

    private static final String EXPECTED_NOTHING_MESSAGE = "expected.nothing";

    /**
     * Sends a message to the specified {@link CommandSender} regarding passing a value to a command while the command expects nothing in it's position.
     * @param sender The target of message.
     * @throws NullPointerException Thrown when sender was null.
     */
    public static void feedbackExpectNothing(@NotNull CommandSender sender) {
        Objects.requireNonNull(sender).sendMessage(getRedTextComponent(ConfigUtil.getMessage(EXPECTED_NOTHING_MESSAGE)));
    }

    public static void feedbackNoPermission(@NotNull CommandSender sender) {
        feedbackFault(sender, ConfigUtil.getMessage("commands.no_permission"));
    }

    /**
     * Sends a message to the specified {@link CommandSender} regarding the specified command fault.
     * @param sender The target of message.
     * @param text The text to send.
     * @throws NullPointerException Thrown when sender was null.
     */
    public static void feedbackFault(@NotNull CommandSender sender, String text) {
        Objects.requireNonNull(sender).sendMessage(getRedTextComponent(text));
    }


    /**
     * Gets a {@link Component} with the specified string, and red color.
     * @param text The text to display.
     * @return An instance of {@link Component} with red color representing the text specified.
     * @throws NullPointerException Thrown when text is null.
     */
    @NotNull
    public static Component getRedTextComponent(@NotNull String text) {
        return Component.text(Objects.requireNonNull(text)).color(NamedTextColor.RED);
    }
}
