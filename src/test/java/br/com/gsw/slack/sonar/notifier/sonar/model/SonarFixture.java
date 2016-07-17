package br.com.gsw.slack.sonar.notifier.sonar.model;

import org.apache.commons.lang3.StringUtils;

public class SonarFixture {
    public static Sonar newSonarAuth() {
        String key = System.getProperty("sonar.key");
        String url = System.getProperty("sonar.url");
        String user = System.getProperty("sonar.user");
        String password = System.getProperty("sonar.password");

        if (StringUtils.isEmpty(user) || StringUtils.isEmpty(password)) {
            return newSonarNoAuth(key, url);
        }
        return new Sonar(key, url, user, password);
    }

    private static Sonar newSonarNoAuth(final String sonarkey, final String sonarUrl) {
        return new Sonar(sonarkey, sonarUrl);
    }
}