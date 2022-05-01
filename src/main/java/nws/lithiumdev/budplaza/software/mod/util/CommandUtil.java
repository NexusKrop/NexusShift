// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

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

    /**
     * Sends a message to the specified {@link CommandSender} regarding the specified command fault.
     * @param sender The target of message.
     * @param text The text to send.
     * @throws NullPointerException Thrown when sender was null.
     */
    public static void feedbackFault(@NotNull CommandSender sender, String text) {
        Objects.requireNonNull(sender).sendMessage(Component.text(Objects.requireNonNull(text)).color(NamedTextColor.RED));
    }
}
