package com.wordpress.fabiohbarbosa.notifier.sonar.web.model;

import java.util.ArrayList;
import java.util.List;

public class MsrResponseFixture {
    public static List<MsrResponse> newRatings(String sqale) {
        final List<MsrResponse> msrResponses = new ArrayList<>();
        msrResponses.add(newMsr(KeyMsr.SQALE_RATING, sqale));
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

    public static List<MsrResponse> newDuplications(final Integer totalFiles) {
        final List<MsrResponse> msrResponses = new ArrayList<>();
        msrResponses.add(newMsr(KeyMsr.DUPLICATED_LINES_DENSITY, "1.8%"));
        msrResponses.add(newMsr(KeyMsr.DUPLICATED_LINES, "226"));
        msrResponses.add(newMsr(KeyMsr.DUPLICATED_BLOCKS, "15"));
        msrResponses.add(newMsr(KeyMsr.DUPLICATED_FILES, String.valueOf(totalFiles)));
        return msrResponses;
    }

    public static List<MsrResponse> newTests() {
        final List<MsrResponse> msrResponses = new ArrayList<>();
        msrResponses.add(newMsr(KeyMsr.TESTS_TOTAL, "116"));
        msrResponses.add(newMsr(KeyMsr.TESTS_ERROR, "2"));
        msrResponses.add(newMsr(KeyMsr.TESTS_FAILURES, "2"));
        msrResponses.add(newMsr(KeyMsr.TESTS_SKIPPED, "1"));
        msrResponses.add(newMsr(KeyMsr.TESTS_SUCCESS, "99.0%"));
        msrResponses.add(newMsr(KeyMsr.COVERAGE, " 33.4%"));
        return msrResponses;
    }

    public static List<MsrResponse> newTests(final Integer total, final Integer errors, final Integer failures, final Integer skipped, final Double success, final Double coverage) {
        final List<MsrResponse> msrResponses = new ArrayList<>();

        msrResponses.add(newMsr(KeyMsr.TESTS_TOTAL, String.valueOf(total)));
        msrResponses.add(newMsr(KeyMsr.TESTS_ERROR, String.valueOf(errors)));
        msrResponses.add(newMsr(KeyMsr.TESTS_FAILURES, String.valueOf(failures)));
        msrResponses.add(newMsr(KeyMsr.TESTS_SKIPPED, String.valueOf(skipped)));
        msrResponses.add(newMsr(KeyMsr.TESTS_SUCCESS, String.valueOf(success)));
        msrResponses.add(newMsr(KeyMsr.COVERAGE, String.valueOf(coverage)));

        return msrResponses;
    }


    private static MsrResponse newMsr(final KeyMsr keyMsr, final String val) {
        final MsrResponse msrResponse = new MsrResponse();
        msrResponse.setKey(keyMsr.KEY);
        msrResponse.setFrmtVal(val);
        msrResponse.setVal(val);
        return msrResponse;
    }

}
