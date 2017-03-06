package com.wordpress.fabiohbarbosa.notifier.plugin.factory;

import com.wordpress.fabiohbarbosa.notifier.plugin.service.PluginLoadProperties;

public final class PluginLoadPropertiesFactory {
    private static PluginLoadProperties loadProperties;

    private PluginLoadPropertiesFactory() {
        // block constructor
    }

    public static PluginLoadProperties getInstance() {
        if (loadProperties == null) {
            loadProperties = new PluginLoadProperties();
        }
        return loadProperties;
    }
}
