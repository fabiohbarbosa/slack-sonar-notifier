package br.com.gsw.slack.sonar.notifier.plugin.service;

import br.com.gsw.slack.sonar.notifier.PrepareFactoryTests;
import br.com.gsw.slack.sonar.notifier.plugin.factory.PluginLoadPropertiesFactory;
import br.com.gsw.slack.sonar.notifier.scm.model.Scm;
import br.com.gsw.slack.sonar.notifier.scm.model.ScmFixture;
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

    //~-- sonar.user
    @Test
    public void sonarTestNullUser() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setUser(null);

        // set property
        final String user = "sonar.user";
        System.setProperty("sonar.user", user);

        final String property = loadProperties.sonarUser(sonar);
        assertEquals(user, property);
    }

    @Test
    public void sonarTestEmptyUser() {
        final Sonar sonar = SonarFixture.newSonar();
        sonar.setUser("");

        // set property
        final String user = "sonar.user";
        System.setProperty("sonar.user", user);

        final String property = loadProperties.sonarUser(sonar);
        assertEquals(user, property);
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

    //~-- sonar.coverage
    @Test
    public void slackTestNullCoverage() {
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        sonar.setCoverage(null);

        // set property
        final String coverage = "1";
        System.setProperty("sonar.coverage", coverage);

        final Double property = loadProperties.sonarCoverage(sonar);
        assertTrue(property == Double.parseDouble(coverage));
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

    // slack.onlyErrors
    @Test
    public void slackTestNullOnlyErrors() {
        final Slack slack = SlackFixture.newSlackEnv();
        slack.setOnlyErrors(null);

        // set property
        final String onlyErrors = "false";
        System.setProperty("slack.onlyErrors", onlyErrors);

        final Boolean property = loadProperties.slackOnlyErrors(slack);
        assertEquals(Boolean.parseBoolean(onlyErrors), property);
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