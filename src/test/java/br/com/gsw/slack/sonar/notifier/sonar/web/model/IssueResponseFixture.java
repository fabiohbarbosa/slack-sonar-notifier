package br.com.gsw.slack.sonar.notifier.sonar.web.model;

import java.util.HashMap;
import java.util.Map;

public class IssueResponseFixture {

    public static Map<Severity, Integer> newIssues() {
        final Map<Severity, Integer> issues = new HashMap<>();

        issues.putAll(newIssueBySeverity(Severity.TOTAL, 100));
        issues.putAll(newIssueBySeverity(Severity.BLOCKER, 25));
        issues.putAll(newIssueBySeverity(Severity.CRITICAL, 25));
        issues.putAll(newIssueBySeverity(Severity.MAJOR, 25));
        issues.putAll(newIssueBySeverity(Severity.MINOR, 25));
        issues.putAll(newIssueBySeverity(Severity.INFO, 25));

        return issues;
    }

    public static Map<Severity, Integer> newIssueBySeverity(final Severity severity, final Integer total) {
        final Map<Severity, Integer> issues = new HashMap<>();
        issues.put(severity, total);
        return issues;
    }

    public static IssueResponse newIssueResponse(final Integer total) {
        return new IssueResponse(total);
    }
}