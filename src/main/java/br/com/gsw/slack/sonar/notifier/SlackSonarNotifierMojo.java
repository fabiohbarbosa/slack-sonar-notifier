package br.com.gsw.slack.sonar.notifier;

import br.com.gsw.slack.sonar.notifier.plugin.factory.LogFactory;
import br.com.gsw.slack.sonar.notifier.plugin.factory.NotifierFactory;
import br.com.gsw.slack.sonar.notifier.slack.model.Slack;
import br.com.gsw.slack.sonar.notifier.sonar.model.Sonar;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "sonar-notifier",
        requiresOnline = true,
        requiresProject = true,
        threadSafe = false)
public class SlackSonarNotifierMojo extends AbstractMojo {
    @Parameter
    protected Sonar sonar;

    @Parameter
    protected Slack slack;

    private static Log LOGGER;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        LogFactory.init(getLog());
        LOGGER = LogFactory.getInstance();

        LOGGER.info("------------------------------------------------------------------------");
        LOGGER.info("Slack Sonar Notifier Plugin");
        LOGGER.info("------------------------------------------------------------------------");
        NotifierFactory.getInstance().start(sonar, slack);
    }
}
