package br.com.gsw.slack.sonar.notifier.sonar.model;

import org.apache.commons.lang3.StringUtils;

public class SonarFixture {
    public static Sonar newSonarAuth() {
        String sonarkey = System.getProperty("sonar.project.key");
        String sonarUrl = System.getProperty("sonar.url");
        String sonarUser = System.getProperty("sonar.user");
        String sonarPass = System.getProperty("sonar.password");

        if (StringUtils.isEmpty(sonarUser) || StringUtils.isEmpty(sonarPass)) {
            return newSonarNoAuth(sonarkey, sonarUrl);
        }
        return new Sonar(sonarkey, sonarUrl, sonarUser, sonarPass);
    }

    private static Sonar newSonarNoAuth(final String sonarkey, final String sonarUrl) {
        return new Sonar(sonarkey, sonarUrl);
    }
}