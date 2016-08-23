package br.com.gsw.slack.sonar.notifier.slack.adapter;

import br.com.gsw.slack.sonar.notifier.plugin.factory.LogFactory;
import br.com.gsw.slack.sonar.notifier.scm.model.Scm;
import br.com.gsw.slack.sonar.notifier.slack.util.SlackMessageHelper;
import br.com.gsw.slack.sonar.notifier.slack.web.model.Attachment;
import br.com.gsw.slack.sonar.notifier.slack.web.model.Field;
import br.com.gsw.slack.sonar.notifier.slack.web.model.SlackRequest;
import br.com.gsw.slack.sonar.notifier.sonar.model.SonarStats;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.Color;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.KeyMsr;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.ResourceResponse;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.Severity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.logging.Log;

import java.util.Map;

public class SlackRequestAdapter {
    private final static Log LOGGER = LogFactory.getInstance();

    public SlackRequest adapter(final SonarStats sonarStats, final Scm scm) {
        LOGGER.debug("Slack request adapter...");

        if (!isNeedToHook(sonarStats)) {
            LOGGER.info(String.format("No sonar stats found in project %s", sonarStats.getProject().getName()));
            return null;
        }

        return adapters(sonarStats, scm);
    }

    private SlackRequest adapters(final SonarStats sonarStats, final Scm scm) {
        final SlackRequest slackRequest = new SlackRequest();
        adapterProject(slackRequest, sonarStats);
        adapterScm(slackRequest, scm);
        adapterRatings(slackRequest, sonarStats);
        adapterIssues(slackRequest, sonarStats);
        adapterDuplications(slackRequest, sonarStats);
        adapterTests(slackRequest, sonarStats);
        return slackRequest;
    }

    private void adapterProject(final SlackRequest slackRequest, final SonarStats sonarStats) {
        final String sonarUrl = sonarStats.getSonar().getUrl();
        final String projectId = sonarStats.getProject().getId();
        final String projectName = sonarStats.getProject().getName();
        final String projectVersion = sonarStats.getProject().getVersion();

        final Attachment attachment = new Attachment();
        attachment.setColor(Color.PROJECT_COLOR.VALUE);
        attachment.setPreText("*PROJECT*");

        attachment.addField(new Field(String.format("*Name*: %s", SlackMessageHelper.projectUrl(sonarUrl, projectId, projectName)), false));
        attachment.addField(new Field(String.format("*Version*: %s", projectVersion), false));

        slackRequest.addAttachment(attachment);
    }

    protected void adapterScm(final SlackRequest slackRequest, final Scm scm) {
        final Attachment attachment = new Attachment();
        attachment.setColor(Color.SCM_COLOR.VALUE);
        attachment.setPreText("*SCM*");

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
        return new Field(String.format("*Branch*:\n %s", SlackMessageHelper.url(valueUrl, branch)), true);
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
        return new Field(String.format("*Commit*:\n %s", SlackMessageHelper.url(valueUrl, commit)), true);
    }

