package com.wordpress.fabiohbarbosa.notifier.sonar.model;

public class Sonar {
    private String key;
    private String url;
    private String login;
    private String password;
    private Double coverage;

    public Sonar() {
    }

    public Sonar(final String key, final String url, final String login, final String password, final Double coverage) {
        this.key = key;
        this.url = url;
        this.login = login;
        this.password = password;
        this.coverage = coverage;
    }

    public Sonar(final String key, final String url) {
        this.key = key;
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Double getCoverage() {
        return coverage;
    }

    public void setCoverage(Double coverage) {
        this.coverage = coverage;
    }

    @Override
    public String toString() {
        return "Sonar{" +
                "key='" + key + '\'' +
                ", url='" + url + '\'' +
                ", login='" + login + '\'' +
                ", coverage='" + coverage + '\'' +
                '}';
    }
}
