package br.com.gsw.slack.sonar.notifier.plugin.service;

import br.com.gsw.slack.sonar.notifier.PrepareFactoryTests;
import br.com.gsw.slack.sonar.notifier.plugin.factory.PluginLoadPropertiesFactory;
import br.com.gsw.slack.sonar.notifier.slack.model.Slack;
import br.com.gsw.slack.sonar.notifier.slack.model.SlackFixture;
import br.com.gsw.slack.sonar.notifier.sonar.model.Sonar;
import br.com.gsw.slack.sonar.notifier.sonar.model.SonarFixture;
import org.apache.maven.project.MavenProject;
import org.junit.Test;

import static org.junit.Assert.*;

public class PluginLoadPropertiesTest extends PrepareFactoryTests {
    private PluginLoadProperties loadProperties = PluginLoadPropertiesFactory.getInstance();

    //~-- all sonar keys
    @Test
    public void sonarTestAllFieldsSet() {
        final Sonar sonar = SonarFixture.newSonar();
        final Sonar sonarProp = loadProperties.sonar(sonar, MavenProjectFixture.newProject());
        assertEquals(sonar.getKey(), sonarProp.getKey());
        assertEquals(sonar.getUrl(), sonarProp.getUrl());
        assertEquals(sonar.getUser(), sonarProp.getUser());
        assertEquals(sonar.getPassword(), sonarProp.getPassword());
        assertEquals(sonar.getCoverage(), sonarProp.getCoverage());
    }

    @Test
    public void sonarTestNullSonarParam() {
        assertNotNull(loadProperties.sonar(null, MavenProjectFixture.newProject()));
    }

    //~-- sonar.key
    @Test
    public void sonarTestNullKey() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setKey(null);

        // set property
        final String key = "sonar.key";
        System.setProperty("sonar.key", key);

