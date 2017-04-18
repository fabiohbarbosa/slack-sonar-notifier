package com.wordpress.fabiohbarbosa.notifier.breaker.service;

import com.wordpress.fabiohbarbosa.notifier.plugin.exception.SlackNotifierException;
import com.wordpress.fabiohbarbosa.notifier.plugin.factory.LogFactory;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.Condition;
import org.apache.maven.plugin.logging.Log;

import java.util.List;

public class Breaker {
    private static final Log LOGGER = LogFactory.getInstance();

    public void toBreak(final Boolean toBreak, final String projectName, List<Condition> conditions) {
        if (toBreak) {
            final String errorMsg = String.format("Found sonar problems in project %s", projectName);
            LOGGER.error(errorMsg);

            LOGGER.error("------------------------------------------------------------------------");
            LOGGER.error("Found Errors");
            LOGGER.error("------------------------------------------------------------------------");
            for (Condition c : conditions) {
                LOGGER.error(String.format("%s: %s", c.getMetricKey().name(), c.getStatus().name()));
                LOGGER.error(String.format("Actual value: %s %n", c.getActualValue()));
            }
            LOGGER.error("------------------------------------------------------------------------");

            LOGGER.error("Breaking execution");
            throw new SlackNotifierException(errorMsg);
        }
    }
}
