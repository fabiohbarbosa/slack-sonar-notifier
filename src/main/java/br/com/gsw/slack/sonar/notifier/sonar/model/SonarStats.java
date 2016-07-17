package br.com.gsw.slack.sonar.notifier.sonar.model;

import br.com.gsw.slack.sonar.notifier.sonar.web.model.ResourceResponse;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.Severity;

import java.util.Map;

public class SonarStats {
    private Project project;
    private Sonar sonar;
    private ResourceResponse ratings;
    private Map<Severity, Integer> issues;
    private ResourceResponse duplications;
    private ResourceResponse tests;

    public SonarStats(final Project project, final Sonar sonar, final ResourceResponse ratings, final Map<Severity, Integer> issues, final ResourceResponse duplications, final ResourceResponse tests) {
        this.project = project;
        this.sonar = sonar;
        this.ratings = ratings;
        this.issues = issues;
        this.duplications = duplications;
        this.tests = tests;
    }

    public Project getProject() {
        return project;
    }

    public Sonar getSonar() {
        return sonar;
    }

    public ResourceResponse getRatings() {
        return ratings;
    }

    public Map<Severity, Integer> getIssues() {
        return issues;
    }

    public ResourceResponse getDuplications() {
        return duplications;
    }

    public ResourceResponse getTests() {
        return tests;
    }
}
