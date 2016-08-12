package br.com.gsw.slack.sonar.notifier.sonar.service;

import br.com.gsw.slack.sonar.notifier.plugin.factory.LogFactory;
import br.com.gsw.slack.sonar.notifier.sonar.model.SonarStats;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.KeyMsr;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.ResourceResponse;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.Severity;
import org.apache.maven.plugin.logging.Log;

import java.util.Map;

public class OnlyErrorsFilter {
    private static final Log LOGGER = LogFactory.getInstance();

    public SonarStats filter(final SonarStats sonarStats, final Double coverage) {
        LOGGER.debug("Only errors filter...");

        sonarStats.setRatings(filterRating(sonarStats.getRatings()));
        sonarStats.setIssues(filterIssues(sonarStats.getIssues()));
        sonarStats.setDuplications(filterDuplications(sonarStats.getDuplications()));
        sonarStats.setTests(filterTests(sonarStats.getTests(), coverage));

        return sonarStats;
    }

    protected ResourceResponse filterRating(final ResourceResponse ratings) {
        LOGGER.debug("Filter ratings...");

        if (ratings == null || ratings.getMsr() == null || ratings.getMsr().size() == 0) {
            LOGGER.debug("Ratings not found to filter!");
            return null;
        }
        if (ratings.getFrmtVal(KeyMsr.SQALE_RATING).equals("A")) {
            LOGGER.debug("Filter removing ratings because not found problems!");
            return null;
        }
        return ratings;
    }

    protected Map<Severity, Integer> filterIssues(final Map<Severity, Integer> issues) {
        LOGGER.debug("Filter issues...");

        if (issues == null || issues.size() == 0) {
            LOGGER.debug("Issues not found to filter!");
            return null;
        }
        final Integer total = issues.get(Severity.TOTAL);
        if (total == 0) {
            LOGGER.debug("Filter removing issues because not found problems!");
            return null;
        }
        return issues;
    }

    protected ResourceResponse filterDuplications(final ResourceResponse duplications) {
        LOGGER.debug("Filter duplications...");

        if (duplications == null || duplications.getMsr() == null || duplications.getMsr().size() == 0) {
            LOGGER.debug("Duplications not found to filter!");
            return null;
        }
        final int duplicatedFiles = Integer.parseInt(duplications.getFrmtVal(KeyMsr.DUPLICATED_FILES));
        if (duplicatedFiles == 0) {
            LOGGER.debug("Filter removing duplications because not found problems!");
            return null;
        }
        return duplications;
    }

    protected ResourceResponse filterTests(final ResourceResponse tests, Double coverage) {
        LOGGER.debug("Filter tests...");

        if (tests == null || tests.getMsr() == null || tests.getMsr().size() == 0) {
            LOGGER.debug("Tests not found to filter!");
            return null;
        }

        final Integer error = Integer.parseInt(tests.getFrmtVal(KeyMsr.TESTS_ERROR));
        final Integer skipped = Integer.parseInt(tests.getFrmtVal(KeyMsr.TESTS_SKIPPED));
        final Integer failures = Integer.parseInt(tests.getFrmtVal(KeyMsr.TESTS_FAILURES));
        final Double testsCoverage = Double.parseDouble(tests.getVal(KeyMsr.COVERAGE));

        boolean analsysCoverage = false;
        if (coverage != null) {
            LOGGER.debug(String.format("Coverage in project is %s and compare to %s", testsCoverage, coverage));
            if (testsCoverage >= coverage) {
                LOGGER.debug("Filter removing tests because not found problems!");
                analsysCoverage = true;
            }
        }

        if (analsysCoverage && error == 0 && skipped == 0 && failures == 0) {
            LOGGER.debug("Filter removing tests because not found problems!");
            return null;
        }
        return tests;
    }
}