    private Field createScmField(final String value, final String title) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return new Field(String.format("*%s*:\n %s", title, value), true);
    }

    private void adapterRatings(final SlackRequest slackRequest, final SonarStats sonarStats) {
        final ResourceResponse ratings = sonarStats.getRatings();
        if (ratings == null) {
            LOGGER.debug(String.format("Not found rating for project %s", sonarStats.getProject().getId()));
            return;
        }
        final String sqaleRating = ratings.getFrmtVal(KeyMsr.SQALE_RATING);
        final String sqaleRatio = ratings.getFrmtVal(KeyMsr.SQALE_DEBT_RATIO);

        final Attachment attachment = new Attachment();
        attachment.setPreText("*TECHNICAL DEBT*");

        final String sonarUrl = sonarStats.getSonar().getUrl();
        final String projectId = sonarStats.getProject().getId();

        attachment.setColor(SlackMessageHelper.ratingColor(sqaleRating));
        attachment.addField(createRatingsField(sonarUrl, projectId, KeyMsr.SQALE_RATING, sqaleRating));
        attachment.addField(createRatingsField(sonarUrl, projectId, KeyMsr.SQALE_DEBT_RATIO, sqaleRatio));

        slackRequest.addAttachment(attachment);
    }

    private Field createRatingsField(final String sonarUrl, final String projectId, final KeyMsr keyMsr, final String value) {
        return new Field(SlackMessageHelper.ratingUrl(sonarUrl, projectId, keyMsr, value), false);
    }

    private void adapterIssues(final SlackRequest slackRequest, final SonarStats sonarStats) {
        final Map<Severity, Integer> issues = sonarStats.getIssues();
        if (issues == null || issues.size() == 0) {
            LOGGER.debug(String.format("Not found issues for project %s", sonarStats.getProject().getId()));
            return;
        }

        final Attachment attachment = new Attachment();

        final String sonarUrl = sonarStats.getSonar().getUrl();
        final String projectId = sonarStats.getProject().getId();

        attachment.setPreText("*ISSUES*");
        attachment.setColor(Color.ISSUES_COLOR.VALUE);

        attachment.addField(createIssuesField(sonarUrl, projectId, Severity.TOTAL, issues, false));
        attachment.addField(createIssuesField(sonarUrl, projectId, Severity.BLOCKER, issues, true));
        attachment.addField(createIssuesField(sonarUrl, projectId, Severity.CRITICAL, issues, true));
        attachment.addField(createIssuesField(sonarUrl, projectId, Severity.MAJOR, issues, true));
        attachment.addField(createIssuesField(sonarUrl, projectId, Severity.MINOR, issues, true));
        attachment.addField(createIssuesField(sonarUrl, projectId, Severity.INFO, issues, true));

        slackRequest.addAttachment(attachment);
    }

    private Field createIssuesField(final String sonarUrl, final String projectId, final Severity severity, final Map<Severity, Integer> issues, final boolean isShort) {
        return new Field(SlackMessageHelper.issueUrl(sonarUrl, projectId, severity, issues.get(severity)), isShort);
    }

    private void adapterDuplications(final SlackRequest slackRequest, final SonarStats sonarStats) {
        final ResourceResponse duplications = sonarStats.getDuplications();
        if (duplications == null) {
            LOGGER.debug(String.format("Not found duplications for project %s", sonarStats.getProject().getId()));
            return;
        }

        final Attachment attachment = new Attachment();

        final String sonarUrl = sonarStats.getSonar().getUrl();
        final String projectId = sonarStats.getProject().getId();

        attachment.setPreText(("*DUPLICATED*"));
        attachment.setColor(Color.ISSUES_COLOR.VALUE);

        final String duplicated = duplications.getFrmtVal(KeyMsr.DUPLICATED_LINES_DENSITY);
        final String lines = duplications.getFrmtVal(KeyMsr.DUPLICATED_LINES);
        final String blocks = duplications.getFrmtVal(KeyMsr.DUPLICATED_BLOCKS);
        final String files = duplications.getFrmtVal(KeyMsr.DUPLICATED_FILES);

        attachment.addField(createDuplicatedField(sonarUrl, projectId, KeyMsr.DUPLICATED_LINES_DENSITY, duplicated));
        attachment.addField(createDuplicatedField(sonarUrl, projectId, KeyMsr.DUPLICATED_LINES, lines));
        attachment.addField(createDuplicatedField(sonarUrl, projectId, KeyMsr.DUPLICATED_BLOCKS, blocks));
        attachment.addField(createDuplicatedField(sonarUrl, projectId, KeyMsr.DUPLICATED_FILES, files));

        slackRequest.addAttachment(attachment);
    }

    private Field createDuplicatedField(final String sonarUrl, final String projectId, final KeyMsr keyMsr, final String value) {
        return new Field(SlackMessageHelper.duplicatedUrl(sonarUrl, projectId, keyMsr, value), true);
    }

    private void adapterTests(final SlackRequest slackRequest, final SonarStats sonarStats) {
        final ResourceResponse tests = sonarStats.getTests();
        if (tests == null) {
            LOGGER.debug(String.format("Not found tests for project %s", sonarStats.getProject().getId()));
            return;
        }

        final Attachment attachment = new Attachment();

        final String sonarUrl = sonarStats.getSonar().getUrl();
        final String projectId = sonarStats.getProject().getId();

        attachment.setPreText(("*TESTS*"));
        attachment.setColor(Color.ISSUES_COLOR.VALUE);

        final String coverage = tests.getFrmtVal(KeyMsr.COVERAGE);
        final String success = tests.getFrmtVal(KeyMsr.TESTS_SUCCESS);
        final String total = tests.getFrmtVal(KeyMsr.TESTS_TOTAL);
        final String skipped = tests.getFrmtVal(KeyMsr.TESTS_SKIPPED);
        final String failures = tests.getFrmtVal(KeyMsr.TESTS_FAILURES);
        final String error = tests.getFrmtVal(KeyMsr.TESTS_ERROR);

        attachment.addField(createTestsField(sonarUrl, projectId, KeyMsr.COVERAGE, coverage));
        attachment.addField(createTestsField(sonarUrl, projectId, KeyMsr.TESTS_SUCCESS, success));
        attachment.addField(createTestsField(sonarUrl, projectId, KeyMsr.TESTS_TOTAL, total));
        attachment.addField(createTestsField(sonarUrl, projectId, KeyMsr.TESTS_SKIPPED, skipped));
        attachment.addField(createTestsField(sonarUrl, projectId, KeyMsr.TESTS_FAILURES, failures));
        attachment.addField(createTestsField(sonarUrl, projectId, KeyMsr.TESTS_ERROR, error));

        slackRequest.addAttachment(attachment);
    }

    private Field createTestsField(final String sonarUrl, final String projectId, final KeyMsr keyKsr, final String total) {
        return new Field(SlackMessageHelper.testsUrl(sonarUrl, projectId, keyKsr, total), true);
    }

    private boolean isNeedToHook(final SonarStats sonarStats) {
        return sonarStats.getRatings() != null ||
               (sonarStats.getIssues() != null && sonarStats.getIssues().size() > 0) ||
               sonarStats.getDuplications() != null ||
               sonarStats.getTests() != null;
    }
}
