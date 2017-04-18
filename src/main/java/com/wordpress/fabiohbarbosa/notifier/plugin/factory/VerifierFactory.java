package com.wordpress.fabiohbarbosa.notifier.plugin.factory;

import com.wordpress.fabiohbarbosa.notifier.plugin.facade.Verifier;

public final class VerifierFactory {
    private static Verifier verifier;

    private VerifierFactory() {
        // block constructor
    }

    public static Verifier getInstance() {
        if (verifier == null) {
            verifier = new Verifier();
        }
        return verifier;
    }
}
