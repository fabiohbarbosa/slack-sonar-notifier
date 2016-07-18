package br.com.gsw.slack.sonar.notifier.plugin.service;

import br.com.gsw.slack.sonar.notifier.plugin.factory.LogFactory;
import br.com.gsw.slack.sonar.notifier.slack.model.Slack;
import br.com.gsw.slack.sonar.notifier.sonar.model.Sonar;
import org.apache.maven.plugin.logging.Log;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class PluginLoadProperties {
    private static final Log LOGGER = LogFactory.getInstance();

    public Sonar sonar(Sonar sonar) {
        if (sonar == null) {
            sonar = new Sonar();
        }
        final Sonar sonarProp = new Sonar(sonar.getKey(), sonar.getUrl(), sonar.getUser(),sonar.getPassword());
        LOGGER.debug("Sonar properties");
        LOGGER.debug(sonarProp.toString());

        if (isEmpty(sonarProp.getKey())) {
            LOGGER.debug(String.format("Load %s from property", "sonar.key"));
            final String property = System.getProperty("sonar.key");
            if (!isEmpty(property)) {
                sonarProp.setKey(property);
            }
        }
        if (isEmpty(sonarProp.getUrl())) {
            LOGGER.debug(String.format("Load %s from property", "sonar.url"));
            final String property = System.getProperty("sonar.url");
            if (!isEmpty(property)) {
                sonarProp.setUrl(property);
            }
        }
        if (isEmpty(sonarProp.getUser())) {
            LOGGER.debug(String.format("Load %s from property", "sonar.user"));
            final String property = System.getProperty("sonar.user");
            if (!isEmpty(property)) {
                sonarProp.setUser(property);
            }
        }
        if (isEmpty(sonarProp.getPassword())) {
            LOGGER.debug(String.format("Load %s from property", "sonar.password"));
            final String property = System.getProperty("sonar.password");
            if (!isEmpty(property)) {
                sonarProp.setPassword(property);
            }
        }
        return sonarProp;
    }

    public Slack slack(Slack slack) {
        if (slack == null) {
            slack = new Slack();
        }

        final Slack slackProp = new Slack(slack.getWebhook(), slack.getOnlyErrors(), slack.getCoverage());
        if (isEmpty(slack.getWebhook())) {
            LOGGER.debug(String.format("Load %s from property", "slack.webhook"));
            final String property = System.getProperty("slack.webhook");
            if (!isEmpty(property)) {
                slackProp.setWebhook(System.getProperty("slack.webhook"));
            }
        }
        if (slack.getOnlyErrors() == null) {
            LOGGER.debug(String.format("Load %s from property", "slack.onlyErrors"));
            final String property = System.getProperty("slack.onlyErrors");
            if (!isEmpty(property)) {
                slackProp.setOnlyErrors(Boolean.valueOf(System.getProperty("slack.onlyErrors")));
            }
        }
        if (slack.getCoverage() == null) {
            LOGGER.debug(String.format("Load %s from property", "slack.coverage"));
            final String property = System.getProperty("slack.coverage");
            if (!isEmpty(property)) {
                slackProp.setCoverage(Integer.parseInt(property));
            }

        }
        return slackProp;
    }
}
