/*
 * Copyright (c) 2022. All rights reserved.
 * Licensed under GNU Affero GPL, either V3 or any later version.
 */

package io.gitlab.budplaza.calamity.plugin.config;

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
 * 提供有关消息组的读取、默认化和写入的方法。此类不可继承，并且不可实例化。
 */
public final class Messages {
    private static Properties properties;
    private static final Logger logger = LogManager.getLogger("CalamityMessages");

    private static Map<String, String> defaults = new HashMap<>();

    @Contract(" -> fail ")
    private Messages() {
        throw new IllegalStateException("汝可识得此类？汝可认得此类不可实例化？");
    }

    /**
     * 添加一对默认消息。
     * @param key 消息的标识符。
     * @param value 消息值。
     */
    public static void addDefault(@NotNull String key, @NotNull String value) {
        defaults.put(Objects.requireNonNull(key), Objects.requireNonNull(value));
    }

    public static String get(@NotNull String key) {
        return (String)properties.get(Objects.requireNonNull(key));
    }

    /**
     * 添加传入的全部默认消息。
     * @param entries 默认消息集。
     */
    public static void addDefaults(Map.Entry<String, String>[] entries) {
        for (var entry : Objects.requireNonNull(entries)) {
            defaults.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 加载消息配置文件。如果不存在，尝试创建新的配置文件，如果失败不再尝试。
     * @param configPath 欲存储配置文件的目录。
     * @throws NullArgumentException 如果传入的 configPath 为 {@code null} 即抛出。
     * @throws IllegalArgumentException 如果传入的 configPath 非目录或不存在即抛出。
     */
    public static void loadIn(@NotNull File configPath) {
        if (!Objects.requireNonNull(configPath).isDirectory()) {
            throw new IllegalArgumentException("传入的实参 configPath 非目录");
        }

        // 生成配置文件实例
        var config = new File(configPath, "messages.properties");
        if (properties == null) properties = new Properties();

        properties.putAll(defaults);

        if (config.isDirectory()) {
            logger.warn("配置文件的位置上是目录。");
            return;
        }

        // 如果需要创建新的文件
        if (!config.exists()) {

            // 存入默认值
            logger.info("消息配置文件不存在，正在创建");

            // 试图创建文件
            try {
                Files.createFile(Path.of(config.getAbsolutePath()));
            } catch (IOException ioe) {
                logger.error("未能创建消息配置文件：", ioe);
                // 使用默认消息，不再尝试保存默认消息
                return;
            }

            // 若上条未返回
            // TODO: 建立一个新方法来执行这种任务，并降低复杂度（可建议题）
            try (var stream = new FileOutputStream(config)) {
                properties.store(stream, "DEFAULT");
            } catch (FileNotFoundException fife) {
                logger.warn("File not found caught: ", fife);
            } catch (IOException ioe) {
                logger.error("IOException: ", ioe);
            }
        } else {
            // 直接读取
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
