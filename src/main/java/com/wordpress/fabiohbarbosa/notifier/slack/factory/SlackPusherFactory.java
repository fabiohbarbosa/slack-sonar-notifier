package com.wordpress.fabiohbarbosa.notifier.slack.factory;

import com.wordpress.fabiohbarbosa.notifier.slack.service.SlackPusher;

public final class SlackPusherFactory {
    private static SlackPusher pusher;

    private SlackPusherFactory() {
        // block constructor
    }

    public static SlackPusher getInstance() {
        if (pusher == null) {
            pusher = new SlackPusher();
        }
        return pusher;
    }
}
