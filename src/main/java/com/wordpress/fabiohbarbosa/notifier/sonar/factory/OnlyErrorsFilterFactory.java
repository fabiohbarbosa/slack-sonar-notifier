package com.wordpress.fabiohbarbosa.notifier.sonar.factory;

import com.wordpress.fabiohbarbosa.notifier.sonar.service.SlackLevelFilter;

public final class OnlyErrorsFilterFactory {
    private static SlackLevelFilter filter;

    private OnlyErrorsFilterFactory() {
        // block constructor
    }

    public static SlackLevelFilter getInstance() {
        if (filter == null) {
            filter = new SlackLevelFilter();
        }
        return filter;
    }
}