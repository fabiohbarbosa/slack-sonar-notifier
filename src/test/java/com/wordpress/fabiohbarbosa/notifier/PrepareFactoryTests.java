package com.wordpress.fabiohbarbosa.notifier;

import com.wordpress.fabiohbarbosa.notifier.plugin.factory.LogFactory;
import org.apache.maven.plugin.logging.SystemStreamLog;

public class PrepareFactoryTests {
    public PrepareFactoryTests() {
        LogFactory.init(new SystemStreamLog());
    }
}
