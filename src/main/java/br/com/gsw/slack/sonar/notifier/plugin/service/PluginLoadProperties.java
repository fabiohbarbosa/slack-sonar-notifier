package br.com.gsw.slack.sonar.notifier.plugin.service;

import br.com.gsw.slack.sonar.notifier.plugin.factory.LogFactory;
import br.com.gsw.slack.sonar.notifier.slack.model.Slack;
import br.com.gsw.slack.sonar.notifier.sonar.model.Sonar;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class PluginLoadProperties {
    private static final Log LOGGER = LogFactory.getInstance();

    public Sonar sonar(Sonar sonar, final MavenProject mavenProject) {
        if (sonar == null) {
            sonar = new Sonar();
        }
        Sonar sonarProp = new Sonar(sonar.getKey(), sonar.getUrl(), sonar.getUser(),sonar.getPassword());
        LOGGER.debug("Sonar properties");
        LOGGER.debug(sonarProp.toString());

        sonarProp = sonarKey(sonarProp, mavenProject);
        sonarProp = sonarUrl(sonarProp);
        sonarProp = sonarUser(sonarProp);
        sonarProp = sonarPassword(sonarProp);

        return sonarProp;
    }

    protected Sonar sonarPassword(final Sonar sonarProp) {
        if (isEmpty(sonarProp.getPassword())) {
            LOGGER.debug(String.format("Load %s from property", "sonar.password"));
            final String property = System.getProperty("sonar.password");
            if (!isEmpty(property)) {
                sonarProp.setPassword(property);
            }
        }
        return sonarProp;
    }

    protected Sonar sonarUser(final Sonar sonarProp) {
        if (isEmpty(sonarProp.getUser())) {
            LOGGER.debug(String.format("Load %s from property", "sonar.user"));
            final String property = System.getProperty("sonar.user");
            if (!isEmpty(property)) {
                sonarProp.setUser(property);
            }
        }
        return sonarProp;
    }

    protected Sonar sonarUrl(final Sonar sonarProp) {
        if (isEmpty(sonarProp.getUrl())) {
            LOGGER.debug(String.format("Load %s from property", "sonar.url"));
            final String property = System.getProperty("sonar.url");
            if (!isEmpty(property)) {
                sonarProp.setUrl(property);
            }
        }
        return sonarProp;
    }

    protected Sonar sonarKey(final Sonar sonarProp, final MavenProject project) {
        if (isEmpty(sonarProp.getKey())) {
            LOGGER.debug(String.format("Load %s from property", "sonar.key"));
            final String property = System.getProperty("sonar.key");
            if (!isEmpty(property)) {
                sonarProp.setKey(property);
            }
        }
        if (isEmpty(sonarProp.getKey())) {
            LOGGER.debug(String.format("Load %s from project artifactId and groupId", "sonar.key"));
            sonarProp.setKey(project.getGroupId()+":"+project.getArtifactId());
        }
        return sonarProp;
    }

    public Slack slack(Slack slack) {
        if (slack == null) {
            slack = new Slack();
        }

        final Slack slackProp = new Slack(slack.getWebhook(), slack.getOnlyErrors(), slack.getCoverage());
        slackWebhook(slackProp);
        slackOnlyErrors(slackProp);
        slackCoverage(slackProp);

        return slackProp;
    }

    protected Slack slackCoverage(final Slack slackProp) {
        if (slackProp.getCoverage() == null) {
            LOGGER.debug(String.format("Load %s from property", "slack.coverage"));
            final String property = System.getProperty("slack.coverage");
            if (!isEmpty(property)) {
                slackProp.setCoverage(Integer.parseInt(property));
            }
        }
        return slackProp;
    }

    protected Slack slackOnlyErrors(final Slack slackProp) {
        if (slackProp.getOnlyErrors() == null) {
            LOGGER.debug(String.format("Load %s from property", "slack.onlyErrors"));
            final String property = System.getProperty("slack.onlyErrors");
            if (!isEmpty(property)) {
                slackProp.setOnlyErrors(Boolean.valueOf(System.getProperty("slack.onlyErrors")));
            }
        }
        return slackProp;
    }

    protected Slack slackWebhook(final Slack slackProp) {
        if (isEmpty(slackProp.getWebhook())) {
            LOGGER.debug(String.format("Load %s from property", "slack.webhook"));
            final String property = System.getProperty("slack.webhook");
            if (!isEmpty(property)) {
                slackProp.setWebhook(System.getProperty("slack.webhook"));
            }
        }
        return slackProp;
    }
}
