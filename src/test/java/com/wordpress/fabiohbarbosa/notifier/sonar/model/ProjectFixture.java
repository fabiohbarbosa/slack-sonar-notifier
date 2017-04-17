package com.wordpress.fabiohbarbosa.notifier.sonar.model;

public class ProjectFixture {
    public static Project newProject() {
        Project project = new Project();
        project.setKey("br.com.embraer:ase");
        project.setVersion("1.2.1");
        return project;
    }
}