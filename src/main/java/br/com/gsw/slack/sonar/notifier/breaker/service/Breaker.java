package br.com.gsw.slack.sonar.notifier.breaker.service;

import br.com.gsw.slack.sonar.notifier.plugin.exception.SlackNotifierException;
import br.com.gsw.slack.sonar.notifier.plugin.factory.LogFactory;
import org.apache.maven.plugin.logging.Log;

public class Breaker {
    private static final Log LOGGER = LogFactory.getInstance();

    public void toBreak(final Boolean toBreak, final String projectName) {
        if (toBreak) {
            final String errorMsg = String.format("Found sonar problems in project %s", projectName);
            LOGGER.error(errorMsg);
            LOGGER.error("Vreaking execution");
            throw new SlackNotifierException(errorMsg);
        }
    }
}
