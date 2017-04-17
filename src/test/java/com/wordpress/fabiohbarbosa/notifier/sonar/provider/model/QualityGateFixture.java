package com.wordpress.fabiohbarbosa.notifier.sonar.provider.model;

public class QualityGateFixture {

    public static QualityGate newQualityGate(QualityStatus status) {
        QualityGate qualityGate = new QualityGate();
        qualityGate.setProjectStatus(ProjectStatusFixture.newProjectStatus(status));
        return qualityGate;
    }
}