package br.com.gsw.slack.sonar.notifier.plugin.facade;

import br.com.gsw.slack.sonar.notifier.PrepareFactoryTests;
import br.com.gsw.slack.sonar.notifier.plugin.exception.SlackNotifierException;
import br.com.gsw.slack.sonar.notifier.scm.model.Scm;
import br.com.gsw.slack.sonar.notifier.scm.model.ScmFixture;
import br.com.gsw.slack.sonar.notifier.slack.adapter.SlackRequestAdapter;
import br.com.gsw.slack.sonar.notifier.slack.model.Slack;
import br.com.gsw.slack.sonar.notifier.slack.model.SlackFixture;
import br.com.gsw.slack.sonar.notifier.slack.service.SlackPusher;
import br.com.gsw.slack.sonar.notifier.slack.web.model.SlackRequest;
import br.com.gsw.slack.sonar.notifier.slack.web.model.SlackRequestFixture;
import br.com.gsw.slack.sonar.notifier.sonar.adapter.SonarAdapter;
import br.com.gsw.slack.sonar.notifier.sonar.model.Sonar;
import br.com.gsw.slack.sonar.notifier.sonar.model.SonarFixture;
import br.com.gsw.slack.sonar.notifier.sonar.model.SonarStats;
import br.com.gsw.slack.sonar.notifier.sonar.model.SonarStatsFixture;
import br.com.gsw.slack.sonar.notifier.sonar.service.OnlyErrorsFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NotifierTest extends PrepareFactoryTests {
    @Spy
    @InjectMocks
    private Notifier notifier;

    @Mock
    private SonarAdapter sonarAdapter;

    @Mock
    private OnlyErrorsFilter onlyErrorsFilter;

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
        doReturn(sonarStats).when(onlyErrorsFilter).filter(sonarStats, sonar.getCoverage());
        doReturn(slackRequest).when(slackRequestAdapter).adapter(sonarStats, scm);

        notifier.start(sonar, slack, scm, false);

        verify(sonarAdapter, times(1)).adapter(sonar);
        verify(onlyErrorsFilter, times(1)).filter(sonarStats, sonar.getCoverage());
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
        verify(onlyErrorsFilter, times(0)).filter(sonarStats, sonar.getCoverage());
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
        verify(onlyErrorsFilter, times(0)).filter(sonarStats, sonar.getCoverage());
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
            verify(onlyErrorsFilter, times(0)).filter(sonarStats, sonar.getCoverage());
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
        doReturn(sonarStats).when(onlyErrorsFilter).filter(sonarStats, sonar.getCoverage());
        doReturn(null).when(slackRequestAdapter).adapter(sonarStats, scm);

        notifier.start(sonar, slack, scm, false);

        verify(sonarAdapter, times(1)).adapter(sonar);
        verify(onlyErrorsFilter, times(0)).filter(sonarStats, sonar.getCoverage());
        verify(slackRequestAdapter, times(1)).adapter(sonarStats, scm);
        verify(slackPusher, times(0)).slackPusher(any(Slack.class), any(SlackRequest.class));
    }

}