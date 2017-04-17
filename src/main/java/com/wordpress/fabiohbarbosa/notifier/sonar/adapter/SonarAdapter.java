package com.wordpress.fabiohbarbosa.notifier.sonar.adapter;

import com.wordpress.fabiohbarbosa.notifier.plugin.factory.LogFactory;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.Project;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.Sonar;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.SonarStats;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.client.SonarRestClient;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.QualityGate;
import com.wordpress.fabiohbarbosa.notifier.web.client.FeignFactory;
import org.apache.maven.plugin.logging.Log;

public class SonarAdapter {
    private static final Log LOGGER = LogFactory.getInstance();
    private SonarRestClient sonarRestClient;

    public SonarStats adapter(final Sonar sonar) {
        LOGGER.debug("Sonar adapter...");
        final String url = sonar.getUrl();
        final String user = sonar.getLogin();
        final String password = sonar.getPassword();
        final String key = sonar.getKey();
        LOGGER.info(String.format("Trying to get sonar stats of %s from %s", key, url));

        LOGGER.debug(String.format("Creating sonar rest sonarRestClient for %s...", url));
        sonarRestClient = buildClient(url, user, password);

        LOGGER.debug("Parsing project...");
        final Project project = sonarRestClient.findProject(key).get(0);

        LOGGER.info("Sonar stats received");
        QualityGate qualityGates = sonarRestClient.qualityGates(key);

        return new SonarStats(project, sonar, qualityGates);
    }

    protected SonarRestClient buildClient(final String url, final String user, final String password) {
        return FeignFactory.build(url, user, password, SonarRestClient.class);
    }

}
