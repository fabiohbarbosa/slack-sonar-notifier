package com.wordpress.fabiohbarbosa.notifier.plugin.facade;

import com.wordpress.fabiohbarbosa.notifier.breaker.factory.BreakerFactory;
import com.wordpress.fabiohbarbosa.notifier.breaker.service.Breaker;
import com.wordpress.fabiohbarbosa.notifier.plugin.factory.LogFactory;
import com.wordpress.fabiohbarbosa.notifier.slack.model.Level;
import com.wordpress.fabiohbarbosa.notifier.sonar.adapter.SonarAdapter;
import com.wordpress.fabiohbarbosa.notifier.sonar.factory.LevelFilterFactory;
import com.wordpress.fabiohbarbosa.notifier.sonar.factory.SonarAdapterFactory;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.Sonar;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.SonarStats;
import com.wordpress.fabiohbarbosa.notifier.sonar.service.LevelFilter;
import org.apache.maven.plugin.logging.Log;

public class Verifier {
    private static final Log LOGGER = LogFactory.getInstance();

    private SonarAdapter sonarAdapter = SonarAdapterFactory.getInstance();
    private Breaker breaker = BreakerFactory.getInstance();
    private LevelFilter levelFilter = LevelFilterFactory.getInstance();

    public void start(final Sonar sonar, final Level level) {
        LOGGER.debug("Starting notifier...");

        SonarStats sonarStats = sonarAdapter.adapter(sonar);

        sonarStats = levelFilter.filter(sonarStats, level);
        final String projectName = sonarStats.getProject().getName();
        if (sonarStats.getQualityGate() == null) {
            LOGGER.info(String.format("Not found errors in project %s", projectName));
            return;
        }

        breaker.toBreak(true, projectName, sonarStats.getQualityGate().getProjectStatus().getConditions());
    }
}
