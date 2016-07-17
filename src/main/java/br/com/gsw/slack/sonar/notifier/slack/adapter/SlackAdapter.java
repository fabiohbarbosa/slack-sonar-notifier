package br.com.gsw.slack.sonar.notifier.slack.adapter;

import br.com.gsw.slack.sonar.notifier.web.client.FeignFactory;
import br.com.gsw.slack.sonar.notifier.plugin.factory.LogFactory;
import br.com.gsw.slack.sonar.notifier.slack.model.Slack;
import br.com.gsw.slack.sonar.notifier.slack.util.SlackMessageHelper;
import br.com.gsw.slack.sonar.notifier.slack.web.client.SlackRestClient;
import br.com.gsw.slack.sonar.notifier.slack.web.model.Attachment;
import br.com.gsw.slack.sonar.notifier.slack.web.model.Field;
import br.com.gsw.slack.sonar.notifier.slack.web.model.SlackRequest;
import br.com.gsw.slack.sonar.notifier.sonar.model.SonarStats;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.Color;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.KeyMsr;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.ResourceResponse;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.Severity;
import com.google.gson.Gson;
import org.apache.maven.plugin.logging.Log;

import java.util.Map;

public class SlackAdapter {
    private final static String SLACK_URL = "https://hooks.slack.com/services/";

    private Log log = LogFactory.getInstance();
    private SlackRestClient client;

    public SlackAdapter() {
        log = LogFactory.getInstance();
    }

    public void adapter(final Slack slack, final SonarStats sonarStats) {
        log.debug("Slack adapter...");

        if (!isNeedToHook(sonarStats)) {
            log.info(String.format("No sonar problems found in project %s", sonarStats.getProject().getName()));
            return;
        }

        SlackRequest slackRequest  = new SlackRequest();

        adapterDefault(slackRequest, sonarStats);
        adapterRatings(slackRequest, sonarStats);
        adapterIssues(slackRequest, sonarStats);
        adapterDuplications(slackRequest, sonarStats);
        adapterTests(slackRequest, sonarStats);
        slackPusher(slack, slackRequest);
    }

    private void slackPusher(final Slack slack, final SlackRequest slackRequest) {
        log.info(String.format("Trying to post sonar stats into slack %s", slack.getWebhook()));

        final Gson gson = new Gson();
        log.debug(gson.toJson(slackRequest));

        final String token = slack.getWebhook().split(SLACK_URL)[1];

        client = FeignFactory.build(SLACK_URL, SlackRestClient.class);
        client.post(token, slackRequest);

        log.info(String.format("Notifier slack %s", slack.getWebhook()));
    }

    private void adapterDefault(final SlackRequest slackRequest, final SonarStats sonarStats) {
        slackRequest.setMrkdwn(true);

        final String sonarUrl = sonarStats.getSonar().getUrl();
        final String projectId = sonarStats.getProject().getId();
        final String projectName = sonarStats.getProject().getName();

        slackRequest.setText(SlackMessageHelper.projectUrl(sonarUrl, projectId, projectName));
    }

    private void adapterRatings(final SlackRequest slackRequest, final SonarStats sonarStats) {
        final ResourceResponse ratings = sonarStats.getRatings();
        if (ratings == null) {
            log.debug(String.format("Not found rating for project %s", sonarStats.getProject().getId()));
            return;
        }
        final String sqaleRating = ratings.getFrmtVal(KeyMsr.SQALE_RATING);
        final String sqaleRatio = ratings.getFrmtVal(KeyMsr.SQALE_DEBT_RATIO);

        final Attachment attachment = new Attachment();

        final String sonarUrl = sonarStats.getSonar().getUrl();
        final String projectId = sonarStats.getProject().getId();

        attachment.setColor(SlackMessageHelper.ratingColor(sqaleRating));
        attachment.addField(createFieldForRatings(sonarUrl, projectId, KeyMsr.SQALE_RATING, sqaleRating));
        attachment.addField(createFieldForRatings(sonarUrl, projectId, KeyMsr.SQALE_DEBT_RATIO, sqaleRatio));

        slackRequest.addAttachment(attachment);
    }

    private Field createFieldForRatings(final String sonarUrl, final String projectId, final KeyMsr keyMsr, final String value) {
        return new Field(SlackMessageHelper.ratingUrl(sonarUrl, projectId, keyMsr, value), true);
    }

    private void adapterIssues(final SlackRequest slackRequest, final SonarStats sonarStats) {
        final Map<Severity, Integer> issues = sonarStats.getIssues();
        if (issues == null || issues.size() == 0) {
            log.debug(String.format("Not found issues for project %s", sonarStats.getProject().getId()));
            return;
        }

        final Attachment attachment = new Attachment();

        final String sonarUrl = sonarStats.getSonar().getUrl();
        final String projectId = sonarStats.getProject().getId();

        attachment.setTitle(String.format("ISSUES: %s", issues.get(Severity.TOTAL)));
        attachment.setTitleLink(SlackMessageHelper.allIssuesUrl(sonarUrl, projectId));
        attachment.setColor(Color.ISSUES_COLOR.VALUE);

        attachment.addField(createFieldForIssue(sonarUrl, projectId, Severity.BLOCKER, issues));
        attachment.addField(createFieldForIssue(sonarUrl, projectId, Severity.CRITICAL, issues));
        attachment.addField(createFieldForIssue(sonarUrl, projectId, Severity.MAJOR, issues));
        attachment.addField(createFieldForIssue(sonarUrl, projectId, Severity.MINOR, issues));
        attachment.addField(createFieldForIssue(sonarUrl, projectId, Severity.INFO, issues));

        slackRequest.addAttachment(attachment);
    }

