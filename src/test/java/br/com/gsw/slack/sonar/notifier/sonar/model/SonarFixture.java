package br.com.gsw.slack.sonar.notifier.sonar.model;

import org.apache.commons.lang3.StringUtils;

public class SonarFixture {
    public static Sonar newSonar() {
        return new Sonar("sonar.key", "sonar.host.url", "sonar.user", "sonar.password", 60.0);
    }
    public static Sonar newSonarAuthEnv() {
        final String key = System.getProperty("sonar.key");
        String url = System.getProperty("sonar.url");
        if (StringUtils.isEmpty(url)) {
             url = System.getProperty("sonar.host.url");
        }
        final String user = System.getProperty("sonar.user");
        final String password = System.getProperty("sonar.password");
        final String coverageProp = System.getProperty("sonar.coverage");

        Double coverage = 60.0;
        if (!StringUtils.isEmpty(coverageProp)) {
            coverage = Double.parseDouble(coverageProp);
        }

        if (StringUtils.isEmpty(user) || StringUtils.isEmpty(password)) {
            return newSonarNoAuthEnv(key, url);
        }
        return new Sonar(key, url, user, password, coverage);
    }

    private static Sonar newSonarNoAuthEnv(final String key, final String url) {
        return new Sonar(key, url);
    }
}