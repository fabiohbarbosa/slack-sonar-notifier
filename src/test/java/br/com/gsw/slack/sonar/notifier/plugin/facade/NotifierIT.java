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
    public void test() {
        final Sonar sonar = SonarFixture.newSonarAuth();
        final Slack slack = SlackFixture.newSlack();

        notifier.start(sonar, slack);
    }
}