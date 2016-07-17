package br.com.gsw.slack.sonar.notifier.slack.model;

public class Slack {
    private String webhook;
    private Integer coverage;

    public Slack() {
    }

    public Slack(final String webhook, final Integer coverage) {
        this.webhook = webhook;
        this.coverage = coverage;
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(final String webhook) {
        this.webhook = webhook;
    }

    public Integer getCoverage() {
        return coverage;
    }

    public void setCoverage(final Integer coverage) {
        this.coverage = coverage;
    }

    @Override
    public String toString() {
        return "Slack{" +
                "webhook='" + webhook + '\'' +
                ", coverage=" + coverage +
                '}';
    }
}
