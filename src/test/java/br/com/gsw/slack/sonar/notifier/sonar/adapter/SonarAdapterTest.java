package br.com.gsw.slack.sonar.notifier.sonar.adapter;

import br.com.gsw.slack.sonar.notifier.PrepareFactoryTests;
import br.com.gsw.slack.sonar.notifier.sonar.model.Sonar;
import br.com.gsw.slack.sonar.notifier.sonar.model.SonarFixture;
import br.com.gsw.slack.sonar.notifier.sonar.web.client.SonarRestClient;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.IssueResponseFixture;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.ResourceResponse;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.ResourceResponseFixture;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.Severity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class SonarAdapterTest extends PrepareFactoryTests {

    @Spy
    @InjectMocks
    private SonarAdapter adapter;

    @Mock
    private SonarRestClient client;

    private final static String KEY = "br.com.gsw:slack-pusher";

    @Before
    public void startUp() {
        // mock static
        doReturn(client).when(adapter).buildClient(anyString(), anyString(), anyString());
    }

    // adapter
    @Test
    public void adapterSucessTest() {
        final Sonar sonar = SonarFixture.newSonar();
        final String key = sonar.getKey();

        final ResourceResponse ratings = ResourceResponseFixture.newRatings();
        final Map<Severity, Integer> issues = IssueResponseFixture.newIssues();
        final ResourceResponse duplications = ResourceResponseFixture.newDuplications();
        final ResourceResponse tests = ResourceResponseFixture.newTests();


        doReturn(ratings).when(adapter).adapterRating(key);
        doReturn(issues).when(adapter).adapterIssues(key);
        doReturn(duplications).when(adapter).adapterDuplications(key);
        doReturn(tests).when(adapter).adapterTests(key);

        assertNotNull(adapter.adapter(sonar));
    }

    // adapterRating
    @Test(expected = IllegalArgumentException.class)
    public void adapterRatingTestErrorForManyRatingsInSonar() {
        final List<ResourceResponse> ratings = new ArrayList<>();
        ratings.add(ResourceResponseFixture.newRatings());
        ratings.add(ResourceResponseFixture.newRatings());

        doReturn(ratings).when(client).ratings(KEY);

        adapter.adapterRating(KEY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void adapterRatingTestErrorForNullRatingsInSonar() {
        final List<ResourceResponse> ratings = null;

        doReturn(ratings).when(client).ratings(KEY);

        adapter.adapterRating(KEY);
    }

    @Test
    public void adapterRatingSuccessTestForOneRatingInSonar() {
        final List<ResourceResponse> ratings = new ArrayList<>();
        final ResourceResponse rating = ResourceResponseFixture.newRatings();

        ratings.add(rating);

        doReturn(ratings).when(client).ratings(KEY);

        final ResourceResponse ratingAdapter = adapter.adapterRating(KEY);
        assertEquals(rating, ratingAdapter);
    }

    // adapterIssues
    @Test
    public void adapterIssuesSuccessTest() {
        doReturn(IssueResponseFixture.newIssueResponse(100)).when(client).issues(Severity.BLOCKER, KEY);
        doReturn(IssueResponseFixture.newIssueResponse(100)).when(client).issues(Severity.CRITICAL, KEY);
        doReturn(IssueResponseFixture.newIssueResponse(100)).when(client).issues(Severity.MAJOR, KEY);
        doReturn(IssueResponseFixture.newIssueResponse(100)).when(client).issues(Severity.MINOR, KEY);
        doReturn(IssueResponseFixture.newIssueResponse(100)).when(client).issues(Severity.INFO, KEY);

        final Map<Severity, Integer> issues = adapter.adapterIssues(KEY);

        assertTrue(issues.get(Severity.TOTAL) == 500);
        assertTrue(issues.get(Severity.BLOCKER) == 100);
        assertTrue(issues.get(Severity.CRITICAL) == 100);
        assertTrue(issues.get(Severity.MAJOR) == 100);
        assertTrue(issues.get(Severity.MINOR) == 100);
        assertTrue(issues.get(Severity.INFO) == 100);
    }

    // adapterDuplications
    @Test
    public void adapterDuplicationsSuccessTest() {
        final ResourceResponse duplication = ResourceResponseFixture.newDuplications();
        final List<ResourceResponse> duplications = Collections.singletonList(duplication);

        doReturn(duplications).when(client).duplications(KEY);

        final ResourceResponse duplicationAdapter = adapter.adapterDuplications(KEY);
        assertEquals(duplication, duplicationAdapter);
    }

    public void adapterDuplicationsErrorTestForNullDuplicationsInSonar() {
        doReturn(null).when(client).duplications(KEY);

        final ResourceResponse duplicationAdapter = adapter.adapterDuplications(KEY);
        assertNull(duplicationAdapter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void adapterDuplicationsErrorTestForManyDuplicationsInSonar() {
        final List<ResourceResponse> duplications = new ArrayList<>();
        duplications.add(ResourceResponseFixture.newDuplications());
        duplications.add(ResourceResponseFixture.newDuplications());

        doReturn(duplications).when(client).duplications(KEY);
        adapter.adapterDuplications(KEY);
    }

    // adapterTests
    @Test
    public void adapterTestsSuccessTest() {
        final ResourceResponse test = ResourceResponseFixture.newTests();
        final List<ResourceResponse> tests = Collections.singletonList(test);

        doReturn(tests).when(client).tests(KEY);

        final ResourceResponse testAdapter = adapter.adapterTests(KEY);
        assertEquals(test, testAdapter);
    }

    public void adapterTestsErrorTestForNullDuplicationsInSonar() {
        doReturn(null).when(client).tests(KEY);

        final ResourceResponse testAdapter = adapter.adapterTests(KEY);
        assertNull(testAdapter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void adapterTestsErrorTestForManyTestsInSonar() {
        final List<ResourceResponse> tests = new ArrayList<>();
        tests.add(ResourceResponseFixture.newTests());
        tests.add(ResourceResponseFixture.newTests());

        doReturn(tests).when(client).tests(KEY);
        adapter.adapterTests(KEY);
    }

}