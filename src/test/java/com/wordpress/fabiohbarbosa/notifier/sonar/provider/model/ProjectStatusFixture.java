package com.wordpress.fabiohbarbosa.notifier.sonar.provider.model;

public class ProjectStatusFixture {

    public static ProjectStatus newProjectStatus(QualityStatus status) {
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setStatus(status);
        projectStatus.setConditions(ConditionFixture.newConditions());
        projectStatus.setPeriods(PeriodFixture.newPeriods());
        return projectStatus;
    }

}