package com.wordpress.fabiohbarbosa.notifier.plugin.facade;

import com.wordpress.fabiohbarbosa.notifier.PrepareFactoryTests;
import com.wordpress.fabiohbarbosa.notifier.plugin.factory.NotifierFactory;
import com.wordpress.fabiohbarbosa.notifier.scm.model.Scm;
import com.wordpress.fabiohbarbosa.notifier.scm.model.ScmFixture;
import com.wordpress.fabiohbarbosa.notifier.slack.model.Slack;
import com.wordpress.fabiohbarbosa.notifier.slack.model.SlackFixture;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.Sonar;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.SonarFixture;
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
        final Slack slack = SlackFixture.newSlackEnv(true);
        final Scm scm = ScmFixture.newScmEnv();

        notifier.start(sonar, slack, scm, false);
    }

    @Test
    public void successTestOnlyErrorsFalse() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        final Slack slack = SlackFixture.newSlackEnv(false);
        final Scm scm = ScmFixture.newScmEnv();

        notifier.start(sonar, slack, scm, false);
    }

    @Test
    public void successTestOnlyErrorsTrueAndNullCoverage() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        sonar.setCoverage(null);
        final Slack slack = SlackFixture.newSlackEnv(true);
        final Scm scm = ScmFixture.newScmEnv();

        notifier.start(sonar, slack, scm, false);
    }

    @Test
    public void successTestOnlyErrorsTrueAndZeroCoverage() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        sonar.setCoverage(0.0);
        final Slack slack = SlackFixture.newSlackEnv(true);
        final Scm scm = ScmFixture.newScmEnv();

        notifier.start(sonar, slack, scm, false);
    }

}