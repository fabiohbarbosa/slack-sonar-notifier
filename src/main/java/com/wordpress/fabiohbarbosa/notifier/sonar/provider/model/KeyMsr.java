package com.wordpress.fabiohbarbosa.notifier.sonar.provider.model;

public enum KeyMsr {
    // RATING
    SQALE_RATING("sqale_rating", "Sqale Rating"), SQALE_DEBT_RATIO("sqale_debt_ratio", "Technical Debt Ratio"),

    // DUPLICATED
    DUPLICATED_LINES("duplicated_lines", "Duplicated Lines"), DUPLICATED_BLOCKS("duplicated_blocks", "Duplicated Blocks"), DUPLICATED_FILES("duplicated_files", "Duplicated Files"), DUPLICATED_LINES_DENSITY("duplicated_lines_density", "Duplicated"),

    // TESTS
    TESTS_TOTAL("tests", "Tests"), TESTS_ERROR("test_errors", "Errors"), TESTS_SKIPPED("skipped_tests", "Skipped"), TESTS_FAILURES("test_failures", "Failures"), TESTS_SUCCESS("test_success_density", "Unit Test Success"), COVERAGE("coverage", "Unit Tests Coverage");

    public String KEY;
    public String TEXT;

    KeyMsr(final String KEY, final String TEXT) {
        this.KEY = KEY;
        this.TEXT = TEXT;
    }
}
