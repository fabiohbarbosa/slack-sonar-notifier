package br.com.gsw.slack.sonar.notifier.sonar.factory;

import br.com.gsw.slack.sonar.notifier.sonar.service.OnlyErrorsFilter;

public class OnlyErrorsFilterFactory {
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
