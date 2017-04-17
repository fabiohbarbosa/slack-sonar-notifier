package com.wordpress.fabiohbarbosa.notifier.sonar.provider.model;

public class Condition {
    private QualityStatus status;
    private SonarKeys metricKey;
    private String comparator;
    private Integer periodIndex;
    private String warningThreshold;
    private String errorThreshold;
    private String actualValue;

    public QualityStatus getStatus() {
        return status;
    }

    public void setStatus(QualityStatus status) {
        this.status = status;
    }

    public SonarKeys getMetricKey() {
        return metricKey;
    }

    public void setMetricKey(SonarKeys metricKey) {
        this.metricKey = metricKey;
    }

    public String getComparator() {
        return comparator;
    }

    public void setComparator(String comparator) {
        this.comparator = comparator;
    }

    public Integer getPeriodIndex() {
        return periodIndex;
    }

    public void setPeriodIndex(Integer periodIndex) {
        this.periodIndex = periodIndex;
    }

    public String getWarningThreshold() {
        return warningThreshold;
    }

    public void setWarningThreshold(String warningThreshold) {
        this.warningThreshold = warningThreshold;
    }

    public String getErrorThreshold() {
        return errorThreshold;
    }

    public void setErrorThreshold(String errorThreshold) {
        this.errorThreshold = errorThreshold;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }
}
