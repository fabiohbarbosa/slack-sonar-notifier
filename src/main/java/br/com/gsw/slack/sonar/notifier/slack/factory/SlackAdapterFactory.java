package br.com.gsw.slack.sonar.notifier.slack.factory;

import br.com.gsw.slack.sonar.notifier.slack.adapter.SlackAdapter;

public final class SlackAdapterFactory {
    private static SlackAdapter adapter;

    private SlackAdapterFactory() {
        // block constructor
    }

    public static SlackAdapter getInstance() {
        if (adapter == null) {
            adapter = new SlackAdapter();
        }
        return adapter;
    }
}
