package br.com.gsw.slack.sonar.notifier.plugin.factory;

import br.com.gsw.slack.sonar.notifier.plugin.service.PluginValidator;

public class PluginValidatorFactory {
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
