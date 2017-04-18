package com.wordpress.fabiohbarbosa.notifier.plugin.facade;

import com.wordpress.fabiohbarbosa.notifier.PrepareFactoryTests;
import com.wordpress.fabiohbarbosa.notifier.scm.model.Scm;
import com.wordpress.fabiohbarbosa.notifier.scm.model.ScmFixture;
import com.wordpress.fabiohbarbosa.notifier.slack.adapter.SlackRequestAdapter;
import com.wordpress.fabiohbarbosa.notifier.slack.model.Level;
import com.wordpress.fabiohbarbosa.notifier.slack.model.Slack;
import com.wordpress.fabiohbarbosa.notifier.slack.model.SlackFixture;
import com.wordpress.fabiohbarbosa.notifier.slack.service.SlackPusher;
import com.wordpress.fabiohbarbosa.notifier.slack.web.model.SlackRequest;
import com.wordpress.fabiohbarbosa.notifier.slack.web.model.SlackRequestFixture;
import com.wordpress.fabiohbarbosa.notifier.sonar.adapter.SonarAdapter;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.Sonar;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.SonarFixture;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.SonarStats;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.SonarStatsFixture;
import com.wordpress.fabiohbarbosa.notifier.sonar.service.LevelFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.any;
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
    private SlackRequestAdapter slackRequestAdapter;

    @Mock
    private LevelFilter levelFilter;

    @Mock
    private SlackPusher slackPusher;

    @Test
    public void startSuccessTests() {
        final Sonar sonar = SonarFixture.newSonar();
        final Level level = Level.INFO;
        final Slack slack = SlackFixture.newSlack();
        final Scm scm = ScmFixture.newScmBitbucket();
        final SonarStats sonarStats = SonarStatsFixture.newSonarStats();
        final SlackRequest slackRequest = SlackRequestFixture.newSlackRequest();

        doReturn(sonarStats).when(sonarAdapter).adapter(sonar);
        doReturn(sonarStats).when(levelFilter).filter(sonarStats, level);
        doReturn(slackRequest).when(slackRequestAdapter).adapter(sonarStats, scm);

        notifier.start(sonar, slack, scm, level, false);

        verify(sonarAdapter).adapter(sonar);
        verify(slackRequestAdapter).adapter(sonarStats, scm);
        verify(slackPusher).slackPusher(slack, slackRequest);
    }

    @Test
    public void startSucessNotCallSlackPusherWhenQualityGateIsNull() {
        final Sonar sonar = SonarFixture.newSonar();
        final Level level = Level.INFO;
        final Slack slack = SlackFixture.newSlack();
        final Scm scm = ScmFixture.newScmEnv();
        final SonarStats sonarStats = SonarStatsFixture.newSonarStats();
        sonarStats.setQualityGate(null);

        final SlackRequest slackRequest = SlackRequestFixture.newSlackRequest();

        doReturn(sonarStats).when(sonarAdapter).adapter(sonar);
        doReturn(sonarStats).when(levelFilter).filter(sonarStats, level);
        doReturn(slackRequest).when(slackRequestAdapter).adapter(sonarStats, scm);

        notifier.start(sonar, slack, scm, level, false);

        verify(sonarAdapter).adapter(sonar);
        verify(slackRequestAdapter, times(0)).adapter(sonarStats, scm);
        verify(slackPusher, times(0)).slackPusher(any(Slack.class), any(SlackRequest.class));
    }

}