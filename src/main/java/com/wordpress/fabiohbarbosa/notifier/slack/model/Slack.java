package com.wordpress.fabiohbarbosa.notifier.slack.model;

public class Slack {
    private String webhook;
    private SlackLevel level = SlackLevel.WARNING;

    public Slack() {
    }

    public Slack(final String webhook, final SlackLevel level) {
        this.webhook = webhook;
        this.level = level;
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(final String webhook) {
        this.webhook = webhook;
    }

    public SlackLevel getLevel() {
        return level;
    }

    public void setLevel(final SlackLevel level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Slack{" +
                "webhook='" + webhook + '\'' +
                "level='" + level + '\'' +
                '}';
    }
}
