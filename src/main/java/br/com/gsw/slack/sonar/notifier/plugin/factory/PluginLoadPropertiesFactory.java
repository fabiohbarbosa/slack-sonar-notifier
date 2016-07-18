package br.com.gsw.slack.sonar.notifier.plugin.factory;

import br.com.gsw.slack.sonar.notifier.plugin.service.PluginLoadProperties;

public class PluginLoadPropertiesFactory {
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
