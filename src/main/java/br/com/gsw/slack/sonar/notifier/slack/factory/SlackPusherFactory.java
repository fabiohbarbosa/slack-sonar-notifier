package br.com.gsw.slack.sonar.notifier.slack.factory;

import br.com.gsw.slack.sonar.notifier.slack.service.SlackPusher;

public class SlackPusherFactory {
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
