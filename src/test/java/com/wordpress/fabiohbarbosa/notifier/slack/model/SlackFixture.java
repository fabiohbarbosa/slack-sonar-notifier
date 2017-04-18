package com.wordpress.fabiohbarbosa.notifier.slack.model;

public class SlackFixture {
    public static Slack newSlack() {
        return new Slack("slack.webhook");
    }
    public static Slack newSlackEnv() {
        String webhook = System.getProperty("slack.webhook");
        return new Slack(webhook);
    }
}
