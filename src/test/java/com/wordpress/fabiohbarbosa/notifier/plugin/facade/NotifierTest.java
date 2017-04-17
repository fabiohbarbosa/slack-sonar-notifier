package com.wordpress.fabiohbarbosa.notifier.plugin.facade;

import com.wordpress.fabiohbarbosa.notifier.PrepareFactoryTests;
import com.wordpress.fabiohbarbosa.notifier.plugin.exception.SlackNotifierException;
import com.wordpress.fabiohbarbosa.notifier.scm.model.Scm;
import com.wordpress.fabiohbarbosa.notifier.scm.model.ScmFixture;
import com.wordpress.fabiohbarbosa.notifier.slack.adapter.SlackRequestAdapter;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.fail;
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
    private SlackPusher slackPusher;

    @Test
    public void startSuccessOnlyErrorsTrue() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        final Slack slack = SlackFixture.newSlackEnv(true);
        final Scm scm = ScmFixture.newScmEnv();
        final SonarStats sonarStats = SonarStatsFixture.newSonarStats();
        final SlackRequest slackRequest = SlackRequestFixture.newSlackRequest();

        doReturn(sonarStats).when(sonarAdapter).adapter(sonar);
        doReturn(slackRequest).when(slackRequestAdapter).adapter(sonarStats, scm);

        notifier.start(sonar, slack, scm, false);

        verify(sonarAdapter, times(1)).adapter(sonar);
        verify(slackRequestAdapter, times(1)).adapter(sonarStats, scm);
        verify(slackPusher, times(1)).slackPusher(slack, slackRequest);
    }

    @Test
    public void startSuccessOnlyErrorsFalse() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        final Slack slack = SlackFixture.newSlackEnv(false);
        final Scm scm = ScmFixture.newScmEnv();
        final SonarStats sonarStats = SonarStatsFixture.newSonarStats();
        final SlackRequest slackRequest = SlackRequestFixture.newSlackRequest();

        doReturn(sonarStats).when(sonarAdapter).adapter(sonar);
        doReturn(slackRequest).when(slackRequestAdapter).adapter(sonarStats, scm);

        notifier.start(sonar, slack, scm, false);

        verify(sonarAdapter, times(1)).adapter(sonar);
        verify(slackRequestAdapter, times(1)).adapter(sonarStats, scm);
        verify(slackPusher, times(1)).slackPusher(slack, slackRequest);
    }

    @Test
    public void startSuccessOnlyErrorsNull() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        final Slack slack = SlackFixture.newSlackEnv(null);
        final Scm scm = ScmFixture.newScmEnv();
        final SonarStats sonarStats = SonarStatsFixture.newSonarStats();
        final SlackRequest slackRequest = SlackRequestFixture.newSlackRequest();

        doReturn(sonarStats).when(sonarAdapter).adapter(sonar);
        doReturn(slackRequest).when(slackRequestAdapter).adapter(sonarStats, scm);

        notifier.start(sonar, slack, scm, false);

        verify(sonarAdapter, times(1)).adapter(sonar);
        verify(slackRequestAdapter, times(1)).adapter(sonarStats, scm);
        verify(slackPusher, times(1)).slackPusher(slack, slackRequest);
    }

    @Test
    public void startSuccessToBreakTest() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        final Slack slack = SlackFixture.newSlackEnv(null);
        final Scm scm = ScmFixture.newScmEnv();
        final SonarStats sonarStats = SonarStatsFixture.newSonarStats();
        final SlackRequest slackRequest = SlackRequestFixture.newSlackRequest();

        doReturn(sonarStats).when(sonarAdapter).adapter(sonar);
        doReturn(slackRequest).when(slackRequestAdapter).adapter(sonarStats, scm);

        try {
            notifier.start(sonar, slack, scm, true);
        } catch (SlackNotifierException e) {
            verify(sonarAdapter, times(1)).adapter(sonar);
            verify(slackRequestAdapter, times(1)).adapter(sonarStats, scm);
            verify(slackPusher, times(1)).slackPusher(slack, slackRequest);
            return;
        }
        fail();
    }

    @Test
    public void startSucessNotCallSlackPusherToNullSlackRequest() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        final Slack slack = SlackFixture.newSlackEnv(false);
        final Scm scm = ScmFixture.newScmEnv();
        final SonarStats sonarStats = SonarStatsFixture.newSonarStats();

        doReturn(sonarStats).when(sonarAdapter).adapter(sonar);
        doReturn(null).when(slackRequestAdapter).adapter(sonarStats, scm);

        notifier.start(sonar, slack, scm, false);

        verify(sonarAdapter, times(1)).adapter(sonar);
        verify(slackRequestAdapter, times(1)).adapter(sonarStats, scm);
        verify(slackPusher, times(0)).slackPusher(any(Slack.class), any(SlackRequest.class));
    }

}