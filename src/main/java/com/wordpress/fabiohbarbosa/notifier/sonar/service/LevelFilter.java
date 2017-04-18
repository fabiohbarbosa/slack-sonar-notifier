package com.wordpress.fabiohbarbosa.notifier.sonar.service;

import com.wordpress.fabiohbarbosa.notifier.plugin.factory.LogFactory;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.Level;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.SonarStats;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.Condition;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.ProjectStatus;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.QualityGate;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.QualityStatus;
import org.apache.maven.plugin.logging.Log;

import java.util.ArrayList;
import java.util.List;

public class LevelFilter {
    private static final Log LOGGER = LogFactory.getInstance();

    public SonarStats filter(SonarStats sonarStats, Level level) {
        LOGGER.info("Filtering level...");
        sonarStats.setQualityGate(filterQualityGates(sonarStats.getQualityGate(), level));
        return sonarStats;
    }

    private QualityGate filterQualityGates(QualityGate qualityGate, Level level) {
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

    private void filterInfo(Level level, List<Condition> conditions, Condition c) {
        if (level != Level.info) {
            return;
        }
        LOGGER.debug(String.format("Adding quality gate %s %s", c.getMetricKey().name(), c.getStatus()));
        conditions.add(c);
    }

    private void filterWarning(Level level, List<Condition> conditions, Condition c) {
        if (level != Level.warning) {
            return;
        }
        if (c.getStatus() == QualityStatus.WARN || c.getStatus() == QualityStatus.ERROR) {
            LOGGER.debug(String.format("Adding quality gate %s %s", c.getMetricKey().name(), c.getStatus()));
            conditions.add(c);
        }
    }

    private void filterError(Level level, List<Condition> conditions, Condition c) {
        if (level != Level.error) {
            return;
        }
        if (c.getStatus() == QualityStatus.ERROR) {
            LOGGER.debug(String.format("Adding quality gate %s %s", c.getMetricKey().name(), c.getStatus()));
            conditions.add(c);
        }
    }

}
