package br.com.gsw.slack.sonar.notifier.slack.factory;

import br.com.gsw.slack.sonar.notifier.slack.adapter.SlackRequestAdapter;

public final class SlackRequestAdapterFactory {
    private static SlackRequestAdapter adapter;

    private SlackRequestAdapterFactory() {
        // block constructor
    }

    public static SlackRequestAdapter getInstance() {
        if (adapter == null) {
            adapter = new SlackRequestAdapter();
        }
        return adapter;
    }
}
