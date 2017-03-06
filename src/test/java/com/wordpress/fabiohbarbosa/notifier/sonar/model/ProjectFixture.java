package com.wordpress.fabiohbarbosa.notifier.sonar.model;

public class ProjectFixture {
    public static Project newProject() {
        return new Project("br.com.embraer:ase", "1.2.1");
    }
}