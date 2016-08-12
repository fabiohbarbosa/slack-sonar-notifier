package br.com.gsw.slack.sonar.notifier.plugin.facade;

import br.com.gsw.slack.sonar.notifier.plugin.factory.LogFactory;
import br.com.gsw.slack.sonar.notifier.slack.adapter.SlackAdapter;
import br.com.gsw.slack.sonar.notifier.slack.factory.SlackAdapterFactory;
import br.com.gsw.slack.sonar.notifier.slack.factory.SlackPusherFactory;
import br.com.gsw.slack.sonar.notifier.slack.model.Slack;
import br.com.gsw.slack.sonar.notifier.slack.web.model.SlackRequest;
import br.com.gsw.slack.sonar.notifier.sonar.adapter.SonarAdapter;
import br.com.gsw.slack.sonar.notifier.sonar.factory.OnlyErrorsFilterFactory;
import br.com.gsw.slack.sonar.notifier.sonar.factory.SonarAdapterFactory;
import br.com.gsw.slack.sonar.notifier.sonar.model.Sonar;
import br.com.gsw.slack.sonar.notifier.sonar.model.SonarStats;
import br.com.gsw.slack.sonar.notifier.sonar.service.OnlyErrorsFilter;
import br.com.gsw.slack.sonar.notifier.slack.service.SlackPusher;
import org.apache.maven.plugin.logging.Log;

public class Notifier {
    private static final Log LOGGER = LogFactory.getInstance();

    private SonarAdapter sonarAdapter = SonarAdapterFactory.getInstance();
    private SlackAdapter slackAdapter = SlackAdapterFactory.getInstance();
    private SlackPusher slackPusher = SlackPusherFactory.getInstance();
    private OnlyErrorsFilter onlyErrorsFilter = OnlyErrorsFilterFactory.getInstance();

    public void start(final Sonar sonar, final Slack slack, final boolean onlyErrors) {
        LOGGER.debug("Starting notifier...");

        SonarStats sonarStats = sonarAdapter.adapter(sonar);

        if (onlyErrors) {
            sonarStats = onlyErrorsFilter.filter(sonarStats, sonar.getCoverage());
        }

        final SlackRequest slackRequest = slackAdapter.adapter(sonarStats);
        if (slackRequest != null) {
            slackPusher.slackPusher(slack, slackRequest);
            return;
        }
        LOGGER.info("Not found errors on Sonarqube :-)");
    }
}
