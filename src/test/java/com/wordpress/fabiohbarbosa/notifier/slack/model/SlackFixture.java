package com.wordpress.fabiohbarbosa.notifier.slack.model;

public class SlackFixture {
    public static Slack newSlack() {
        return new Slack("slack.webhook", true);
    }
    public static Slack newSlackEnv(final Boolean onlyErrors) {
        String webhook = System.getProperty("slack.webhook");
        return new Slack(webhook, onlyErrors);
    }

    public static Slack newSlackEnv() {
        return newSlackEnv(true);
    }
}
