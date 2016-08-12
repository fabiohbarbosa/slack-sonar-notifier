package br.com.gsw.slack.sonar.notifier.sonar.model;

public class Sonar {
    private String key;
    private String url;
    private String user;
    private String password;
    private Double coverage;

    public Sonar() {
    }

    public Sonar(final String key, final String url, final String user, final String password, final Double coverage) {
        this.key = key;
        this.url = url;
        this.user = user;
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

    public String getUser() {
        return user;
    }

    public void setUser(final String user) {
        this.user = user;
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
                ", user='" + user + '\'' +
                ", coverage='" + coverage + '\'' +
                '}';
    }

    public static Sonar createOrClone(final Sonar sonar) {
        if (sonar != null) {
            return new Sonar(sonar.getKey(), sonar.getUrl(), sonar.getUser(),sonar.getPassword(), sonar.getCoverage());
        }
        return new Sonar();
    }
}
