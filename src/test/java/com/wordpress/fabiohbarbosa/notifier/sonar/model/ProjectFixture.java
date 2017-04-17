package com.wordpress.fabiohbarbosa.notifier.sonar.model;

public class ProjectFixture {
    public static Project newProject() {
        Project project = new Project();
        project.setName("SLACK NOTIFIER");
        project.setKey("com.wordpress.fabiohbarbosa:slack-notifier");
        project.setVersion("1.2.1");
        return project;
    }
}