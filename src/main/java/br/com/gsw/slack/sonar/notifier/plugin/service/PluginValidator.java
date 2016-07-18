package br.com.gsw.slack.sonar.notifier.plugin.service;

import br.com.gsw.slack.sonar.notifier.slack.model.Slack;
import br.com.gsw.slack.sonar.notifier.sonar.model.Sonar;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class PluginValidator {
    public void sonar(final Sonar sonar) {
        if (sonar == null) {
            throw new IllegalArgumentException("Plugin require sonar property");
        }
        if (isEmpty(sonar.getKey())) {
            throw new IllegalArgumentException("Plugin require sonar.key property");
        }
        if (isEmpty(sonar.getUrl())) {
            throw new IllegalArgumentException("Plugin require sonar.url property");
        }
    }

    public void slack(final Slack slack) {
        if (slack == null) {
            throw new IllegalArgumentException("Plugin require slack property");
        }
        if (isEmpty(slack.getWebhook())) {
            throw new IllegalArgumentException("Plugin require slack.webhook property");
        }
    }
}
