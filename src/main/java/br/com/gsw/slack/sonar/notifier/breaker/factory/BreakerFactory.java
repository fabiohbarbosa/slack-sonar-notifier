package br.com.gsw.slack.sonar.notifier.breaker.factory;

import br.com.gsw.slack.sonar.notifier.breaker.service.Breaker;

public final class BreakerFactory {
    private static Breaker breaker;

    private BreakerFactory() {
        // block constructor
    }

    public static Breaker getInstance() {
        if (breaker == null) {
            breaker = new Breaker();
        }
        return breaker;
    }
}
