package br.com.gsw.slack.sonar.notifier.sonar.web.model;

import java.util.ArrayList;
import java.util.List;

public class MsrResponseFixture {
    public static List<MsrResponse> newRatings() {
        final List<MsrResponse> msrResponses = new ArrayList<>();
        msrResponses.add(newMsr(KeyMsr.SQALE_RATING, "A"));
        msrResponses.add(newMsr(KeyMsr.SQALE_DEBT_RATIO, "1.6%"));
        return msrResponses;
    }

    public static List<MsrResponse> newDuplications() {
        final List<MsrResponse> msrResponses = new ArrayList<>();
        msrResponses.add(newMsr(KeyMsr.DUPLICATED_LINES_DENSITY, "1.8%"));
        msrResponses.add(newMsr(KeyMsr.DUPLICATED_LINES, "226"));
        msrResponses.add(newMsr(KeyMsr.DUPLICATED_BLOCKS, "15"));
        msrResponses.add(newMsr(KeyMsr.DUPLICATED_FILES, "9"));
        return msrResponses;
    }

    public static List<MsrResponse> newTests() {
        final List<MsrResponse> msrResponses = new ArrayList<>();
        msrResponses.add(newMsr(KeyMsr.TESTS_TOTAL, "116"));
        msrResponses.add(newMsr(KeyMsr.TESTS_ERROR, "2"));
        msrResponses.add(newMsr(KeyMsr.TESTS_FAILURES, "2"));
        msrResponses.add(newMsr(KeyMsr.TESTS_SKIPPED, "1"));
        msrResponses.add(newMsr(KeyMsr.TESTS_SUCCESS, "99.0%"));
        msrResponses.add(newMsr(KeyMsr.COVERAGE, "10"));
        return msrResponses;
    }

    private static MsrResponse newMsr(final KeyMsr keyMsr, final String val) {
        final MsrResponse msrResponse = new MsrResponse();
        msrResponse.setKey(keyMsr.KEY);
        msrResponse.setFrmtVal(val);
        return msrResponse;
    }

}
