package io.github.nexuskrop.nexusshift.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

public final class SenderUtil {
    private SenderUtil() {
        throw new IllegalStateException("No SenderUtil instances for you!");
    }

    /**
     * Sends a message to the specified {@link CommandSender} to notice about a
     * recent failure.
     * @param sender The sender.
     * @param message The message to send.
     */
    public static void sendFailure(CommandSender sender, String message) {
        sender.sendMessage(Component.text(message).color(NamedTextColor.RED));
    }
}
