package br.com.gsw.slack.sonar.notifier.plugin.factory;

import br.com.gsw.slack.sonar.notifier.plugin.facade.Notifier;

public class NotifierFactory {
    private static Notifier notifier;

    private NotifierFactory() {
        // block constructor
    }

    public static Notifier getInstance() {
        if (notifier == null) {
            notifier = new Notifier();
        }
        return notifier;
    }
}
