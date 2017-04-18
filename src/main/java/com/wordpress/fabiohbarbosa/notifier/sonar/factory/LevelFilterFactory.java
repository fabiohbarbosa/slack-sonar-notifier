package com.wordpress.fabiohbarbosa.notifier.sonar.factory;

import com.wordpress.fabiohbarbosa.notifier.sonar.service.LevelFilter;

public final class LevelFilterFactory {
    private static LevelFilter filter;

    private LevelFilterFactory() {
        // block constructor
    }

    public static LevelFilter getInstance() {
        if (filter == null) {
            filter = new LevelFilter();
        }
        return filter;
    }
}