package com.wordpress.fabiohbarbosa.notifier.slack.factory;

import com.wordpress.fabiohbarbosa.notifier.slack.adapter.SlackRequestAdapter;

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
