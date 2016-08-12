package br.com.gsw.slack.sonar.notifier.plugin.facade;

import br.com.gsw.slack.sonar.notifier.PrepareFactoryTests;
import br.com.gsw.slack.sonar.notifier.slack.model.Slack;
import br.com.gsw.slack.sonar.notifier.slack.model.SlackFixture;
import br.com.gsw.slack.sonar.notifier.sonar.model.Sonar;
import br.com.gsw.slack.sonar.notifier.sonar.model.SonarFixture;
import br.com.gsw.slack.sonar.notifier.plugin.factory.NotifierFactory;
import org.junit.Test;

public class NotifierIT extends PrepareFactoryTests {
    private final Notifier notifier;

    public NotifierIT() {
        super();
        notifier = NotifierFactory.getInstance();
    }

    @Test
    public void successTestOnlyErrorsTrue() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        final Slack slack = SlackFixture.newSlackEnv();

        notifier.start(sonar, slack, true);
    }

    @Test
    public void successTestOnlyErrorsFalse() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        final Slack slack = SlackFixture.newSlackEnv();

        notifier.start(sonar, slack, false);
    }

    @Test
    public void successTestOnlyErrorsTrueAndNullCoverage() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        sonar.setCoverage(null);
        final Slack slack = SlackFixture.newSlackEnv();

        notifier.start(sonar, slack, true);
    }

    @Test
    public void successTestOnlyErrorsTrueAndZeroCoverage() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        sonar.setCoverage(0.0);
        final Slack slack = SlackFixture.newSlackEnv();

        notifier.start(sonar, slack, true);
    }


}