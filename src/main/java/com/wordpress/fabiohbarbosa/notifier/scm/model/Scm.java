package com.wordpress.fabiohbarbosa.notifier.scm.model;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class Scm {
    private String url;
    private String branch;
    private String user;
    private String commit;

    public Scm(){
    }

    public Scm(final String url, final String branch, final String user, final String commit) {
        this.url = url;
        this.branch = branch;
        this.user = user;
        this.commit = commit;
    }

    public static String normalizeUrl(final String url) {
        if (isEmpty(url)) {
            return null;
        }
        if (url.charAt(url.length() - 1) == '/') {
            return url.substring(0, url.length() - 1);
        }
        return url;
    }

    public static String removeOrigin(final String branch) {
        if (isEmpty(branch)) {
            return null;
        }
        return branch.replace("origin/", "");
    }

    public static String normalizeCommit(final String commit) {
        if (isEmpty(commit)) {
            return null;
        }
        if (commit.length() > 7) {
            return commit.substring(0, 7);
        }
        return commit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(final String commit) {
        this.commit = commit;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(final String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Scm{" +
                "url='" + url + '\'' +
                ", branch='" + branch + '\'' +
                ", user='" + user + '\'' +
                ", commit='" + commit + '\'' +
                '}';
    }
}
