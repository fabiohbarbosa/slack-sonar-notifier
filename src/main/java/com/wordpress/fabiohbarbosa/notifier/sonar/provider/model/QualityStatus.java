package com.wordpress.fabiohbarbosa.notifier.sonar.provider.model;

public enum QualityStatus {
    OK(Color.QUALITY_GATE_OK), WARN(Color.QUALITY_GATE_WARN), ERROR(Color.QUALITY_GATE_ERROR), NONE(Color.QUALITY_GATE_NONE);

    public Color COLOR;

    QualityStatus(Color COLOR) {
        this.COLOR = COLOR;
    }
}
