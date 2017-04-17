package com.wordpress.fabiohbarbosa.notifier.sonar.provider.model;

public class QualityGate {
    private ProjectStatus projectStatus;

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
}
