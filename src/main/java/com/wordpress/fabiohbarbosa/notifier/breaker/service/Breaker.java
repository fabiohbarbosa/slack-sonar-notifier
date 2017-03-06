package com.wordpress.fabiohbarbosa.notifier.breaker.service;

import com.wordpress.fabiohbarbosa.notifier.plugin.exception.SlackNotifierException;
import com.wordpress.fabiohbarbosa.notifier.plugin.factory.LogFactory;
import org.apache.maven.plugin.logging.Log;

public class Breaker {
    private static final Log LOGGER = LogFactory.getInstance();

    public void toBreak(final Boolean toBreak, final String projectName) {
        if (toBreak) {
            final String errorMsg = String.format("Found sonar problems in project %s", projectName);
            LOGGER.error(errorMsg);
            LOGGER.error("Breaking execution");
            throw new SlackNotifierException(errorMsg);
        }
    }
}
