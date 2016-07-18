package br.com.gsw.slack.sonar.notifier.plugin.service;

import br.com.gsw.slack.sonar.notifier.plugin.factory.LogFactory;
import br.com.gsw.slack.sonar.notifier.slack.model.Slack;
import br.com.gsw.slack.sonar.notifier.sonar.model.Sonar;
import org.apache.maven.plugin.logging.Log;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class PluginLoadProperties {
    private static final Log LOGGER = LogFactory.getInstance();

    public Sonar sonar(final Sonar sonar) {
        if (sonar == null) {
            return null;
        }
        final Sonar sonarProp = new Sonar(sonar.getKey(), sonar.getUrl(), sonar.getUser(),sonar.getPassword());

        if (isEmpty(sonar.getKey())) {
            LOGGER.info(String.format("Load %s from property", "sonar.key"));
            sonarProp.setKey(System.getProperty("sonar.key"));
        }
        if (isEmpty(sonar.getUrl())) {
            LOGGER.info(String.format("Load %s from property", "sonar.url"));
            sonarProp.setUrl(System.getProperty("sonar.url"));
        }
        if (isEmpty(sonar.getUser())) {
            LOGGER.info(String.format("Load %s from property", "sonar.user"));
            sonarProp.setUser(System.getProperty("sonar.user"));
        }
        if (isEmpty(sonar.getPassword())) {
            LOGGER.info(String.format("Load %s from property", "sonar.password"));
            sonarProp.setPassword(System.getProperty("sonar.password"));
        }
        return sonarProp;
    }

    public Slack slack(final Slack slack) {
        if (slack == null) {
            return null;
        }

        final Slack slackProp = new Slack(slack.getWebhook(), slack.getOnlyErrors(), slack.getCoverage());
        if (isEmpty(slack.getWebhook())) {
            LOGGER.info(String.format("Load %s from property", "slack.webhook"));
            slackProp.setWebhook(System.getProperty("slack.webhook"));
        }
        if (slack.getOnlyErrors() == null) {
            LOGGER.info(String.format("Load %s from property", "slack.onlyErrors"));
            slackProp.setOnlyErrors(Boolean.valueOf(System.getProperty("slack.onlyErrors")));
        }
        if (slack.getCoverage() == null) {
            LOGGER.info(String.format("Load %s from property", "slack.coverage"));
            slackProp.setCoverage(Integer.parseInt(System.getProperty("slack.coverage")));
        }
        return slackProp;
    }
}
