package io.github.nexuskrop.nexusshift.command.exceptions;

import org.jetbrains.annotations.NotNull;

public class CommandSyntaxException extends Exception {
    public CommandSyntaxException(@NotNull String message) {
        super(message);
    }
}
