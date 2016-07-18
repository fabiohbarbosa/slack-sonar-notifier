package br.com.gsw.slack.sonar.notifier.sonar.factory;

import br.com.gsw.slack.sonar.notifier.sonar.adapter.SonarAdapter;

public class SonarAdapterFactory {
    private static SonarAdapter sonarAdapter;

    private SonarAdapterFactory() {
        // block constructor
    }

    public static SonarAdapter getInstance() {
        if (sonarAdapter == null) {
            sonarAdapter = new SonarAdapter();
        }
        return sonarAdapter;
    }
}
