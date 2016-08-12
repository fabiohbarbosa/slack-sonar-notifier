package br.com.gsw.slack.sonar.notifier.sonar.web.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResourceResponse {
    private String id;
    private String key;
    private String name;
    private String version;
    private String description;
    private List<MsrResponse> msr;

    public String getVal(KeyMsr keyMsr) {
        if (msr == null || msr.size() == 0) {
            return null;
        }
        for (final MsrResponse m : msr) {
            if (Objects.equals(m.getKey(), keyMsr.KEY)) {
                return m.getVal();
            }
        }
        return null;
    }

    public String getFrmtVal(KeyMsr keyMsr) {
        if (msr == null || msr.size() == 0) {
            return null;
        }
        for (final MsrResponse m : msr) {
            if (Objects.equals(m.getKey(), keyMsr.KEY)) {
                return m.getFrmtVal();
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<MsrResponse> getMsr() {
        return msr;
    }

    public void setMsr(final List<MsrResponse> msr) {
        this.msr = msr;
    }

    @Override
    public String toString() {
        return "SonarResourceResponse{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", msr=" + msr +
                '}';
    }

    public void addMsr(final KeyMsr keyMsr, final String val, final String frmtVal) {
        if (msr == null) {
            msr = new ArrayList<>();
        }
        msr.add(new MsrResponse(keyMsr.KEY, val, frmtVal));
    }
}
