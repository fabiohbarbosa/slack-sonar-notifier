package br.com.gsw.slack.sonar.notifier.plugin.service;

import br.com.gsw.slack.sonar.notifier.PrepareFactoryTests;
import br.com.gsw.slack.sonar.notifier.plugin.factory.PluginValidatorFactory;
import br.com.gsw.slack.sonar.notifier.slack.model.Slack;
import br.com.gsw.slack.sonar.notifier.slack.model.SlackFixture;
import br.com.gsw.slack.sonar.notifier.sonar.model.Sonar;
import br.com.gsw.slack.sonar.notifier.sonar.model.SonarFixture;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PluginValidatorTest extends PrepareFactoryTests {
    private PluginValidator validator = PluginValidatorFactory.getInstance();

    //~-- Sonar
    @Test
    public void sonarSuccessTest() {
        validator.sonar(SonarFixture.newSonar());
    }

    @Test(expected = IllegalArgumentException.class)
    public void sonarTestNull() {
        validator.sonar(null);
    }

    @Test
    public void sonarTestNullKey() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setKey(null);
        try {
            validator.sonar(sonar);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("sonar.key"));
        }
    }

    @Test
    public void sonarTestEmptyKey() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setKey("");
        try {
            validator.sonar(sonar);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("sonar.key"));
        }
    }

    @Test
    public void sonarTestNullUrl() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setUrl(null);
        try {
            validator.sonar(sonar);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("sonar.url"));
        }
    }

    @Test
    public void sonarTestEmptyUrl() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setUrl("");
        try {
            validator.sonar(sonar);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("sonar.url"));
        }
    }

    //~-- Slack
    @Test
    public void slackSuccessTest() {
        validator.slack(SlackFixture.newSlack());
    }

    @Test(expected = IllegalArgumentException.class)
    public void slackrTestNull() {
        validator.slack(null);
    }

    @Test
    public void slackTestNullKey() {
        final Slack slack = SlackFixture.newSlack();
        slack.setWebhook(null);
        try {
            validator.slack(slack);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("slack.webhook"));
        }
    }

    @Test
    public void slackTestEmptyKey() {
        final Slack slack = SlackFixture.newSlack();
        slack.setWebhook("");
        try {
            validator.slack(slack);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("slack.webhook"));
        }
    }

}