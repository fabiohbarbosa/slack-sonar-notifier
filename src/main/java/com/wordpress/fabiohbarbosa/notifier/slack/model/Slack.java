package com.wordpress.fabiohbarbosa.notifier.slack.model;

public class Slack {
    private String webhook;

    public Slack() {
    }

    public Slack(final String webhook) {
        this.webhook = webhook;
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(final String webhook) {
        this.webhook = webhook;
    }

    @Override
    public String toString() {
        return "Slack{" +
                "webhook='" + webhook + '\'' +
                '}';
    }
}
