package com.wordpress.fabiohbarbosa.notifier.breaker.factory;

import com.wordpress.fabiohbarbosa.notifier.breaker.service.Breaker;

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
