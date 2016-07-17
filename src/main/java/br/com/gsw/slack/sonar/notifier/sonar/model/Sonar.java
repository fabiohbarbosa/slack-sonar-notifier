package br.com.gsw.slack.sonar.notifier.sonar.model;

public class Sonar {
    private String key;
    private String url;
    private String user;
    private String password;

    public Sonar() {
    }

    public Sonar(final String key, final String url, final String user, final String password) {
        this.key = key;
        this.url = url;
        this.user = user;
        this.password = password;
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

    @Override
    public String toString() {
        return "Sonar{" +
                "key='" + key + '\'' +
                ", url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
