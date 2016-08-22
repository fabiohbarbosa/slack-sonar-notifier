package br.com.gsw.slack.sonar.notifier.plugin.service;

import br.com.gsw.slack.sonar.notifier.plugin.factory.LogFactory;
import br.com.gsw.slack.sonar.notifier.scm.model.Scm;
import br.com.gsw.slack.sonar.notifier.slack.model.Slack;
import br.com.gsw.slack.sonar.notifier.sonar.model.Sonar;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class PluginLoadProperties {
    private static final Log LOGGER = LogFactory.getInstance();

    //-- sonar
    public Sonar sonar(Sonar sonar, final MavenProject mavenProject) {
        if (sonar == null) {
            sonar = new Sonar();
        }
        Sonar sonarProp = new Sonar(sonar.getKey(), sonar.getUrl(), sonar.getLogin(),sonar.getPassword(), sonar.getCoverage());

        LOGGER.debug("Sonar properties");
        LOGGER.debug(sonarProp.toString());

        sonarProp.setKey(sonarKey(sonarProp, mavenProject));
        sonarProp.setUrl(sonarUrl(sonarProp));
        sonarProp.setLogin(sonarLogin(sonarProp));
        sonarProp.setPassword(sonarPassword(sonarProp));
        sonarProp.setCoverage(sonarCoverage(sonarProp));

        return sonarProp;
    }

    protected String sonarKey(final Sonar sonarProp, final MavenProject project) {
        String property = sonarProp.getKey();
        if (isEmpty(property)) {
            property = getProperty("sonar.key");
        }
        if (isEmpty(property)) {
            LOGGER.debug(String.format("Load %s from project artifactId and groupId", "sonar.key"));
            property = project.getGroupId()+":"+project.getArtifactId();
        }
        return property;
    }

    protected String sonarUrl(final Sonar sonarProp) {
        String property = sonarProp.getUrl();
        if (isEmpty(property)) {
            property = getProperty("sonar.url");
        }
        if (isEmpty(property)) {
            property = getProperty("sonar.host.url");
        }
        return property;
    }

    protected String sonarPassword(final Sonar sonarProp) {
        String property = sonarProp.getPassword();
        if (isEmpty(property)) {
            property = getProperty("sonar.password");
        }
        return property;
    }

    protected String sonarLogin(final Sonar sonarProp) {
        String property = sonarProp.getLogin();
        if (isEmpty(property)) {
            property = getProperty("sonar.login");
        }
        return property;
    }

    protected Double sonarCoverage(final Sonar sonarProp) {
        Double property = sonarProp.getCoverage();
        if (property == null) {
            if (!isEmpty(getProperty("sonar.coverage"))) {
                property = Double.parseDouble(getProperty("sonar.coverage"));
            }
        }
        return property;
    }

    //-- slack
    public Slack slack(Slack slack) {
        if (slack == null) {
            slack = new Slack();
        }

        Slack slackProp = new Slack(slack.getWebhook(), slack.getOnlyErrors());

        LOGGER.debug("Slack properties");
        LOGGER.debug(slackProp.toString());

        slackProp.setWebhook(slackWebhook(slackProp));
        slackProp.setOnlyErrors(slackOnlyErrors(slackProp));

        return slackProp;
    }

    protected String slackWebhook(final Slack slackProp) {
        String property = slackProp.getWebhook();
        if (isEmpty(property)) {
            property = getProperty("slack.webhook");
        }
        return property;
    }

    protected Boolean slackOnlyErrors(final Slack slackProp) {
        Boolean property = slackProp.getOnlyErrors();
        if (property == null) {
            if (!isEmpty(getProperty("slack.onlyErrors"))) {
                property = Boolean.valueOf(getProperty("slack.onlyErrors"));
            }
        }
        return property;
    }

    //-- scm
    public Scm scm(Scm scm) {
        if (scm == null) {
            scm = new Scm();
        }
        Scm scmProp = new Scm(scm.getUrl(), scm.getBranch(), scm.getUser(), scm.getCommit());

        LOGGER.debug("Scm properties");
        LOGGER.debug(scmProp.toString());

        scmProp.setUrl(scmUrl(scmProp));
        scmProp.setBranch(scmBranch(scmProp));
        scmProp.setUser(scmUser(scmProp));
        scmProp.setCommit(scmCommit(scmProp));

        return scmProp;
    }

    private String scmUrl(final Scm scmProp) {
        String property = scmProp.getUser();
        if (isEmpty(property)) {
            property = getProperty("scm.url");
        }
        return property;
    }

    protected String scmUser(final Scm scmProp) {
        String property = scmProp.getUser();
        if (isEmpty(property)) {
            property = getProperty("scm.user");
        }
        return property;
    }

    protected String scmBranch(final Scm scmProp) {
        String property = scmProp.getBranch();
        if (isEmpty(property)) {
            property = getProperty("scm.branch");
        }
        return property;
    }

    private String scmCommit(final Scm scmProp) {
        String property = scmProp.getUser();
        if (isEmpty(property)) {
            property = getProperty("scm.commit");
        }
        return property;
    }

    private String getProperty(final String property) {
        final String sysProp = System.getProperty(property);
        LOGGER.debug(String.format("Read system property '%s' to :%s", property, sysProp));
        return sysProp;
    }
}
