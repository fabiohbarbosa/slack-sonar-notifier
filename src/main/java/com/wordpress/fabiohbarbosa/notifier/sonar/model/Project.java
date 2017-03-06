package com.wordpress.fabiohbarbosa.notifier.sonar.model;

import com.wordpress.fabiohbarbosa.notifier.sonar.web.model.ResourceResponse;

public class Project {
    private String id;
    private String name;
    private String version;

    public Project() {
    }

    public Project(final String id, final String name, final String version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public Project(final String name, final String version) {
        this.name = name;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public static Project parse(ResourceResponse sonarResource) {
        return new Project(sonarResource.getId(), sonarResource.getName(), sonarResource.getVersion());
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
