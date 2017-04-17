package com.wordpress.fabiohbarbosa.notifier.plugin.facade;

import com.wordpress.fabiohbarbosa.notifier.PrepareFactoryTests;
import com.wordpress.fabiohbarbosa.notifier.plugin.factory.NotifierFactory;
import com.wordpress.fabiohbarbosa.notifier.scm.model.Scm;
import com.wordpress.fabiohbarbosa.notifier.scm.model.ScmFixture;
import com.wordpress.fabiohbarbosa.notifier.slack.model.Slack;
import com.wordpress.fabiohbarbosa.notifier.slack.model.SlackFixture;
import com.wordpress.fabiohbarbosa.notifier.slack.model.SlackLevel;
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
    public void successTestInfoLevel() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        final Slack slack = SlackFixture.newSlackEnv(SlackLevel.INFO);
        final Scm scm = ScmFixture.newScmEnv();

        notifier.start(sonar, slack, scm, false);
    }

    @Test
    public void successTestWarningLevel() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        final Slack slack = SlackFixture.newSlackEnv(SlackLevel.WARNING);
        final Scm scm = ScmFixture.newScmEnv();

        notifier.start(sonar, slack, scm, false);
    }

    @Test
    public void successTestErrorLevel() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        final Slack slack = SlackFixture.newSlackEnv(SlackLevel.ERROR);
        final Scm scm = ScmFixture.newScmEnv();

        notifier.start(sonar, slack, scm, false);
    }


}