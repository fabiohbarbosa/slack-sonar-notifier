package com.wordpress.fabiohbarbosa.notifier.plugin.exception;

public class SlackNotifierException extends RuntimeException {
    public SlackNotifierException(final String message) {
        super(message);
    }
}
