package com.wordpress.fabiohbarbosa.notifier;

import com.wordpress.fabiohbarbosa.notifier.plugin.factory.LogFactory;
import com.wordpress.fabiohbarbosa.notifier.plugin.factory.PluginLoadPropertiesFactory;
import com.wordpress.fabiohbarbosa.notifier.plugin.factory.PluginValidatorFactory;
import com.wordpress.fabiohbarbosa.notifier.plugin.factory.VerifierFactory;
import com.wordpress.fabiohbarbosa.notifier.scm.model.Scm;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.Level;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.Sonar;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "verify",  requiresOnline = true)
public class SlackSonarVerifyMojo extends AbstractMojo {
    @Parameter
    private Sonar sonar;

    @Parameter
    private Scm scm;

    @Parameter(defaultValue = "${project}")
    private MavenProject mavenProject;

    @Parameter(defaultValue = "false", property = "skipNotifier")
    private Boolean skipNotifier;

    @Parameter(defaultValue = "warning", property = "levelNotifier")
    private Level levelNotifier;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        LogFactory.init(getLog());
        final Log log = LogFactory.getInstance();

        log.info("------------------------------------------------------------------------");
        log.info("Slack Sonar Notifier Plugin");
        log.info("Goal: verify");
        log.info("------------------------------------------------------------------------");

        log.debug(String.format("Received sonar %s", sonar));
        log.debug(String.format("Received scm %s", scm));
        log.debug(String.format("Received skipNotifier %s", skipNotifier));
        log.debug(String.format("Received levelNotifier %s", levelNotifier));

        log.debug("------------------------------------------------------------------------");

        if (skipNotifier) {
            log.info("Slack Sonar Notifier Plugin is skipped.");
            return;
        }

        sonar = PluginLoadPropertiesFactory.getInstance().sonar(sonar, mavenProject);
        scm = PluginLoadPropertiesFactory.getInstance().scm(scm);

        PluginValidatorFactory.getInstance().sonar(sonar);

        VerifierFactory.getInstance().start(sonar, levelNotifier);
    }

}
