package br.com.gsw.slack.sonar.notifier.slack.util;

import br.com.gsw.slack.sonar.notifier.sonar.web.model.Color;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.KeyMsr;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.Severity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

public final class SlackMessageHelper {
    private SlackMessageHelper() {
        // Block constructor
    }

    public static String projectUrl(final String sonarUrl, final String projectId, final String projectName) {
        return String.format("<%s/dashboard/index?id=%s| %s>", sonarUrl, projectId, projectName.toUpperCase());
    }

    public static String ratingColor(final String sqaleRating) {
        return Color.valueOf("RATING_"+sqaleRating).VALUE;
    }

    public static String allIssuesUrl(final String sonarUrl, final String projectId) {
        return String.format("%s/component_issues?id=%s#resolved=false", sonarUrl, projectId);
    }

    public static String issueUrl(final String sonarUrl, final String projectId, final Severity severity, final Integer value) {
        final Integer valueUrl = defaultValueInteger(value);
        return "<" + sonarUrl + "/component_issues?id=" + projectId + "#resolved=false%7Cseverities=" + severity.name() + "|" + WordUtils.capitalize(severity.name().toLowerCase()) + ">: " + valueUrl;
    }

    public static String duplicatedUrl(final String sonarUrl, final String projectId, final KeyMsr keyKsr, final String value) {
        return String.format("<%s/drilldown/measures/%s?metric=%s|%s>: %s", sonarUrl, projectId, keyKsr.KEY, keyKsr.TEXT, defaultValueString(value));
    }

    public static String testsUrl(final String sonarUrl, final String projectId, final KeyMsr keyKsr, final String value) {
        return String.format("<%s/drilldown/measures/%s?metric=%s|%s>: %s", sonarUrl, projectId, keyKsr.KEY, keyKsr.TEXT, defaultValueString(value));
    }

    public static String ratingUrl(final String sonarUrl, final String projectId, final KeyMsr keyMsr, final String value) {
        return String.format("<%s/drilldown/measures/%s?metric=%s|%s>: %s", sonarUrl, projectId, keyMsr.KEY, keyMsr.TEXT, defaultValueString(value));
    }

    private static String defaultValueString(final String value) {
        String valueUrl = value;
        if (StringUtils.isEmpty(value)) {
            valueUrl = "-";
        }
        return valueUrl;
    }

    private static Integer defaultValueInteger(final Integer value) {
        Integer valueUrl = value;
        if (value == null) {
            valueUrl = 0;
        }
        return valueUrl;
    }

    public static String url(final String valueUrl, final String value) {
        if (StringUtils.isEmpty(valueUrl)) {
            return value;
        }
        return String.format("<%s|%s>", valueUrl, value);
    }
}
