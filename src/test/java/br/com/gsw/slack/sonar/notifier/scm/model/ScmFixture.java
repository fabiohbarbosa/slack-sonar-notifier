package br.com.gsw.slack.sonar.notifier.scm.model;

public class ScmFixture {
    private ScmFixture() {
       // block constructor
    }

    public static Scm newScmBitbucket() {
        final String url = "https://bitbucket.com/gswteam/slack-sonar-notifier/";
        return newScm(url, "origin/testIT", "fabiohbarbosa", "caaef090d185e90132043487bee78b877455e4a1");
    }

    public static Scm newScmGithub() {
        final String url = "https://github.com/gswteam/slack-sonar-notifier/";
        return newScm(url, "origin/testIT", "fabiohbarbosa", "caaef090d185e90132043487bee78b877455e4a1");
    }

    private static Scm newScm(final String url, final String branch, final String user, final String commit) {
        return new Scm(url, branch, user, commit);
    }

    public static Scm newScmEnv() {
        return new Scm(System.getProperty("scm.url"), System.getProperty("scm.branch"), System.getProperty("scm.user"), System.getProperty("scm.commit"));
    }
}