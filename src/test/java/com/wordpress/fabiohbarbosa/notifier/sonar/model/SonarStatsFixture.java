package com.wordpress.fabiohbarbosa.notifier.sonar.model;

import com.wordpress.fabiohbarbosa.notifier.sonar.web.model.IssueResponseFixture;
import com.wordpress.fabiohbarbosa.notifier.sonar.web.model.ResourceResponse;
import com.wordpress.fabiohbarbosa.notifier.sonar.web.model.ResourceResponseFixture;
import com.wordpress.fabiohbarbosa.notifier.sonar.web.model.Severity;

import java.util.Map;

public class SonarStatsFixture {
    public static SonarStats newSonarStats() {
        final Project project = ProjectFixture.newProject();
        final Sonar sonar = SonarFixture.newSonarAuthEnv();
        final ResourceResponse ratings = ResourceResponseFixture.newRatings();
        final Map<Severity, Integer> issues = IssueResponseFixture.newIssues();
        final ResourceResponse duplications = ResourceResponseFixture.newDuplications();
        final ResourceResponse tests = ResourceResponseFixture.newTests();

        return new SonarStats(project, sonar, ratings, issues, duplications, tests);
    }
}