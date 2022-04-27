// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.util;

import io.gitlab.budplaza.calamity.plugin.config.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import nws.lithiumdev.budplaza.software.mod.Globals;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public final class ConfigUtil {
    private ConfigUtil() {}

    private static final HashMap<String, Component> parsed = new HashMap<>();

    /**
     * Performs a full reload by clearing all parsed configurations, then reload the config file.
     */
    public static void reloadConfig() {
        parsed.clear();
        Globals.reloadConfig();
    }
}
