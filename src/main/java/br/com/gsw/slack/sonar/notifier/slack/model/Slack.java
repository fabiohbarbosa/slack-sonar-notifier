package br.com.gsw.slack.sonar.notifier.slack.model;

public class Slack {
    private String webhook;
    private Boolean onlyErrors;

    public Slack() {
    }

    public Slack(final String webhook, final Boolean onlyErrors) {
        this.webhook = webhook;
        this.onlyErrors = onlyErrors;
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(final String webhook) {
        this.webhook = webhook;
    }

    public Boolean getOnlyErrors() {
        return onlyErrors;
    }

    public void setOnlyErrors(final Boolean onlyErrors) {
        this.onlyErrors = onlyErrors;
    }

    @Override
    public String toString() {
        return "Slack{" +
                "webhook='" + webhook + '\'' +
                "onlyErrors='" + onlyErrors + '\'' +
                '}';
    }
}
