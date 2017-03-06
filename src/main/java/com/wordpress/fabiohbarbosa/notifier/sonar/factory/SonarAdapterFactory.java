package com.wordpress.fabiohbarbosa.notifier.sonar.factory;

import com.wordpress.fabiohbarbosa.notifier.sonar.adapter.SonarAdapter;

public final class SonarAdapterFactory {
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
