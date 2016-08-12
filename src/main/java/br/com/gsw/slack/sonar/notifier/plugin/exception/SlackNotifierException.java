package br.com.gsw.slack.sonar.notifier.plugin.exception;

public class SlackNotifierException extends RuntimeException {
    public SlackNotifierException(final String message) {
        super(message);
    }
}
