package br.com.gsw.slack.sonar.notifier.slack.util;

import br.com.gsw.slack.sonar.notifier.sonar.web.model.KeyMsr;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.Severity;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.Color;
import org.apache.commons.lang3.text.WordUtils;

public class SlackMessageHelper {
    private SlackMessageHelper() {
        // Block constructor
    }

    public static String projectUrl(final String sonarUrl, final String projectId, final String projectName) {
        return String.format("<%s/dashboard/index?id=%s| SonarQube: %s>", sonarUrl, projectId, projectName.toUpperCase());
    }

    public static String ratingColor(final String sqaleRating) {
        return Color.valueOf("RATING_"+sqaleRating).VALUE;
    }

    public static String allIssuesUrl(final String sonarUrl, final String projectId) {
        return String.format("%s/component_issues?id=%s#resolved=false", sonarUrl, projectId);
    }

    public static String issueUrl(final String sonarUrl, final String projectId, final Severity severity, final Integer totalIssues) {
        return "<" + sonarUrl + "/component_issues?id=" + projectId + "#resolved=false%7Cseverities=" + severity.name() + "|" + WordUtils.capitalize(severity.name().toLowerCase()) + ">: " + totalIssues;
    }

    public static String duplicatedUrl(final String sonarUrl, final String projectId, final KeyMsr keyKsr, final String value) {
        return String.format("<%s/drilldown/measures/%s?metric=%s|%s>: %s", sonarUrl, projectId, keyKsr.KEY, keyKsr.TEXT, value);
    }

    public static String testsUrl(final String sonarUrl, final String projectId, final KeyMsr keyKsr, final String total) {
        return String.format("<%s/drilldown/measures/%s?metric=%s|%s>: %s", sonarUrl, projectId, keyKsr.KEY, keyKsr.TEXT, total);
    }

    public static String ratingUrl(final String sonarUrl, final String projectId, final KeyMsr keyMsr, final String value) {
        return String.format("<%s/drilldown/measures/%s?metric=%s|%s>: %s", sonarUrl, projectId, keyMsr.KEY, keyMsr.TEXT, value);
    }
}
