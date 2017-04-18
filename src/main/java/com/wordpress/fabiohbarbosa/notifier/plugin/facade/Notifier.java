package com.wordpress.fabiohbarbosa.notifier.plugin.facade;

import com.wordpress.fabiohbarbosa.notifier.breaker.factory.BreakerFactory;
import com.wordpress.fabiohbarbosa.notifier.breaker.service.Breaker;
import com.wordpress.fabiohbarbosa.notifier.plugin.factory.LogFactory;
import com.wordpress.fabiohbarbosa.notifier.scm.model.Scm;
import com.wordpress.fabiohbarbosa.notifier.slack.adapter.SlackRequestAdapter;
import com.wordpress.fabiohbarbosa.notifier.slack.factory.SlackPusherFactory;
import com.wordpress.fabiohbarbosa.notifier.slack.factory.SlackRequestAdapterFactory;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.Level;
import com.wordpress.fabiohbarbosa.notifier.slack.model.Slack;
import com.wordpress.fabiohbarbosa.notifier.slack.service.SlackPusher;
import com.wordpress.fabiohbarbosa.notifier.slack.web.model.SlackRequest;
import com.wordpress.fabiohbarbosa.notifier.sonar.adapter.SonarAdapter;
import com.wordpress.fabiohbarbosa.notifier.sonar.factory.LevelFilterFactory;
import com.wordpress.fabiohbarbosa.notifier.sonar.factory.SonarAdapterFactory;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.Sonar;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.SonarStats;
import com.wordpress.fabiohbarbosa.notifier.sonar.service.LevelFilter;
import org.apache.maven.plugin.logging.Log;

public class Notifier {
    private static final Log LOGGER = LogFactory.getInstance();

    private SonarAdapter sonarAdapter = SonarAdapterFactory.getInstance();
    private SlackRequestAdapter slackRequestAdapter = SlackRequestAdapterFactory.getInstance();
    private SlackPusher slackPusher = SlackPusherFactory.getInstance();
    private Breaker breaker = BreakerFactory.getInstance();
    private LevelFilter levelFilter = LevelFilterFactory.getInstance();

    public void start(final Sonar sonar, final Slack slack, final Scm scm, final Level level, final Boolean toBreak) {
        LOGGER.debug("Starting notifier...");

        SonarStats sonarStats = sonarAdapter.adapter(sonar);

        sonarStats = levelFilter.filter(sonarStats, level);
        final String projectName = sonarStats.getProject().getName();
        if (sonarStats.getQualityGate() == null) {
            LOGGER.info(String.format("Not found errors in project %s", projectName));
            return;
        }

        final SlackRequest slackRequest = slackRequestAdapter.adapter(sonarStats, scm);

        slackPusher.slackPusher(slack, slackRequest);
        breaker.toBreak(toBreak, projectName, sonarStats.getQualityGate().getProjectStatus().getConditions());
    }
}