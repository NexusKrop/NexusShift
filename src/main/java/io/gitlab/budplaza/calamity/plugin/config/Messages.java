/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.gitlab.budplaza.calamity.plugin.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.apache.commons.lang.NullArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Provide the reading and writing of the message configurations.
 */
public final class Messages {
    private static Properties properties;
    private static final Logger logger = LogManager.getLogger("CalamityMessages");
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    private static final Map<String, String> defaults = new HashMap<>();

    @Contract(" -> fail ")
    private Messages() {
        throw new IllegalStateException("DID YOU KNOW THAT, FOR THIS PARTICULAR THING, IT IS SO THAT NOT TO BE INSTANTIATED.");
    }

    /**
     * Adds a default message to the message configuration.
     * @param key The identifier of the message.
     * @param value The message text.
     */
    public static void addDefault(@NotNull String key, @NotNull String value) {
        defaults.put(Objects.requireNonNull(key), Objects.requireNonNull(value));
    }

    public static String get(@NotNull String key) {
        return (String)properties.get(Objects.requireNonNull(key));
    }

    public static Component getParsed(@NotNull String key)
    {
        return miniMessage.deserialize(get(key));
    }

    /**
     * Add all messages specified.
     * @param entries Default message sets.
     */
    public static void addDefaults(Map.Entry<String, String>[] entries) {
        for (var entry : Objects.requireNonNull(entries)) {
            defaults.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Loads the message configuration from a configuration file.
     * @param configPath The path stores the configuration.
     * @throws NullArgumentException Thrown if the configPath is {@code null}.
     * @throws IllegalArgumentException Thrown if configPath does not exist or if it is not a directory.
     */
    public static void loadMessages(@NotNull File configPath) {
        if (!Objects.requireNonNull(configPath).isDirectory()) {
            throw new IllegalArgumentException("传入的实参 configPath 非目录");
        }

        // instantiate
        var config = new File(configPath, "messages.properties");
        if (properties == null) properties = new Properties();

        properties.putAll(defaults);

        if (config.isDirectory()) {
            logger.warn("Configuration is a directory.");
            return;
        }

        // Create new file if necessary
        if (!config.exists()) {

            // Create a default file
            logger.info("Message configuration file does not exist. Creating a new one.");

            // Create
            try {
                Files.createFile(Path.of(config.getAbsolutePath()));
            } catch (IOException ioe) {
                logger.error("Failed to create default file: ", ioe);
                return;
            }

            // If not returned
            // TODO: New method for this
            try (var stream = new FileOutputStream(config)) {
                properties.store(stream, "DEFAULT");
            } catch (FileNotFoundException fife) {
                logger.warn("File not found caught: ", fife);
            } catch (IOException ioe) {
                logger.error("IOException: ", ioe);
            }
        } else {
            // Read
            try (var stream = new FileInputStream(config)) {
                properties.load(stream);
            } catch (FileNotFoundException fife) {
                logger.warn("File not found caught: ", fife);
            } catch (IOException ioe) {
                logger.error("IOException: ", ioe);
            }
        }
    }
}
