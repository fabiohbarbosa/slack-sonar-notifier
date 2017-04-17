package com.wordpress.fabiohbarbosa.notifier.sonar.model;

import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.QualityGate;

public class SonarStats {
    private Project project;
    private Sonar sonar;
    private QualityGate qualityGate;

    public SonarStats(final Project project, final Sonar sonar, QualityGate qualityGate) {
        this.project = project;
        this.sonar = sonar;
        this.qualityGate = qualityGate;
    }

    public void setProject(final Project project) {
        this.project = project;
    }

    public void setSonar(final Sonar sonar) {
        this.sonar = sonar;
    }

    public Project getProject() {
        return project;
    }

    public Sonar getSonar() {
        return sonar;
    }

    public QualityGate getQualityGate() {
        return qualityGate;
    }

    public void setQualityGate(QualityGate qualityGate) {
        this.qualityGate = qualityGate;
    }
}