        final Sonar sonarProp = loadProperties.sonarKey(sonar, MavenProjectFixture.newProject());
        assertEquals(key, sonarProp.getKey());
        assertEquals(sonar.getUrl(), sonarProp.getUrl());
        assertEquals(sonar.getUser(), sonarProp.getUser());
        assertEquals(sonar.getPassword(), sonarProp.getPassword());
        assertEquals(sonar.getCoverage(), sonarProp.getCoverage());
    }

    @Test
    public void sonarTestEmptyKey() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setKey("");

        // set property
        final String key = "sonar.key";
        System.setProperty("sonar.key", key);

        final Sonar sonarProp = loadProperties.sonarKey(sonar, MavenProjectFixture.newProject());
        assertEquals(key, sonarProp.getKey());
        assertEquals(sonar.getUrl(), sonarProp.getUrl());
        assertEquals(sonar.getUser(), sonarProp.getUser());
        assertEquals(sonar.getPassword(), sonarProp.getPassword());
        assertEquals(sonar.getCoverage(), sonarProp.getCoverage());
    }

    @Test
    public void sonarTestNullKeyUsingProjectProperties() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setKey(null);

        System.setProperty("sonar.key", "");
        final MavenProject mavenProject = MavenProjectFixture.newProject();

        final Sonar sonarProp = loadProperties.sonarKey(sonar, mavenProject);
        assertEquals(mavenProject.getGroupId()+":"+mavenProject.getArtifactId(), sonarProp.getKey());
        assertEquals(sonar.getUrl(), sonarProp.getUrl());
        assertEquals(sonar.getUser(), sonarProp.getUser());
        assertEquals(sonar.getPassword(), sonarProp.getPassword());
        assertEquals(sonar.getCoverage(), sonarProp.getCoverage());
    }

    //~-- sonar.url
    @Test
    public void sonarTestNullUrl() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setUrl(null);

        // set property
        final String url = "sonar.url";
        System.setProperty("sonar.url", url);

        final Sonar sonarProp = loadProperties.sonarUrl(sonar);
        assertEquals(sonar.getKey(), sonarProp.getKey());
        assertEquals(url, sonarProp.getUrl());
        assertEquals(sonar.getUser(), sonarProp.getUser());
        assertEquals(sonar.getPassword(), sonarProp.getPassword());
        assertEquals(sonar.getCoverage(), sonarProp.getCoverage());
    }

    @Test
    public void sonarTestEmptyUrl() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setUrl("");

        // set property
        final String url = "sonar.url";
        System.setProperty("sonar.url", url);

        final Sonar sonarProp = loadProperties.sonarUrl(sonar);
        assertEquals(sonar.getKey(), sonarProp.getKey());
        assertEquals(url, sonarProp.getUrl());
        assertEquals(sonar.getUser(), sonarProp.getUser());
        assertEquals(sonar.getPassword(), sonarProp.getPassword());
        assertEquals(sonar.getCoverage(), sonarProp.getCoverage());
    }

    //~-- sonar.user
    @Test
    public void sonarTestNullUser() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setUser(null);

        // set property
        final String user = "sonar.user";
        System.setProperty("sonar.user", user);

        final Sonar sonarProp = loadProperties.sonarUser(sonar);
        assertEquals(sonar.getKey(), sonarProp.getKey());
        assertEquals(sonar.getUrl(), sonarProp.getUrl());
        assertEquals(user, sonarProp.getUser());
        assertEquals(sonar.getPassword(), sonarProp.getPassword());
        assertEquals(sonar.getCoverage(), sonarProp.getCoverage());
    }

    @Test
    public void sonarTestEmptyUser() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setUser("");

        // set property
        final String user = "sonar.user";
        System.setProperty("sonar.user", user);

        final Sonar sonarProp = loadProperties.sonarUser(sonar);
        assertEquals(sonar.getKey(), sonarProp.getKey());
        assertEquals(sonar.getUrl(), sonarProp.getUrl());
        assertEquals(user, sonarProp.getUser());
        assertEquals(sonar.getPassword(), sonarProp.getPassword());
        assertEquals(sonar.getCoverage(), sonarProp.getCoverage());
    }

    //~-- sonar.password
    @Test
    public void sonarTestNullPassword() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setPassword(null);

        // set property
        final String password = "sonar.password";
        System.setProperty("sonar.password", password);

        final Sonar sonarProp = loadProperties.sonarPassword(sonar);
        assertEquals(sonar.getKey(), sonarProp.getKey());
        assertEquals(sonar.getUrl(), sonarProp.getUrl());
        assertEquals(sonar.getUser(), sonarProp.getUser());
        assertEquals(password, sonarProp.getPassword());
        assertEquals(sonar.getCoverage(), sonarProp.getCoverage());
    }

    @Test
    public void sonarTestEmptyPassword() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setPassword("");

        // set property
        final String password = "sonar.password";
        System.setProperty("sonar.password", password);

        final Sonar sonarProp = loadProperties.sonarPassword(sonar);
        assertEquals(sonar.getKey(), sonarProp.getKey());
        assertEquals(sonar.getUrl(), sonarProp.getUrl());
        assertEquals(sonar.getUser(), sonarProp.getUser());
        assertEquals(password, sonarProp.getPassword());
        assertEquals(sonar.getCoverage(), sonarProp.getCoverage());
    }

    //~-- sonar.coverage
    @Test
    public void slackTestNullCoverage() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        sonar.setCoverage(null);

        // set property
        final String coverage = "1";
        System.setProperty("sonar.coverage", coverage);

        final Sonar sonarProp = loadProperties.sonarCoverage(sonar);
        assertEquals(sonar.getKey(), sonarProp.getKey());
        assertEquals(sonar.getUrl(), sonarProp.getUrl());
        assertEquals(sonar.getUser(), sonarProp.getUser());
        assertTrue(sonarProp.getCoverage() == Integer.parseInt(coverage));
    }

    //~-- all slack keys
    @Test
    public void slackTestAllFieldsSet() {
        final Slack slack = SlackFixture.newSlackEnv();
        final Slack slackProp = loadProperties.slack(slack);
        assertEquals(slack.getWebhook(), slackProp.getWebhook());
        assertEquals(slack.getOnlyErrors(), slackProp.getOnlyErrors());
    }

    @Test
    public void slackTestNullSlackParam() {
        assertNotNull(loadProperties.slack(null));
    }

    //~-- slack.webhook
    @Test
    public void slackTestNullWebhook() {
        final Slack slack = SlackFixture.newSlackEnv();
        slack.setWebhook(null);

        // set property
        final String webhook = "slack.webhook";
        System.setProperty("slack.webhook", webhook);

        final Slack slackProp = loadProperties.slackWebhook(slack);
        assertEquals(webhook, slackProp.getWebhook());
        assertEquals(slack.getOnlyErrors(), slackProp.getOnlyErrors());
    }

    @Test
    public void slackTestEmptyWebhook() {
        final Slack slack = SlackFixture.newSlackEnv();
        slack.setWebhook("");

        // set property
        final String webhook = "slack.webhook";
        System.setProperty("slack.webhook", webhook);

        final Slack slackProp = loadProperties.slackWebhook(slack);
        assertEquals(webhook, slackProp.getWebhook());
        assertEquals(slack.getOnlyErrors(), slackProp.getOnlyErrors());
    }

    @Test
    public void slackTestNullOnlyErrors() {
        final Slack slack = SlackFixture.newSlackEnv();
        slack.setOnlyErrors(null);

        // set property
        final String onlyErrors = "false";
        System.setProperty("slack.onlyErrors", onlyErrors);

        final Slack slackProp = loadProperties.slackOnlyErrors(slack);
        assertEquals(slack.getWebhook(), slackProp.getWebhook());
        assertEquals(Boolean.parseBoolean(onlyErrors), slackProp.getOnlyErrors());
    }

}