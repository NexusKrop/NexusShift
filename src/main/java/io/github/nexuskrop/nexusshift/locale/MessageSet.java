package io.github.nexuskrop.nexusshift.locale;

import com.destroystokyo.paper.utils.PaperPluginLogger;
import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public final class MessageSet {
    private static final Logger LOGGER = LogManager.getLogger("NS-MessageSet");

    private MessageSet() {
        throw new IllegalStateException("No MessageSet instances for you!");
    }

    private static HashMap<String, String> messages = new HashMap<>();

    public static void load() {
        var gson = new GsonBuilder().enableComplexMapKeySerialization().create();

        var type = new TypeToken<HashMap<String, String>>() {}.getType();

        try (var stream = MessageSet.class.getResourceAsStream("/message.json")) {
            assert stream != null;
            messages = gson.fromJson(new InputStreamReader(stream), type);
        } catch (IOException io) {
            LOGGER.error("Error while reading messages from resources", io);
        }
    }

    public static @Nullable String getOrNull(String key) {
        return messages.get(key);
    }

    public static @NotNull String get(String key) {
        var result = getOrNull(key);
        if (result == null) throw new IllegalArgumentException("No such key");
        return result;
    }
}
