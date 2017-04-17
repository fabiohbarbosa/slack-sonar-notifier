package com.wordpress.fabiohbarbosa.notifier.slack.adapter;

import com.wordpress.fabiohbarbosa.notifier.plugin.factory.LogFactory;
import com.wordpress.fabiohbarbosa.notifier.scm.model.Scm;
import com.wordpress.fabiohbarbosa.notifier.slack.util.SlackMessageHelper;
import com.wordpress.fabiohbarbosa.notifier.slack.web.model.Attachment;
import com.wordpress.fabiohbarbosa.notifier.slack.web.model.Field;
import com.wordpress.fabiohbarbosa.notifier.slack.web.model.SlackRequest;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.SonarStats;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.Color;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.Condition;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.ProjectStatus;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.logging.Log;

import java.util.List;

public class SlackRequestAdapter {
    private static final Log LOGGER = LogFactory.getInstance();

    public SlackRequest adapter(final SonarStats sonarStats, final Scm scm) {
        LOGGER.debug("Slack request adapter...");
        return adapters(sonarStats, scm);
    }

    protected SlackRequest adapters(final SonarStats sonarStats, final Scm scm) {
        final SlackRequest slackRequest = new SlackRequest();
        adapterProject(slackRequest, sonarStats);
        adapterScm(slackRequest, scm);
        adapterQualityGates(slackRequest, sonarStats.getQualityGate().getProjectStatus());
        return slackRequest;
    }

    protected void adapterQualityGates(final SlackRequest slackRequest, final ProjectStatus projectStatus) {
        final Attachment attach = new Attachment();
        attach.setPreText("*Quality Gates*");
        attach.setColor(projectStatus.getStatus().COLOR.VALUE);

        List<Condition> conditions = projectStatus.getConditions();

        for (Condition c : conditions) {
            String title = String.format("%s: %n*%s*", c.getMetricKey().NAME, c.getStatus().name());
            attach.addField(new Field(title, true));
        }
        slackRequest.addAttachment(attach);
    }

    protected void adapterProject(final SlackRequest slackRequest, final SonarStats sonarStats) {
        final String sonarUrl = sonarStats.getSonar().getUrl();
        final String projectId = sonarStats.getProject().getId();
        final String projectName = sonarStats.getProject().getName();
        final String projectVersion = sonarStats.getProject().getVersion();

        LOGGER.debug(String.format("Adapter project received %s", sonarStats));

        final Attachment attachment = new Attachment();
        attachment.setColor(Color.PROJECT_COLOR.VALUE);
        attachment.setPreText("*Project*");

        attachment.addField(new Field(String.format("*Name*: %s", SlackMessageHelper.projectUrl(sonarUrl, projectId, projectName)), false));
        attachment.addField(new Field(String.format("*Version*: %s", projectVersion), false));

        slackRequest.addAttachment(attachment);
    }

    protected void adapterScm(final SlackRequest slackRequest, final Scm scm) {
        final Attachment attachment = new Attachment();
        attachment.setColor(Color.SCM_COLOR.VALUE);
        attachment.setPreText("*SCM*");

        LOGGER.debug(String.format("Adapter SCM received %s", scm));

        attachment.addField(createScmField(scm.getUrl(), "URL"));
        attachment.addField(createScmBranchField(scm));
        attachment.addField(createScmField(scm.getUser(), "User"));
        attachment.addField(createScmCommitField(scm));

        if (!CollectionUtils.isEmpty(attachment.getFields())) {
            slackRequest.addAttachment(attachment);
        }
    }

    protected Field createScmBranchField(final Scm scm) {
        final String url = Scm.normalizeUrl(scm.getUrl());
        final String branch = Scm.removeOrigin(scm.getBranch());

        if (StringUtils.isEmpty(branch)) {
            return null;
        }

        String valueUrl = "";
        if (!StringUtils.isEmpty(url)) {
            if (url.contains("github")) {
                valueUrl = url + "/tree/" + branch;
            }
            if (url.contains("bitbucket")) {
                valueUrl = url + "/src/?at=" + branch;
            }
        }
        return new Field(String.format("*Branch*:%n %s", SlackMessageHelper.url(valueUrl, branch)), true);
    }

    protected Field createScmCommitField(final Scm scm) {
        final String url = Scm.normalizeUrl(scm.getUrl());
        final String commit = Scm.normalizeCommit(scm.getCommit());

        if (StringUtils.isEmpty(commit)) {
            return null;
        }

        String valueUrl = "";
        if (!StringUtils.isEmpty(url)) {
            if (url.contains("github")) {
                valueUrl = url + "/commit/" + commit;
            }
            if (url.contains("bitbucket")) {
                valueUrl = url + "/commits/" + commit;
            }
        }
        return new Field(String.format("*Commit*:%n %s", SlackMessageHelper.url(valueUrl, commit)), true);
    }

    protected Field createScmField(final String value, final String title) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return new Field(String.format("*%s*:%n %s", title, value), true);
    }

}
