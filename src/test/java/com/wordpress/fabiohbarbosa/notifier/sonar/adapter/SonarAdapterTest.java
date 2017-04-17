package com.wordpress.fabiohbarbosa.notifier.sonar.adapter;

import com.wordpress.fabiohbarbosa.notifier.PrepareFactoryTests;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.Sonar;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.SonarFixture;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.client.SonarRestClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SonarAdapterTest extends PrepareFactoryTests {

    @Spy
    @InjectMocks
    private SonarAdapter adapter;

    @Mock
    private SonarRestClient sonarRestClient;

    private final static String KEY = "com.wordpress.fabiohbarbosa:slack-sonar-notifier";

    @Before
    public void startUp() {
        // mock static
        doReturn(sonarRestClient).when(adapter).buildClient(anyString(), anyString(), anyString());
    }

    // adapter
    @Test
    public void adapterSucessTest() {
        final Sonar sonar = SonarFixture.newSonar();

        assertNotNull(adapter.adapter(sonar));

        verify(sonarRestClient).findProject(sonar.getKey());
        verify(sonarRestClient).qualityGates(sonar.getKey());

    }

}