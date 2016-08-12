package br.com.gsw.slack.sonar.notifier.plugin.factory;

import org.apache.maven.plugin.logging.Log;

public final class LogFactory {
    private static Log log;

    private LogFactory() {
        // block constructor
    }

    public static void init(Log _log) {
        log = _log;
    }
    public static Log getInstance() {
        return log;
    }
}
