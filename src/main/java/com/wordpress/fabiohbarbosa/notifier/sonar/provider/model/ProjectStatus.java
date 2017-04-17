package com.wordpress.fabiohbarbosa.notifier.sonar.provider.model;

import java.util.List;

public class ProjectStatus {
    private QualityStatus status;
    private List<Condition> conditions;
    private List<Period> periods;

    public QualityStatus getStatus() {
        return status;
    }

    public void setStatus(QualityStatus status) {
        this.status = status;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }
}
