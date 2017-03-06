package com.wordpress.fabiohbarbosa.notifier.plugin.factory;

import com.wordpress.fabiohbarbosa.notifier.plugin.facade.Notifier;

public final class NotifierFactory {
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
