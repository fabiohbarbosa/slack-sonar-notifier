package com.wordpress.fabiohbarbosa.notifier.sonar.web.model;

public class IssueResponse {
    private Integer total;

    public IssueResponse() {
    }

    public IssueResponse(final Integer total) {
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(final Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "SonarIssueResponse{" +
                "total=" + total +
                '}';
    }
}
