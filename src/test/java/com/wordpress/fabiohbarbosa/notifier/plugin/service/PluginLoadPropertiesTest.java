package com.wordpress.fabiohbarbosa.notifier.plugin.service;

import com.wordpress.fabiohbarbosa.notifier.PrepareFactoryTests;
import com.wordpress.fabiohbarbosa.notifier.plugin.factory.PluginLoadPropertiesFactory;
import com.wordpress.fabiohbarbosa.notifier.scm.model.Scm;
import com.wordpress.fabiohbarbosa.notifier.scm.model.ScmFixture;
import com.wordpress.fabiohbarbosa.notifier.slack.model.Slack;
import com.wordpress.fabiohbarbosa.notifier.slack.model.SlackFixture;
import com.wordpress.fabiohbarbosa.notifier.slack.model.SlackLevel;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.Sonar;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.SonarFixture;
import org.apache.maven.project.MavenProject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PluginLoadPropertiesTest extends PrepareFactoryTests {
    private PluginLoadProperties loadProperties = PluginLoadPropertiesFactory.getInstance();

    //~-- all sonar keys
    @Test
    public void sonarTestAllFieldsSet() {
        final Sonar sonar = SonarFixture.newSonar();
        final Sonar sonarProp = loadProperties.sonar(sonar, MavenProjectFixture.newProject());
        assertEquals(sonar.getKey(), sonarProp.getKey());
        assertEquals(sonar.getUrl(), sonarProp.getUrl());
        assertEquals(sonar.getLogin(), sonarProp.getLogin());
        assertEquals(sonar.getPassword(), sonarProp.getPassword());
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

        final String property = loadProperties.sonarKey(sonar, MavenProjectFixture.newProject());
        assertEquals(key, property);
    }

    @Test
    public void sonarTestEmptyKey() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setKey("");

        // set property
        final String key = "sonar.key";
        System.setProperty("sonar.key", key);

        final String property = loadProperties.sonarKey(sonar, MavenProjectFixture.newProject());
        assertEquals(key, property);
    }

    @Test
    public void sonarTestNullKeyUsingProjectProperties() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setKey(null);

        System.setProperty("sonar.key", "");
        final MavenProject mavenProject = MavenProjectFixture.newProject();

        final String property = loadProperties.sonarKey(sonar, mavenProject);
        assertEquals(mavenProject.getGroupId()+":"+mavenProject.getArtifactId(), property);
    }

    //~-- sonar.url
    @Test
    public void sonarTestNullUrl() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setUrl(null);

        // set property
        final String url = "sonar.url";
        System.setProperty("sonar.url", url);

        final String property = loadProperties.sonarUrl(sonar);
        assertEquals(url, property);
    }

    @Test
    public void sonarTestEmptyUrl() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setUrl("");

        // set property
        final String url = "sonar.url";
        System.setProperty("sonar.url", url);

        final String property = loadProperties.sonarUrl(sonar);
        assertEquals(url, property);
    }

    //~-- sonar.login
    @Test
    public void sonarTestNullLogin() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setLogin(null);

        // set property
        final String login = "sonar.login";
        System.setProperty("sonar.login", login);

        final String property = loadProperties.sonarLogin(sonar);
        assertEquals(login, property);
    }

    @Test
    public void sonarTestEmptyLogin() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setLogin("");

        // set property
        final String login = "sonar.login";
        System.setProperty("sonar.login", login);

        final String property = loadProperties.sonarLogin(sonar);
        assertEquals(login, property);
    }

    //~-- sonar.password
    @Test
    public void sonarTestNullPassword() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setPassword(null);

        // set property
        final String password = "sonar.password";
        System.setProperty("sonar.password", password);

        final String property = loadProperties.sonarPassword(sonar);
        assertEquals(password, property);
    }

    @Test
    public void sonarTestEmptyPassword() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setPassword("");

        // set property
        final String password = "sonar.password";
        System.setProperty("sonar.password", password);

        final String property = loadProperties.sonarPassword(sonar);
        assertEquals(password, property);
    }

    //~-- all slack keys
    @Test
    public void slackTestAllFieldsSet() {
        final Slack slack = SlackFixture.newSlackEnv();
        final Slack slackProp = loadProperties.slack(slack);
        assertEquals(slack.getWebhook(), slackProp.getWebhook());
        assertEquals(slack.getLevel(), slackProp.getLevel());
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

        final String property = loadProperties.slackWebhook(slack);
        assertEquals(webhook, property);
    }

    @Test
    public void slackTestEmptyWebhook() {
        final Slack slack = SlackFixture.newSlackEnv();
        slack.setWebhook("");

        // set property
        final String webhook = "slack.webhook";
        System.setProperty("slack.webhook", webhook);

        final String property = loadProperties.slackWebhook(slack);
        assertEquals(webhook, property);
    }

    // slack.level
    @Test
    public void slackTestInfoLevel() {
        final Slack slack = SlackFixture.newSlackEnv();
        slack.setLevel(null);

        // set property
        final String level = "INFO";
        System.setProperty("slack.level", level);

        final SlackLevel property = loadProperties.slackLevel(slack);
        assertEquals(SlackLevel.INFO, property);
    }

    @Test
    public void slackTestWarningLevel() {
        final Slack slack = SlackFixture.newSlackEnv();
        slack.setLevel(null);

        // set property
        final String level = "WARNING";
        System.setProperty("slack.level", level);

        final SlackLevel property = loadProperties.slackLevel(slack);
        assertEquals(SlackLevel.WARNING, property);
    }

    @Test
    public void slackTestErrorLevel() {
        final Slack slack = SlackFixture.newSlackEnv();
        slack.setLevel(null);

        // set property
        final String level = "ERROR";
        System.setProperty("slack.level", level);

        final SlackLevel property = loadProperties.slackLevel(slack);
        assertEquals(SlackLevel.ERROR, property);
    }

    //~-- all scm keys
    @Test
    public void scmTestAllFieldsSet() {
        final Scm scm = ScmFixture.newScmEnv();
        final Scm scmProp = loadProperties.scm(scm);
        assertEquals(scm.getUser(), scmProp.getUser());
        assertEquals(scm.getBranch(), scmProp.getBranch());
    }

    @Test
    public void scmTestNullScmParam() {
        assertNotNull(loadProperties.scm(null));
    }

    //~-- slack.user
    @Test
    public void scmTestNullUser() {
        final Scm scm = ScmFixture.newScmGithub();
        scm.setUser(null);

        // set property
        final String key = "scm.user";
        System.setProperty("scm.user", key);

        final String property = loadProperties.scmUser(scm);
        assertEquals(key, property);
    }

    @Test
    public void scmTestEmptyUser() {
        final Scm scm = ScmFixture.newScmGithub();
        scm.setUser("");

        // set property
        final String key = "scm.user";
        System.setProperty("scm.user", key);

        final String property = loadProperties.scmUser(scm);
        assertEquals(key, property);
    }

    //~-- slack.branch
    @Test
    public void scmTestNullBranch() {
        final Scm scm = ScmFixture.newScmGithub();
        scm.setBranch(null);

        // set property
        final String key = "scm.branch";
        System.setProperty("scm.branch", key);

        final String property = loadProperties.scmBranch(scm);
        assertEquals(key, property);
    }

    @Test
    public void scmTestEmptyBranch() {
        final Scm scm = ScmFixture.newScmGithub();
        scm.setBranch("");

        // set property
        final String key = "scm.branch";
        System.setProperty("scm.branch", key);

        final String property = loadProperties.scmBranch(scm);
        assertEquals(key, property);
    }

}