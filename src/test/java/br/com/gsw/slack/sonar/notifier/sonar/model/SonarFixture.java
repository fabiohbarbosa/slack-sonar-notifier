package br.com.gsw.slack.sonar.notifier.sonar.model;

import org.apache.commons.lang3.StringUtils;

public class SonarFixture {
    public static Sonar newSonar() {
        return new Sonar("sonar.key", "sonar.url", "sonar.user", "sonar.password");
    }
    public static Sonar newSonarAuthEnv() {
        String key = System.getProperty("sonar.key");
        String url = System.getProperty("sonar.url");
        String user = System.getProperty("sonar.user");
        String password = System.getProperty("sonar.password");

        if (StringUtils.isEmpty(user) || StringUtils.isEmpty(password)) {
            return newSonarNoAuthEnv(key, url);
        }
        return new Sonar(key, url, user, password);
    }

    private static Sonar newSonarNoAuthEnv(final String key, final String url) {
        return new Sonar(key, url);
    }
}