package com.wordpress.fabiohbarbosa.notifier.sonar.provider.model;

public enum Color {
    PROJECT_COLOR("#0000cc"),
    SCM_COLOR("#0000cc"),
    QUALITY_GATE_OK("#0A0"), QUALITY_GATE_WARN("#ffff00"), QUALITY_GATE_ERROR("#ff0000"), QUALITY_GATE_NONE("#e1e1d0");

    public String VALUE;

    Color(final String VALUE){
        this.VALUE = VALUE;
    }
}
