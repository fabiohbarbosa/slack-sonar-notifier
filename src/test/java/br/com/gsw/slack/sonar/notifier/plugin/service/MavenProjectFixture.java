package br.com.gsw.slack.sonar.notifier.plugin.service;

import org.apache.maven.project.MavenProject;

public class MavenProjectFixture {
    private MavenProjectFixture() {
        // block constructor
    }

    public static MavenProject newProject() {
        final MavenProject mavenProject = new MavenProject();
        mavenProject.setGroupId("br.com.gsw.slack");
        mavenProject.setArtifactId("sonar-notifier");
        return mavenProject;
    }
}
