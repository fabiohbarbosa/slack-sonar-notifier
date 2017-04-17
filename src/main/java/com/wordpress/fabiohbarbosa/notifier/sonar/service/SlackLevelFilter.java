package com.wordpress.fabiohbarbosa.notifier.sonar.service;

import com.wordpress.fabiohbarbosa.notifier.plugin.factory.LogFactory;
import com.wordpress.fabiohbarbosa.notifier.slack.model.SlackLevel;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.SonarStats;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.Condition;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.ProjectStatus;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.QualityGate;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.QualityStatus;
import org.apache.maven.plugin.logging.Log;

import java.util.ArrayList;
import java.util.List;

public class SlackLevelFilter {
    private static final Log LOGGER = LogFactory.getInstance();

    public SonarStats filter(SonarStats sonarStats, SlackLevel level) {
        LOGGER.debug("Only errors filter...");
        sonarStats.setQualityGate(filterQualityGates(sonarStats.getQualityGate(), level));
        return sonarStats;
    }

    private QualityGate filterQualityGates(QualityGate qualityGate, SlackLevel level) {
        LOGGER.debug("Filtering quality gates...");

        if (qualityGate == null || qualityGate.getProjectStatus() == null) {
            return null;
        }

        ProjectStatus projectStatus = qualityGate.getProjectStatus();
        List<Condition> conditions = new ArrayList<>();
        for (Condition c : projectStatus.getConditions()) {
            filterInfo(level, conditions, c);
            filterWarning(level, conditions, c);
            filterError(level, conditions, c);
        }

        if (!conditions.isEmpty()) {
            projectStatus.setConditions(conditions);
            qualityGate.setProjectStatus(projectStatus);
            return  qualityGate;
        } else {
            return null;
        }
    }

    private void filterInfo(SlackLevel level, List<Condition> conditions, Condition c) {
        if (level != SlackLevel.INFO) {
            return;
        }
        LOGGER.debug(String.format("Adding quality gate %s %s", c.getMetricKey().name(), c.getStatus()));
        conditions.add(c);
    }

    private void filterWarning(SlackLevel level, List<Condition> conditions, Condition c) {
        if (level != SlackLevel.WARNING) {
            return;
        }
        if (c.getStatus() == QualityStatus.WARN || c.getStatus() == QualityStatus.ERROR) {
            LOGGER.debug(String.format("Adding quality gate %s %s", c.getMetricKey().name(), c.getStatus()));
            conditions.add(c);
        }
    }

    private void filterError(SlackLevel level, List<Condition> conditions, Condition c) {
        if (level != SlackLevel.ERROR) {
            return;
        }
        if (c.getStatus() == QualityStatus.WARN || c.getStatus() == QualityStatus.ERROR) {
            LOGGER.debug(String.format("Adding quality gate %s %s", c.getMetricKey().name(), c.getStatus()));
            conditions.add(c);
        }
    }

}
