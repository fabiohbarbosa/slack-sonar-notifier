package com.wordpress.fabiohbarbosa.notifier.sonar.model;

import org.apache.commons.lang3.StringUtils;

public class SonarFixture {
    public static Sonar newSonar() {
        return new Sonar("sonar.key", "sonar.host.url", "sonar.login", "sonar.password");
    }
    public static Sonar newSonarAuthEnv() {
        final String key = System.getProperty("sonar.key");
        String url = System.getProperty("sonar.url");
        if (StringUtils.isEmpty(url)) {
             url = System.getProperty("sonar.host.url");
        }
        final String user = System.getProperty("sonar.login");
        final String password = System.getProperty("sonar.password");

        if (StringUtils.isEmpty(user) || StringUtils.isEmpty(password)) {
            return newSonarNoAuthEnv(key, url);
        }
        return new Sonar(key, url, user, password);
    }

    private static Sonar newSonarNoAuthEnv(final String key, final String url) {
        return new Sonar(key, url);
    }
}