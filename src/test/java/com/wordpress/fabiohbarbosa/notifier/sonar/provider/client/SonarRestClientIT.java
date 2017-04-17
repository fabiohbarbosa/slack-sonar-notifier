package com.wordpress.fabiohbarbosa.notifier.sonar.provider.client;

import com.wordpress.fabiohbarbosa.notifier.PrepareFactoryTests;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.Project;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.QualityGate;
import com.wordpress.fabiohbarbosa.notifier.web.client.FeignFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class SonarRestClientIT extends PrepareFactoryTests {
    private SonarRestClient restClient;
    private String projectKey;

    @Before
    public void setUp() {
        String url = System.getProperty("sonar.host.url");
        String user = System.getProperty("sonar.login");
        String password = System.getProperty("sonar.password");
        projectKey = System.getProperty("sonar.key");

        restClient = FeignFactory.build(url, user, password, SonarRestClient.class);
    }

    @Test
    public void restClientQualityGatesTest() {
        QualityGate qualityGate = restClient.qualityGates(projectKey);
        assertNotNull(qualityGate);
    }

    @Test
    public void restClientProjectTest() {
        Project project = restClient.findProject(projectKey).get(0);
        assertNotNull(project);
    }
}