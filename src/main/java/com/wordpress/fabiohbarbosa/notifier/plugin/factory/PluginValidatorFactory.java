package com.wordpress.fabiohbarbosa.notifier.plugin.factory;

import com.wordpress.fabiohbarbosa.notifier.plugin.service.PluginValidator;

public final class PluginValidatorFactory {
    private static PluginValidator validator;

    private PluginValidatorFactory() {
        // block constructor
    }

    public static PluginValidator getInstance() {
        if (validator == null) {
            validator = new PluginValidator();
        }
        return validator;
    }
}
