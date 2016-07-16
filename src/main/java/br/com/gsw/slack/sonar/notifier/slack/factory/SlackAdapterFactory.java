package br.com.gsw.slack.sonar.notifier.slack.factory;

import br.com.gsw.slack.sonar.notifier.slack.adapter.SlackAdapter;

public class SlackAdapterFactory {
    public static SlackAdapter getInstance() {
        return new SlackAdapter();
    }
}
