package br.com.gsw.slack.sonar.notifier.sonar.adapter;

import br.com.gsw.slack.sonar.notifier.plugin.factory.LogFactory;
import br.com.gsw.slack.sonar.notifier.sonar.model.Project;
import br.com.gsw.slack.sonar.notifier.sonar.model.Sonar;
import br.com.gsw.slack.sonar.notifier.sonar.model.SonarStats;
import br.com.gsw.slack.sonar.notifier.sonar.web.client.SonarRestClient;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.KeyMsr;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.ResourceResponse;
import br.com.gsw.slack.sonar.notifier.sonar.web.model.Severity;
import br.com.gsw.slack.sonar.notifier.web.client.FeignFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.logging.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SonarAdapter {
    private static final Log LOGGER = LogFactory.getInstance();
    private SonarRestClient client;

    public SonarStats adapter(final Sonar sonar) {
        LOGGER.debug("Sonar adapter...");
        final String url = sonar.getUrl();
        final String user = sonar.getUser();
        final String password = sonar.getPassword();
        final String key = sonar.getKey();
        LOGGER.info(String.format("Trying to get sonar stats of %s from %s", key, url));

        LOGGER.debug(String.format("Creating sonar rest client for %s...", url));
        client = buildClient(url, user, password);

        final ResourceResponse ratings = adapterRating(key);
        final Map<Severity, Integer> issues = adapterIssues(key);
        final ResourceResponse duplications = adapterDuplications(key);
        final ResourceResponse tests = adapterTests(key);

        LOGGER.debug("Parsing project...");
        final Project project = Project.parse(ratings);

        LOGGER.info("Sonar stats received");
        return new SonarStats(project, sonar, ratings, issues, duplications, tests);
    }

    protected ResourceResponse adapterRating(final String key) {
        LOGGER.debug("Find sonar project and rating...");
        final List<ResourceResponse> ratings = client.ratings(key);
        LOGGER.debug(String.format("Receive sonar project and rating %s", ratings));

        if (ratings == null || ratings.size() > 1) {
            final String err = String.format("Invalid project for key %s", key);
            LOGGER.debug(err);
            throw new IllegalArgumentException(err);
        }
        return ratings.get(0);
    }

    protected Map<Severity, Integer> adapterIssues(final String key) {
        LOGGER.debug("Find sonar issues...");

        final Map<Severity, Integer> issues = new HashMap<>();
        final Integer blocker = client.issues(Severity.BLOCKER, key).getTotal();
        final Integer critical = client.issues(Severity.CRITICAL, key).getTotal();
        final Integer major = client.issues(Severity.MAJOR, key).getTotal();
        final Integer minor = client.issues(Severity.MINOR, key).getTotal();
        final Integer info = client.issues(Severity.INFO, key).getTotal();

        issues.put(Severity.TOTAL, blocker + critical + major + minor + info);
        issues.put(Severity.BLOCKER, blocker);
        issues.put(Severity.CRITICAL, critical);
        issues.put(Severity.MAJOR, major);
        issues.put(Severity.MINOR, minor);
        issues.put(Severity.INFO, info);

        LOGGER.debug(String.format("Receive sonar issues %s", issues));

        return issues;
    }

    protected ResourceResponse adapterDuplications(final String key) {
        LOGGER.debug("Find sonar duplications...");

        final List<ResourceResponse> duplications = client.duplications(key);
        LOGGER.debug(String.format("Receive sonar duplications %s", duplications));

        ResourceResponse response = null;
        if (duplications != null && duplications.size() == 1) {
            response = duplications.get(0);
        }
        if (duplications != null && duplications.size() > 1) {
            final String err = String.format("Invalid duplications for key %s", key);
            LOGGER.debug(err);
            throw new IllegalArgumentException(err);
        }
        return response;
    }

    protected ResourceResponse adapterTests(final String key) {
        LOGGER.debug("Find sonar tests...");

        final List<ResourceResponse> tests = client.tests(key);
        LOGGER.debug(String.format("Receive sonar tests %s", tests));

        if (tests != null && tests.size() == 1) {
            final ResourceResponse test = tests.get(0);
            normalizeAdapterTestsValue(test);
            return test;
        }
        if (tests != null && tests.size() > 1) {
            final String err = String.format("Invalid tests for key %s", key);
            LOGGER.debug(err);
            throw new IllegalArgumentException(err);
        }
        return null;
    }

    private void normalizeAdapterTestsValue(final ResourceResponse test) {
        if (StringUtils.isEmpty(test.getFrmtVal(KeyMsr.TESTS_TOTAL)) || StringUtils.isEmpty(test.getVal(KeyMsr.TESTS_TOTAL))) {
            test.addMsr(KeyMsr.TESTS_TOTAL, "0", "0");
        }

        if (StringUtils.isEmpty(test.getFrmtVal(KeyMsr.TESTS_ERROR)) || StringUtils.isEmpty(test.getVal(KeyMsr.TESTS_ERROR))) {
            test.addMsr(KeyMsr.TESTS_ERROR, "0", "0");
        }

        if (StringUtils.isEmpty(test.getFrmtVal(KeyMsr.TESTS_FAILURES)) || StringUtils.isEmpty(test.getVal(KeyMsr.TESTS_FAILURES))) {
            test.addMsr(KeyMsr.TESTS_FAILURES, "0", "0");
        }

        if (StringUtils.isEmpty(test.getFrmtVal(KeyMsr.COVERAGE)) || StringUtils.isEmpty(test.getVal(KeyMsr.COVERAGE))) {
            test.addMsr(KeyMsr.COVERAGE, "0.0", "0.0%");
        }

        if (StringUtils.isEmpty(test.getFrmtVal(KeyMsr.TESTS_SUCCESS)) || StringUtils.isEmpty(test.getVal(KeyMsr.TESTS_SUCCESS))) {
            test.addMsr(KeyMsr.TESTS_SUCCESS, "0.0", "0.0%");
        }
    }

    protected SonarRestClient buildClient(final String url, final String user, final String password) {
        return FeignFactory.build(url, user, password, SonarRestClient.class);
    }

}
