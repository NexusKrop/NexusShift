/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift.ui;

import com.google.gson.Gson;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.ComponentSerializer;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;

/**
 * Provides utilities to convert Kyori Adventure's {@link Component}s to
 * Minecraft {@link net.minecraft.network.chat.Component}s.
 *
 * @author WithLithum
 * @since rev2-20220502
 * @see net.kyori.adventure.text.serializer.ComponentSerializer
 * @see net.minecraft.network.chat.Component.Serializer
 * @implNote This class converts a component to JSON before converting to a
 * Minecraft component, and vice versa. Speed may be a problem - if possible,
 * you may cache your results.
 */
public final class NativeComponentSerializer implements ComponentSerializer<Component, Component, net.minecraft.network.chat.Component> {
    private static final NativeComponentSerializer instance = new NativeComponentSerializer();
    private static final Gson gson = new Gson();

    /**
     * Gets an instance of this class. Multiple calls are guaranteed to return
     * the same instance.
     * @return An instance of this class.
     */
    public static NativeComponentSerializer nativeComponentSerializer() {
        return instance;
    }

    /**
     * Converts a native {@link net.minecraft.network.chat.Component} to a
     * Kyori Adventure {@link Component}.
     * @param input The native component to convert from.
     * @return The converted component.
     */
    @Override
    public @NotNull Component deserialize(net.minecraft.network.chat.@NotNull Component input) {
        var output = net.minecraft.network.chat.Component.Serializer.toJson(input);
        return GsonComponentSerializer.gson().deserialize(output);
    }

    /**
     * Converts a Kyori Adventure {@link Component} to native component.
     * @param component The adventure component.
     * @return The converted component.
     */
    @NotNull
    @Override
    public net.minecraft.network.chat.Component serialize(@NotNull Component component) {
        var output = GsonComponentSerializer.gson().serialize(component);
        var result = net.minecraft.network.chat.Component.Serializer.fromJson(output);

        if (result == null) {
            throw new IllegalStateException("FromJson returned null - check your args");
        }

        return result;
    }
}
