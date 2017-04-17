package com.wordpress.fabiohbarbosa.notifier.slack.model;

public class SlackFixture {
    public static Slack newSlack() {
        return new Slack("slack.webhook", SlackLevel.INFO);
    }
    public static Slack newSlackEnv(final SlackLevel level) {
        String webhook = System.getProperty("slack.webhook");
        return new Slack(webhook, level);
    }

    public static Slack newSlackEnv() {
        return newSlackEnv(SlackLevel.INFO);
    }
}
