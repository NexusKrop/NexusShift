package io.github.nexuskrop.nexusshift.command.exceptions;

import org.jetbrains.annotations.NotNull;

public class CommandExecutionException extends Exception{
    public CommandExecutionException(@NotNull String message) {
        super(message);
    }
}
