package br.com.gsw.slack.sonar.notifier.plugin.facade;

import br.com.gsw.slack.sonar.notifier.plugin.factory.LogFactory;
import br.com.gsw.slack.sonar.notifier.sonar.factory.SonarAdapterFactory;
import br.com.gsw.slack.sonar.notifier.sonar.model.SonarStats;
import br.com.gsw.slack.sonar.notifier.slack.adapter.SlackAdapter;
import br.com.gsw.slack.sonar.notifier.slack.factory.SlackAdapterFactory;
import br.com.gsw.slack.sonar.notifier.slack.model.Slack;
import br.com.gsw.slack.sonar.notifier.sonar.adapter.SonarAdapter;
import br.com.gsw.slack.sonar.notifier.sonar.model.Sonar;
import org.apache.maven.plugin.logging.Log;

public class Notifier {
    private static final Log LOGGER = LogFactory.getInstance();

    private SonarAdapter sonarAdapter = SonarAdapterFactory.getInstance();
    private SlackAdapter slackAdapter = SlackAdapterFactory.getInstance();

    public void start(final Sonar sonar, final Slack slack) {
        LOGGER.debug("Starting notifier...");

        final SonarStats sonarStats = sonarAdapter.adapter(sonar);
        slackAdapter.adapter(slack, sonarStats);
    }
}
