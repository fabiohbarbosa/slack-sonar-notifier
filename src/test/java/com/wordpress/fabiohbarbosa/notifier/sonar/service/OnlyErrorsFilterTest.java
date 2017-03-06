package com.wordpress.fabiohbarbosa.notifier.sonar.service;

import com.wordpress.fabiohbarbosa.notifier.PrepareFactoryTests;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.SonarStats;
import com.wordpress.fabiohbarbosa.notifier.sonar.model.SonarStatsFixture;
import com.wordpress.fabiohbarbosa.notifier.sonar.web.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class OnlyErrorsFilterTest extends PrepareFactoryTests {

    @Spy
    private OnlyErrorsFilter filter;

    // filter
    @Test
    public void filterSucessTest() {
        final SonarStats sonarStats = SonarStatsFixture.newSonarStats();

        final ResourceResponse ratings = sonarStats.getRatings();
        final Map<Severity, Integer> issues = sonarStats.getIssues();
        final ResourceResponse duplications = sonarStats.getDuplications();
        final ResourceResponse tests = sonarStats.getTests();

        final Double coverage = 60.0;

        doReturn(ratings).when(filter).filterRating(ratings);
        doReturn(issues).when(filter).filterIssues(issues);
        doReturn(duplications).when(filter).filterDuplications(duplications);
        doReturn(tests).when(filter).filterTests(tests, coverage);

        assertEquals(sonarStats, filter.filter(sonarStats, coverage));
    }

    // filter ratings
    @Test
    public void removeRatingForNullValue() {
        Assert.assertEquals(null, filter.filterRating(null));
    }

    @Test
    public void removeRatingForNullMsrValue() {
        final ResourceResponse ratings = ResourceResponseFixture.newRatings(SqaleRating.A);
        ratings.setMsr(null);

        Assert.assertEquals(null, filter.filterRating(ratings));
    }

    @Test
    public void removeRatingForEmptyMsrValue() {
        final ResourceResponse ratings = ResourceResponseFixture.newRatings(SqaleRating.A);
        ratings.setMsr(new ArrayList<MsrResponse>());

        Assert.assertEquals(null, filter.filterRating(ratings));
    }

    @Test
    public void removeRatingForAValue() {
        final ResourceResponse ratings = ResourceResponseFixture.newRatings(SqaleRating.A);
        Assert.assertEquals(null, filter.filterRating(ratings));
    }

    @Test
    public void notRemoveRatingForBValue() {
        final ResourceResponse ratings = ResourceResponseFixture.newRatings(SqaleRating.B);
        Assert.assertEquals(ratings, filter.filterRating(ratings));
    }

    // filter issues
    @Test
    public void removeIssuesForNullValue() {
        assertEquals(null, filter.filterIssues(null));
    }

    @Test
    public void removeIssuesForEmptyValue() {
        assertEquals(null, filter.filterIssues(new HashMap<Severity, Integer>()));
    }

    @Test
    public void removeIssuesForZeroInTotalIssues() {
        final Map<Severity, Integer> issues = IssueResponseFixture.newIssues();
        issues.put(Severity.TOTAL, 0);

        assertEquals(null, filter.filterIssues(issues));
    }

    @Test
    public void notRemoveWhenExistIssues() {
        final Map<Severity, Integer> issues = IssueResponseFixture.newIssues();
        issues.put(Severity.TOTAL, 100);

        assertEquals(issues, filter.filterIssues(issues));
    }

    // filter duplications
    @Test
    public void removeDuplicationsForNullValue() {
        Assert.assertEquals(null, filter.filterDuplications(null));
    }

    @Test
    public void removeDuplicationsForNullMsrValue() {
        final ResourceResponse duplications = ResourceResponseFixture.newDuplications();
        duplications.setMsr(null);

        Assert.assertEquals(null, filter.filterDuplications(duplications));
    }

    @Test
    public void removeDuplicationsForEmptyMsrValue() {
        final ResourceResponse duplications = ResourceResponseFixture.newDuplications();
        duplications.setMsr(new ArrayList<MsrResponse>());

        Assert.assertEquals(null, filter.filterDuplications(duplications));
    }

    @Test
    public void removeDuplicationsForZeroInTotalFiles() {
        final ResourceResponse duplications = ResourceResponseFixture.newDuplications(0);
        Assert.assertEquals(null, filter.filterDuplications(duplications));
    }

    @Test
    public void notRemoveDuplicationsForTotalFileGreaterThenZero() {
        final ResourceResponse duplications = ResourceResponseFixture.newDuplications(10);
        Assert.assertEquals(duplications, filter.filterDuplications(duplications));
    }

    // filter tests
    @Test
    public void removeTestsForNullValue() {
        Assert.assertEquals(null, filter.filterTests(null, 0.0));
    }

    @Test
    public void removeTestsForNullMsrValue() {
        final ResourceResponse tests = ResourceResponseFixture.newTests();
        tests.setMsr(null);

        Assert.assertEquals(null, filter.filterTests(tests, 0.0));
    }

    @Test
    public void removeTestsForEmptyMsrValue() {
        final ResourceResponse tests = ResourceResponseFixture.newTests();
        tests.setMsr(new ArrayList<MsrResponse>());

        Assert.assertEquals(null, filter.filterTests(tests, 0.0));
    }

    @Test
    public void notRemoveTestsForCoverageInProjectLessThenCoverageAnalysis() {
        final Integer total = 534;
        final Integer errors = 0;
        final Integer failures = 0;
        final Integer skipped = 0;
        final Double success = 100.0;
        final Double coverage = 30.0;
        final ResourceResponse tests = ResourceResponseFixture.newTests(total, errors, failures, skipped, success, coverage);

        Assert.assertEquals(tests, filter.filterTests(tests, 60.0));
    }

    @Test
    public void notRemoveTestsForCoverageInProjectLessThenCoverageAnalysisAndExistErrors() {
        final Integer total = 534;
        final Integer errors = 10;
        final Integer failures = 0;
        final Integer skipped = 0;
        final Double success = 100.0;
        final Double coverage = 30.0;
        final ResourceResponse tests = ResourceResponseFixture.newTests(total, errors, failures, skipped, success, coverage);

        Assert.assertEquals(tests, filter.filterTests(tests, 60.0));
    }

    @Test
    public void removeTestsForCoverageInProjectEqualsCoverageAnalysis() {
        final Integer total = 534;
        final Integer errors = 0;
        final Integer failures = 0;
        final Integer skipped = 0;
        final Double success = 100.0;
        final Double coverage = 60.0;
        final ResourceResponse tests = ResourceResponseFixture.newTests(total, errors, failures, skipped, success, coverage);

        Assert.assertEquals(null, filter.filterTests(tests, 60.0));
    }

    @Test
    public void removeTestsForCoverageInProjectGreaterThanCoverageAnalysisAndNotExistErrorsFailuresAndSkipped() {
        final Integer total = 534;
        final Integer errors = 0;
        final Integer failures = 0;
        final Integer skipped = 0;
        final Double success = 100.0;
        final Double coverage = 80.0;
        final ResourceResponse tests = ResourceResponseFixture.newTests(total, errors, failures, skipped, success, coverage);

        Assert.assertEquals(null, filter.filterTests(tests, 60.0));
    }

    @Test
    public void removeTestsForCoverageNullAndNotExistErrorsFailuresAndSkipped() {
        final Integer total = 534;
        final Integer errors = 0;
        final Integer failures = 0;
        final Integer skipped = 0;
        final Double success = 100.0;
        final Double coverage = 80.0;
        final ResourceResponse tests = ResourceResponseFixture.newTests(total, errors, failures, skipped, success, coverage);

        Assert.assertEquals(null, filter.filterTests(tests, null));
    }

    @Test
    public void notRemoveTestsForCoverageInProjectGreaterThanCoverageAnalysisButExistErrors() {
        final Integer total = 534;
        final Integer errors = 30;
        final Integer failures = 0;
        final Integer skipped = 0;
        final Double success = 100.0;
        final Double coverage = 80.0;
        final ResourceResponse tests = ResourceResponseFixture.newTests(total, errors, failures, skipped, success, coverage);

        Assert.assertEquals(tests, filter.filterTests(tests, 60.0));
    }

    @Test
    public void notRemoveTestsForCoverageNullButExistErrors() {
        final Integer total = 534;
        final Integer errors = 30;
        final Integer failures = 0;
        final Integer skipped = 0;
        final Double success = 100.0;
        final Double coverage = 80.0;
        final ResourceResponse tests = ResourceResponseFixture.newTests(total, errors, failures, skipped, success, coverage);

        Assert.assertEquals(tests, filter.filterTests(tests, null));
    }

    @Test
    public void notRemoveTestsForCoverageInProjectGreaterThanCoverageAnalysisButExistFailures() {
        final Integer total = 534;
        final Integer errors = 0;
        final Integer failures = 30;
        final Integer skipped = 0;
        final Double success = 100.0;
        final Double coverage = 80.0;
        final ResourceResponse tests = ResourceResponseFixture.newTests(total, errors, failures, skipped, success, coverage);

        Assert.assertEquals(tests, filter.filterTests(tests, 60.0));
    }

    @Test
    public void notRemoveTestsForCoverageInProjectGreaterThanCoverageAnalysisButExistSkipped() {
        final Integer total = 534;
        final Integer errors = 0;
        final Integer failures = 0;
        final Integer skipped = 30;
        final Double success = 100.0;
        final Double coverage = 80.0;
        final ResourceResponse tests = ResourceResponseFixture.newTests(total, errors, failures, skipped, success, coverage);

        Assert.assertEquals(tests, filter.filterTests(tests, 60.0));
    }

}