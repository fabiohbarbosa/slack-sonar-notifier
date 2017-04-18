package com.wordpress.fabiohbarbosa.notifier;

import com.wordpress.fabiohbarbosa.notifier.plugin.factory.LogFactory;
import com.wordpress.fabiohbarbosa.notifier.plugin.factory.NotifierFactory;
import com.wordpress.fabiohbarbosa.notifier.plugin.factory.PluginLoadPropertiesFactory;
import com.wordpress.fabiohbarbosa.notifier.plugin.factory.PluginValidatorFactory;
import com.wordpress.fabiohbarbosa.notifier.scm.model.Scm;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.Level;
import com.wordpress.fabiohbarbosa.notifier.slack.model.Slack;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.Sonar;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "notifier",  requiresOnline = true)
public class SlackSonarNotifierMojo extends AbstractMojo {
    @Parameter
    private Sonar sonar;

    @Parameter
    private Scm scm;

    @Parameter
    private Slack slack;

    @Parameter(defaultValue = "${project}")
    private MavenProject mavenProject;

    @Parameter(defaultValue = "false", property = "skipNotifier")
    private Boolean skipNotifier;

    @Parameter(defaultValue = "false", property = "breakNotifier")
    private Boolean breakNotifier;

    @Parameter(defaultValue = "warning", property = "levelNotifier")
    private Level levelNotifier;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        LogFactory.init(getLog());
        final Log log = LogFactory.getInstance();

        log.info("------------------------------------------------------------------------");
        log.info("Slack Sonar Notifier Plugin");
        log.info("Goal: notifier");
        log.info("------------------------------------------------------------------------");

        log.debug(String.format("Received sonar %s", sonar));
        log.debug(String.format("Received slack %s", slack));
        log.debug(String.format("Received scm %s", scm));
        log.debug(String.format("Received skipNotifier %s", skipNotifier));
        log.debug(String.format("Received breakNotifier %s", breakNotifier));
        log.debug(String.format("Received levelNotifier %s", levelNotifier));

        log.debug("------------------------------------------------------------------------");

        if (skipNotifier) {
            log.info("Slack Sonar Notifier Plugin is skipped.");
            return;
        }

        sonar = PluginLoadPropertiesFactory.getInstance().sonar(sonar, mavenProject);
        slack = PluginLoadPropertiesFactory.getInstance().slack(slack);
        scm = PluginLoadPropertiesFactory.getInstance().scm(scm);

        PluginValidatorFactory.getInstance().sonar(sonar);
        PluginValidatorFactory.getInstance().slack(slack);

        NotifierFactory.getInstance().start(sonar, slack, scm, levelNotifier, breakNotifier);
    }

}
