package br.com.gsw.slack.sonar.notifier.slack.model;

import org.apache.commons.lang3.StringUtils;

public class SlackFixture {
    public static Slack newSlack() {
        String webhook = System.getProperty("slack.webhook");
        String coverage = System.getProperty("slack.coverage");

        Integer coverageInt = 60;
        if (!StringUtils.isEmpty(coverage)) {
            coverageInt = Integer.parseInt(coverage);
        }

        return new Slack(webhook, coverageInt);
    }
}
