package com.wordpress.fabiohbarbosa.notifier.slack.util;

import org.apache.commons.lang3.StringUtils;

public final class SlackMessageHelper {
    private SlackMessageHelper() {
        // Block constructor
    }

    public static String projectUrl(final String sonarUrl, final String projectId, final String projectName) {
        return String.format("<%s/dashboard/index?id=%s| %s>", sonarUrl, projectId, projectName.toUpperCase());
    }

    public static String url(final String valueUrl, final String value) {
        if (StringUtils.isEmpty(valueUrl)) {
            return value;
        }
        return String.format("<%s|%s>", valueUrl, value);
    }

}
