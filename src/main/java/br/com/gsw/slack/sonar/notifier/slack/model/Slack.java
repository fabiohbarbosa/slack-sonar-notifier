package br.com.gsw.slack.sonar.notifier.slack.model;

public class Slack {
    private String webhook;
    private Boolean onlyErrors;
    private Integer coverage;

    public Slack() {
    }

    public Slack(final String webhook, final Boolean onlyErrors, final Integer coverage) {
        this.webhook = webhook;
        this.onlyErrors = onlyErrors;
        this.coverage = coverage;
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
