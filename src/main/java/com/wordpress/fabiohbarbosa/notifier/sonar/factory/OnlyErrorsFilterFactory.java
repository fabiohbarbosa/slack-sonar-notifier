package com.wordpress.fabiohbarbosa.notifier.sonar.factory;

import com.wordpress.fabiohbarbosa.notifier.sonar.service.OnlyErrorsFilter;

public final class OnlyErrorsFilterFactory {
    private static OnlyErrorsFilter filter;

    private OnlyErrorsFilterFactory() {
        // block constructor
    }

    public static OnlyErrorsFilter getInstance() {
        if (filter == null) {
            filter = new OnlyErrorsFilter();
        }
        return filter;
    }
}
