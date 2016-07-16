package br.com.gsw.slack.sonar.notifier;

import br.com.gsw.slack.sonar.notifier.plugin.factory.LogFactory;
import org.apache.maven.plugin.logging.SystemStreamLog;

public class PrepareFactoryTests {
    public PrepareFactoryTests() {
        LogFactory.init(new SystemStreamLog());
    }
}
