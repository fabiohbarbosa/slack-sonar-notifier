package br.com.gsw.slack.sonar.notifier.plugin.service;

import br.com.gsw.slack.sonar.notifier.plugin.factory.LogFactory;
import br.com.gsw.slack.sonar.notifier.slack.model.Slack;
import br.com.gsw.slack.sonar.notifier.sonar.model.Sonar;
import org.apache.maven.plugin.logging.Log;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class PluginValidator {
    private static final Log LOGGER = LogFactory.getInstance();

    public void sonar(final Sonar sonar) {
        LOGGER.debug("Plugin validator sonar...");
        if (sonar == null) {
            final String errorMessage = "Plugin require sonar property";
            LOGGER.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (isEmpty(sonar.getKey())) {
            final String errorMessage = "Plugin require sonar.key property";
            LOGGER.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (isEmpty(sonar.getUrl())) {
            final String errorMessage = "Plugin require sonar.url or sonar.host.url property";
            LOGGER.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public void slack(final Slack slack) {
        LOGGER.debug("Plugin validator slack...");
        if (slack == null) {
            final String errorMessage = "Plugin require slack property";
            LOGGER.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (isEmpty(slack.getWebhook())) {
            final String errorMessage = "Plugin require slack.webhook property";
            LOGGER.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
