package com.wordpress.fabiohbarbosa.notifier.plugin.service;

import org.apache.maven.project.MavenProject;

public class MavenProjectFixture {
    private MavenProjectFixture() {
        // block constructor
    }

    public static MavenProject newProject() {
        final MavenProject mavenProject = new MavenProject();
        mavenProject.setGroupId("com.wordpress.fabiohbarbosa");
        mavenProject.setArtifactId("slack-sonar-notifier");
        return mavenProject;
    }
}
