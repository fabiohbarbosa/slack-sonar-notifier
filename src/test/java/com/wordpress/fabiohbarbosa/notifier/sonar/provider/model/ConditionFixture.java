package com.wordpress.fabiohbarbosa.notifier.sonar.provider.model;

import java.util.ArrayList;
import java.util.List;

public class ConditionFixture {

    public static List<Condition> newConditions() {
        List<Condition> conditions = new ArrayList<>();
        conditions.add(newCondition(SonarKeys.NEW_SECURITY_RATING_KEY));
        conditions.add(newCondition(SonarKeys.NEW_RELIABILITY_RATING_KEY));
        conditions.add(newCondition(SonarKeys.NEW_MAINTAINABILITY_RATING_KEY));
        conditions.add(newCondition(SonarKeys.NEW_COVERAGE_KEY));
        conditions.add(newCondition(SonarKeys.COVERAGE_KEY));
        conditions.add(newCondition(SonarKeys.BLOCKER_VIOLATIONS_KEY));
        conditions.add(newCondition(SonarKeys.TEST_FAILURES_KEY));
        conditions.add(newCondition(SonarKeys.SKIPPED_TESTS_KEY));
        conditions.add(newCondition(SonarKeys.CRITICAL_VIOLATIONS_KEY));
        conditions.add(newCondition(SonarKeys.NEW_BUGS_KEY));
        conditions.add(newCondition(SonarKeys.DUPLICATED_BLOCKS_KEY));
        conditions.add(newCondition(SonarKeys.NEW_BLOCKS_DUPLICATED_KEY));
        conditions.add(newCondition(SonarKeys.CODE_SMELLS_KEY));
        conditions.add(newCondition(SonarKeys.VULNERABILITIES_KEY));
        conditions.add(newCondition(SonarKeys.NEW_VULNERABILITIES_KEY));
        conditions.add(newCondition(SonarKeys.NEW_CODE_SMELLS_KEY));

        return conditions;
    }

    public static Condition newCondition(SonarKeys metricKey) {
        Condition condition = new Condition();

        condition.setStatus(QualityStatus.ERROR);
        condition.setMetricKey(metricKey);
        condition.setComparator("GT");
        condition.setPeriodIndex(1);
        condition.setWarningThreshold("0");
        condition.setErrorThreshold("10");
        condition.setActualValue("10");

        return condition;
    }
}