    private Field createFieldForIssue(final String sonarUrl, final String projectId, final Severity severity, final Map<Severity, Integer> issues) {
        return new Field(SlackMessageHelper.issueUrl(sonarUrl, projectId, severity, issues.get(severity)), true);
    }

    private void adapterDuplications(final SlackRequest slackRequest, final SonarStats sonarStats) {
        final ResourceResponse duplications = sonarStats.getDuplications();
        if (duplications == null) {
            log.debug(String.format("Not found duplications for project %s", sonarStats.getProject().getId()));
            return;
        }

        final Attachment attachment = new Attachment();

        final String sonarUrl = sonarStats.getSonar().getUrl();
        final String projectId = sonarStats.getProject().getId();

        attachment.setTitle(("DUPLICATED"));
        attachment.setColor(Color.ISSUES_COLOR.VALUE);

        final String duplicated = duplications.getFrmtVal(KeyMsr.DUPLICATED_LINES_DENSITY);
        final String lines = duplications.getFrmtVal(KeyMsr.DUPLICATED_LINES);
        final String blocks = duplications.getFrmtVal(KeyMsr.DUPLICATED_BLOCKS);
        final String files = duplications.getFrmtVal(KeyMsr.DUPLICATED_FILES);

        attachment.addField(createFieldDuplicated(sonarUrl, projectId, KeyMsr.DUPLICATED_LINES_DENSITY, duplicated));
        attachment.addField(createFieldDuplicated(sonarUrl, projectId, KeyMsr.DUPLICATED_LINES, lines));
        attachment.addField(createFieldDuplicated(sonarUrl, projectId, KeyMsr.DUPLICATED_BLOCKS, blocks));
        attachment.addField(createFieldDuplicated(sonarUrl, projectId, KeyMsr.DUPLICATED_FILES, files));

        slackRequest.addAttachment(attachment);
    }

    private Field createFieldDuplicated(final String sonarUrl, final String projectId, final KeyMsr keyMsr, final String value) {
        return new Field(SlackMessageHelper.duplicatedUrl(sonarUrl, projectId, keyMsr, value), true);
    }

    private void adapterTests(final SlackRequest slackRequest, final SonarStats sonarStats) {
        final ResourceResponse tests = sonarStats.getTests();
        if (tests == null) {
            log.debug(String.format("Not found tests for project %s", sonarStats.getProject().getId()));
            return;
        }

        final Attachment attachment = new Attachment();

        final String sonarUrl = sonarStats.getSonar().getUrl();
        final String projectId = sonarStats.getProject().getId();

        attachment.setTitle(("TESTS"));
        attachment.setColor(Color.ISSUES_COLOR.VALUE);

        final String total = tests.getFrmtVal(KeyMsr.TESTS_TOTAL);
        final String error = tests.getFrmtVal(KeyMsr.TESTS_ERROR);
        final String skipped = tests.getFrmtVal(KeyMsr.TESTS_SKIPPED);
        final String failures = tests.getFrmtVal(KeyMsr.TESTS_FAILURES);
        final String success = tests.getFrmtVal(KeyMsr.TESTS_SUCCESS);
        final String coverage = tests.getFrmtVal(KeyMsr.COVERAGE);

        attachment.addField(createFieldTests(sonarUrl, projectId, KeyMsr.TESTS_TOTAL, total));
        attachment.addField(createFieldTests(sonarUrl, projectId, KeyMsr.TESTS_ERROR, error));
        attachment.addField(createFieldTests(sonarUrl, projectId, KeyMsr.TESTS_SKIPPED, skipped));
        attachment.addField(createFieldTests(sonarUrl, projectId, KeyMsr.TESTS_FAILURES, failures));
        attachment.addField(createFieldTests(sonarUrl, projectId, KeyMsr.TESTS_SUCCESS, success));
        attachment.addField(createFieldTests(sonarUrl, projectId, KeyMsr.COVERAGE, coverage));

        slackRequest.addAttachment(attachment);
    }

    private Field createFieldTests(final String sonarUrl, final String projectId, final KeyMsr keyKsr, final String total) {
        return new Field(SlackMessageHelper.testsUrl(sonarUrl, projectId, keyKsr, total), true);
    }

    private boolean isNeedToHook(final SonarStats sonarStats) {
        return sonarStats.getRatings() != null ||
               (sonarStats.getIssues() != null && sonarStats.getIssues().size() > 0) ||
               sonarStats.getDuplications() != null ||
               sonarStats.getTests() != null;
    }
}
