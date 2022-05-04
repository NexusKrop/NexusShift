/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.github.nexuskrop.shift.locale

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.github.nexuskrop.shift.ui.Messages
import io.github.nexuskrop.shift.ui.NativeComponentSerializer
import io.github.nexuskrop.shift.util.Common
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.TextComponent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

object MessageSet {
    private val mapGson : Gson = GsonBuilder().enableComplexMapKeySerialization().create()
    private val logger : Logger = LogManager.getLogger("Nexus-MessageSet")
    private var messages : HashMap<String, String> = HashMap<String, String>()

    init {
        throw IllegalStateException("No CommandEngine instances for you!")
    }

    /**
     * Loads the messages from the
     */
    @JvmStatic
    fun loadDefault() {
        loadFromStream(this.javaClass.getResourceAsStream("/messages.json"))
        applyCustomisationFile()
    }

    private fun applyCustomisationFile() {
        val file = File(Common.getDataFolder(), "messages.json")

        if (!file.exists()) {
            logger.info("No customisation file")
            return
        }

        val stream = file.inputStream();

        try {
            val result = mapGson.fromJson<Map<String, String>>(InputStreamReader(stream, StandardCharsets.UTF_8),
                    object : TypeToken<Map<String, String>>(){}.type)
            stream.close()

            if (result.isEmpty()) return

            for ((key,value) in result) {
                messages[key] = value;
            }
        } catch (ex : Exception) {
            logger.warn("Failed to read message customisation file:", ex)
        }
    }

    @JvmStatic
    fun get(key : String) : String {
        return messages[key] ?: key
    }

    @JvmStatic
    fun getMini(key : String) : Component {
        val orig = get(key)

        return if (orig == key) TextComponent(key)
        else {
            NativeComponentSerializer.nativeComponentSerializer().serialize(
                    MiniMessage.miniMessage().deserialize(orig)
            )
        }
    }

    private fun loadFromStream(stream : InputStream?) {
        if (stream == null) {
            logger.warn("Null stream, will use default")
            return
        }

        try {
            val result = mapGson.fromJson<HashMap<String, String>>(InputStreamReader(stream, StandardCharsets.UTF_8),
                    object : TypeToken<HashMap<String, String>>(){}.type)
            stream.close()
            messages = result
        } catch (ex : Exception) {
            logger.error("Failed to read resources:", ex)
        }
    }
}