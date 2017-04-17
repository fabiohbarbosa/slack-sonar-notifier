package com.wordpress.fabiohbarbosa.notifier.sonar.model;

import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.QualityStatus;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.QualityGate;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.QualityGateFixture;

public class SonarStatsFixture {
    public static SonarStats newSonarStats() {
        return newSonarStats(QualityStatus.OK);
    }

    public static SonarStats newSonarStats(QualityStatus status) {
        final Project project = ProjectFixture.newProject();
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        final QualityGate qualityGate = QualityGateFixture.newQualityGate(status);
        return new SonarStats(project, sonar, qualityGate);
    }
}