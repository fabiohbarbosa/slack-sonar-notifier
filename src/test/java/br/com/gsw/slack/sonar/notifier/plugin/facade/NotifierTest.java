package br.com.gsw.slack.sonar.notifier.plugin.facade;

import br.com.gsw.slack.sonar.notifier.slack.model.SlackFixture;
import br.com.gsw.slack.sonar.notifier.sonar.model.SonarFixture;
import br.com.gsw.slack.sonar.notifier.sonar.model.SonarStats;
import br.com.gsw.slack.sonar.notifier.PrepareFactoryTests;
import br.com.gsw.slack.sonar.notifier.slack.adapter.SlackAdapter;
import br.com.gsw.slack.sonar.notifier.slack.model.Slack;
import br.com.gsw.slack.sonar.notifier.sonar.adapter.SonarAdapter;
import br.com.gsw.slack.sonar.notifier.sonar.model.Sonar;
import br.com.gsw.slack.sonar.notifier.sonar.model.SonarStatsFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NotifierTest extends PrepareFactoryTests {
    @Spy
    @InjectMocks
    private Notifier notifier;

    @Mock
    private SonarAdapter sonarAdapter;

    @Mock
    private SlackAdapter slackAdapter;

    @Test
    public void startSuccessTest() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        final Slack slack = SlackFixture.newSlackEnv();
        final SonarStats sonarStats = SonarStatsFixture.newSonarStats();

        doReturn(sonarStats).when(sonarAdapter).adapter(sonar);

        notifier.start(sonar, slack);

        verify(sonarAdapter, times(1)).adapter(sonar);
        verify(slackAdapter, times(1)).adapter(slack, sonarStats);
    }
}