package com.wordpress.fabiohbarbosa.notifier.sonar.provider.model;

import com.google.gson.annotations.SerializedName;

public enum SonarKeys {

    @SerializedName("lines")
    LINES_KEY("Lines"),

    @SerializedName("generated_lines")
    GENERATED_LINES_KEY("Generated Lines"),

    @SerializedName("ncloc")
    NCLOC_KEY("Lines of Code"),

    @SerializedName("new_lines")
    NEW_LINES_KEY("Lines on New Code"),

    @SerializedName("ncloc_language_distribution")
    NCLOC_LANGUAGE_DISTRIBUTION_KEY("Lines of Code Per Language"),

    @SerializedName("generated_ncloc")
    GENERATED_NCLOC_KEY("Generated Lines of Code"),

    @SerializedName("classes")
    CLASSES_KEY("Classes"),

    @SerializedName("files")
    FILES_KEY("Files"),

    @SerializedName("directories")
    DIRECTORIES_KEY("Directories"),

    @SerializedName("functions")
    FUNCTIONS_KEY("Functions"),

    @SerializedName("accessors")
    ACCESSORS_KEY("Accessors"),

    @SerializedName("statements")
    STATEMENTS_KEY("Statements"),

    @SerializedName("public_api")
    PUBLIC_API_KEY("Public API"),

    @SerializedName("projects")
    PROJECTS_KEY("Projects"),

    @SerializedName("comment_lines")
    COMMENT_LINES_KEY("Comment Lines"),

    @SerializedName("comment_lines_density")
    COMMENT_LINES_DENSITY_KEY("Comments (%)"),

    @SerializedName("public_documented_api_density")
    PUBLIC_DOCUMENTED_API_DENSITY_KEY("Public Documented API (%)"),

    @SerializedName("public_undocumented_api")
    PUBLIC_UNDOCUMENTED_API_KEY("Public Undocumented API"),

    @SerializedName("commented_out_code_lines")
    COMMENTED_OUT_CODE_LINES_KEY("Commented-Out LOC"),

    @SerializedName("complexity")
    COMPLEXITY_KEY("Complexity"),

    @SerializedName("file_complexity")
    FILE_COMPLEXITY_KEY("Complexity / File"),

    @SerializedName("complexity_in_classes")
    COMPLEXITY_IN_CLASSES_KEY("Complexity in Classes"),

    @SerializedName("class_complexity")
    CLASS_COMPLEXITY_KEY("Complexity / Class"),

    @SerializedName("complexity_in_functions")
    COMPLEXITY_IN_FUNCTIONS_KEY("Complexity in Functions"),

    @SerializedName("function_complexity")
    FUNCTION_COMPLEXITY_KEY("Complexity / Function"),

    @SerializedName("class_complexity_distribution")
    CLASS_COMPLEXITY_DISTRIBUTION_KEY("Class Distribution / Complexity"),

    @SerializedName("function_complexity_distribution")
    FUNCTION_COMPLEXITY_DISTRIBUTION_KEY("Function Distribution / Complexity"),

    @SerializedName("file_complexity_distribution")
    FILE_COMPLEXITY_DISTRIBUTION_KEY("File Distribution / Complexity"),

    @SerializedName("cognitive_complexity")
    COGNITIVE_COMPLEXITY_KEY("Cognitive Complexity"),

    @SerializedName("tests")
    TESTS_KEY("Unit Tests"),

    @SerializedName("test_execution_time")
    TEST_EXECUTION_TIME_KEY("Unit Test Duration"),

    @SerializedName("test_errors")
    TEST_ERRORS_KEY("Unit Test Errors"),

    @SerializedName("skipped_tests")
    SKIPPED_TESTS_KEY("Skipped Unit Tests"),

    @SerializedName("test_failures")
    TEST_FAILURES_KEY("Unit Test Failures"),

    @SerializedName("test_success_density")
    TEST_SUCCESS_DENSITY_KEY("Unit Test Success (%)"),

    @SerializedName("test_data")
    TEST_DATA_KEY("Unit Test Details"),

    @SerializedName("coverage")
    COVERAGE_KEY("Coverage"),

    @SerializedName("new_coverage")
    NEW_COVERAGE_KEY("Coverage on New Code"),

    @SerializedName("lines_to_cover")
    LINES_TO_COVER_KEY("Lines to Cover"),

    @SerializedName("new_lines_to_cover")
    NEW_LINES_TO_COVER_KEY("Lines to Cover on New Code"),

    @SerializedName("uncovered_lines")
    UNCOVERED_LINES_KEY("Uncovered Lines"),

    @SerializedName("new_uncovered_lines")
    NEW_UNCOVERED_LINES_KEY("Uncovered Lines on New Code"),

    @SerializedName("line_coverage")
    LINE_COVERAGE_KEY("Line Coverage"),

    @SerializedName("new_line_coverage")
    NEW_LINE_COVERAGE_KEY("Line Coverage on New Code"),

    @SerializedName("coverage_line_hits_data")
    COVERAGE_LINE_HITS_DATA_KEY("Coverage Hits by Line"),

    @SerializedName("conditions_to_cover")
    CONDITIONS_TO_COVER_KEY("Branches to Cover"),

    @SerializedName("new_conditions_to_cover")
    NEW_CONDITIONS_TO_COVER_KEY("Branches to Cover on New Code"),

    @SerializedName("uncovered_conditions")
    UNCOVERED_CONDITIONS_KEY("Uncovered Conditions"),

    @SerializedName("new_uncovered_conditions")
    NEW_UNCOVERED_CONDITIONS_KEY("Uncovered Conditions on New Code"),

    @SerializedName("branch_coverage")
    BRANCH_COVERAGE_KEY("Condition Coverage"),

    @SerializedName("new_branch_coverage")
    NEW_BRANCH_COVERAGE_KEY("Condition Coverage on New Code"),

    @SerializedName("conditions_by_line")
    CONDITIONS_BY_LINE_KEY("Conditions by Line"),

    @SerializedName("covered_conditions_by_line")
    COVERED_CONDITIONS_BY_LINE_KEY("Covered Conditions by Line"),

    @SerializedName("it_coverage")
    IT_COVERAGE_KEY("IT Coverage"),

    @SerializedName("new_it_coverage")
    NEW_IT_COVERAGE_KEY("Coverage by IT on New Code"),

    @SerializedName("it_lines_to_cover")
    IT_LINES_TO_COVER_KEY("IT Lines to Cover"),

    @SerializedName("new_it_lines_to_cover")
    NEW_IT_LINES_TO_COVER_KEY("Lines to Cover by IT on New Code"),

    @SerializedName("it_uncovered_lines")
    IT_UNCOVERED_LINES_KEY("IT Uncovered Lines"),

    @SerializedName("new_it_uncovered_lines")
    NEW_IT_UNCOVERED_LINES_KEY("Uncovered Lines by IT on New Code"),

    @SerializedName("it_line_coverage")
    IT_LINE_COVERAGE_KEY("IT Line Coverage"),

    @SerializedName("new_it_line_coverage")
    NEW_IT_LINE_COVERAGE_KEY("Line Coverage by IT on New Code"),

    @SerializedName("it_coverage_line_hits_data")
    IT_COVERAGE_LINE_HITS_DATA_KEY("IT Coverage Hits by Line"),

    @SerializedName("it_conditions_to_cover")
    IT_CONDITIONS_TO_COVER_KEY("IT Branches to Cover"),

    @SerializedName("new_it_conditions_to_cover")
    NEW_IT_CONDITIONS_TO_COVER_KEY("Branches to Cover by IT on New Code"),

    @SerializedName("it_uncovered_conditions")
    IT_UNCOVERED_CONDITIONS_KEY("IT Uncovered Conditions"),

    @SerializedName("new_it_uncovered_conditions")
    NEW_IT_UNCOVERED_CONDITIONS_KEY("Uncovered Conditions by IT on New Code"),

    @SerializedName("it_branch_coverage")
    IT_BRANCH_COVERAGE_KEY("IT Condition Coverage"),

    @SerializedName("new_it_branch_coverage")
    NEW_IT_BRANCH_COVERAGE_KEY("Condition Coverage by IT on New Code"),

    @SerializedName("it_conditions_by_line")
    IT_CONDITIONS_BY_LINE_KEY("IT Conditions by Line"),

    @SerializedName("it_covered_conditions_by_line")
    IT_COVERED_CONDITIONS_BY_LINE_KEY("IT Covered Conditions by Line"),

    @SerializedName("overall_coverage")
    OVERALL_COVERAGE_KEY("Overall Coverage"),

    @SerializedName("new_overall_coverage")
    NEW_OVERALL_COVERAGE_KEY("Overall Coverage on New Code"),

    @SerializedName("overall_lines_to_cover")
    OVERALL_LINES_TO_COVER_KEY("Overall Lines to Cover"),

    @SerializedName("new_overall_lines_to_cover")
    NEW_OVERALL_LINES_TO_COVER_KEY("Overall Lines to Cover on New Code"),

    @SerializedName("overall_uncovered_lines")
    OVERALL_UNCOVERED_LINES_KEY("Overall Uncovered Lines"),

    @SerializedName("new_overall_uncovered_lines")
    NEW_OVERALL_UNCOVERED_LINES_KEY("Overall Uncovered Lines on New Code"),

    @SerializedName("overall_line_coverage")
    OVERALL_LINE_COVERAGE_KEY("Overall Line Coverage"),

    @SerializedName("new_overall_line_coverage")
    NEW_OVERALL_LINE_COVERAGE_KEY("Overall Line Coverage on New Code"),

    @SerializedName("overall_coverage_line_hits_data")
    OVERALL_COVERAGE_LINE_HITS_DATA_KEY("Overall Coverage Hits by Line"),

    @SerializedName("overall_conditions_to_cover")
    OVERALL_CONDITIONS_TO_COVER_KEY("Overall Branches to Cover"),

    @SerializedName("new_overall_conditions_to_cover")
    NEW_OVERALL_CONDITIONS_TO_COVER_KEY("Overall Branches to Cover on New Code"),

    @SerializedName("overall_uncovered_conditions")
    OVERALL_UNCOVERED_CONDITIONS_KEY("Overall Uncovered Conditions"),

    @SerializedName("new_overall_uncovered_conditions")
    NEW_OVERALL_UNCOVERED_CONDITIONS_KEY("Overall Uncovered Conditions on New Code"),

    @SerializedName("overall_branch_coverage")
    OVERALL_BRANCH_COVERAGE_KEY("Overall Condition Coverage"),

    @SerializedName("new_overall_branch_coverage")
    NEW_OVERALL_BRANCH_COVERAGE_KEY("Overall Condition Coverage on New Code"),

    @SerializedName("overall_conditions_by_line")
    OVERALL_CONDITIONS_BY_LINE_KEY("Overall Conditions by Line"),

    @SerializedName("overall_covered_conditions_by_line")
    OVERALL_COVERED_CONDITIONS_BY_LINE_KEY("Overall Covered Conditions by Line"),

    @SerializedName("duplicated_lines")
    DUPLICATED_LINES_KEY("Duplicated Lines"),

    @SerializedName("new_duplicated_lines")
    NEW_DUPLICATED_LINES_KEY("Duplicated Lines on New Code"),

    @SerializedName("duplicated_blocks")
    DUPLICATED_BLOCKS_KEY("Duplicated Blocks"),

    @SerializedName("new_duplicated_blocks")
    NEW_BLOCKS_DUPLICATED_KEY("Duplicated Blocks on New Code"),

    @SerializedName("duplicated_files")
    DUPLICATED_FILES_KEY("Duplicated Files"),

    @SerializedName("duplicated_lines_density")
    DUPLICATED_LINES_DENSITY_KEY("Duplicated Lines (%)"),

    @SerializedName("new_duplicated_lines_density")
    NEW_DUPLICATED_LINES_DENSITY_KEY("Duplicated Lines on New Code (%)"),

    @SerializedName("duplications_data")
    DUPLICATIONS_DATA_KEY("Duplication Details"),

    @SerializedName("violations")
    VIOLATIONS_KEY("Issues"),

    @SerializedName("blocker_violations")
    BLOCKER_VIOLATIONS_KEY("Blocker Issues"),

    @SerializedName("critical_violations")
    CRITICAL_VIOLATIONS_KEY("Critical Issues"),

    @SerializedName("major_violations")
    MAJOR_VIOLATIONS_KEY("Major Issues"),

    @SerializedName("minor_violations")
    MINOR_VIOLATIONS_KEY("Minor Issues"),

    @SerializedName("info_violations")
    INFO_VIOLATIONS_KEY("Info Issues"),

    @SerializedName("new_violations")
    NEW_VIOLATIONS_KEY("New Issues"),

    @SerializedName("new_blocker_violations")
    NEW_BLOCKER_VIOLATIONS_KEY("New Blocker Issues"),

    @SerializedName("new_critical_violations")
    NEW_CRITICAL_VIOLATIONS_KEY("New Critical Issues"),

    @SerializedName("new_major_violations")
    NEW_MAJOR_VIOLATIONS_KEY("New Major Issues"),

    @SerializedName("new_minor_violations")
    NEW_MINOR_VIOLATIONS_KEY("New Minor Issues"),

    @SerializedName("new_info_violations")
    NEW_INFO_VIOLATIONS_KEY("New Info Issues"),

    @SerializedName("false_positive_issues")
    FALSE_POSITIVE_ISSUES_KEY("False Positive Issues"),

    @SerializedName("wont_fix_issues")
    WONT_FIX_ISSUES_KEY("Won't Fix Issues"),

    @SerializedName("open_issues")
    OPEN_ISSUES_KEY("Open Issues"),

    @SerializedName("reopened_issues")
    REOPENED_ISSUES_KEY("Reopened Issues"),

    @SerializedName("confirmed_issues")
    CONFIRMED_ISSUES_KEY("Confirmed Issues"),

    @SerializedName("code_smells")
    CODE_SMELLS_KEY("Code Smells"),

    @SerializedName("new_code_smells")
    NEW_CODE_SMELLS_KEY("New Code Smells"),

    @SerializedName("bugs")
    BUGS_KEY("Bugs"),

    @SerializedName("new_bugs")
    NEW_BUGS_KEY("New Bugs"),

    @SerializedName("vulnerabilities")
    VULNERABILITIES_KEY("Vulnerabilities"),

    @SerializedName("new_vulnerabilities")
    NEW_VULNERABILITIES_KEY("New Vulnerabilities"),

    @SerializedName("dsm")
    DEPENDENCY_MATRIX_KEY("Dependency Matrix"),

    @SerializedName("package_cycles")
    DIRECTORY_CYCLES_KEY("Directory Cycles"),

    @SerializedName("package_tangle_index")
    DIRECTORY_TANGLE_INDEX_KEY("Directory Tangle Index"),

    @SerializedName("package_tangles")
    DIRECTORY_TANGLES_KEY("File Dependencies to Cut"),

    @SerializedName("package_feedback_edges")
    DIRECTORY_FEEDBACK_EDGES_KEY("Package Dependencies to Cut"),

    @SerializedName("package_edges_weight")
    DIRECTORY_EDGES_WEIGHT_KEY("Directory Edges Weight"),

    @SerializedName("file_cycles")
    FILE_CYCLES_KEY("File Cycles"),

    @SerializedName("file_tangle_index")
    FILE_TANGLE_INDEX_KEY("File Tangle Index"),

    @SerializedName("file_tangles")
    FILE_TANGLES_KEY("File Tangles"),

    @SerializedName("file_feedback_edges")
    FILE_FEEDBACK_EDGES_KEY("Suspect File Dependencies"),

    @SerializedName("file_edges_weight")
    FILE_EDGES_WEIGHT_KEY("File Edges Weight"),

    @SerializedName("authors_by_line")
    SCM_AUTHORS_BY_LINE_KEY("Authors by Line"),

    @SerializedName("revisions_by_line")
    SCM_REVISIONS_BY_LINE_KEY("Revisions by Line"),

    @SerializedName("last_commit_datetimes_by_line")
    SCM_LAST_COMMIT_DATETIMES_BY_LINE_KEY("Last Commit Dates by Line"),

    @SerializedName("sqale_index")
    TECHNICAL_DEBT_KEY("Technical Debt"),

    @SerializedName("new_technical_debt")
    NEW_TECHNICAL_DEBT_KEY("Added Technical Debt"),

    @SerializedName("sqale_rating")
    SQALE_RATING_KEY("Maintainability Rating"),

    @SerializedName("new_maintainability_rating")
    NEW_MAINTAINABILITY_RATING_KEY("Maintainability Rating on New Code"),

    @SerializedName("development_cost")
    DEVELOPMENT_COST_KEY("SQALE Development Cost"),

    @SerializedName("sqale_debt_ratio")
    SQALE_DEBT_RATIO_KEY("Technical Debt Ratio"),

    @SerializedName("new_sqale_debt_ratio")
    NEW_SQALE_DEBT_RATIO_KEY("Technical Debt Ratio on New Code"),

    @SerializedName("effort_to_reach_maintainability_rating_a")
    EFFORT_TO_REACH_MAINTAINABILITY_RATING_A_KEY("Effort to Reach Maintainability Rating A"),

    @SerializedName("reliability_remediation_effort")
    RELIABILITY_REMEDIATION_EFFORT_KEY("Reliability Remediation Effort"),

    @SerializedName("new_reliability_remediation_effort")
    NEW_RELIABILITY_REMEDIATION_EFFORT_KEY("Reliability Remediation Effort on New Code"),

    @SerializedName("reliability_rating")
    RELIABILITY_RATING_KEY("Reliability Rating"),

    @SerializedName("new_reliability_rating")
    NEW_RELIABILITY_RATING_KEY("Reliability Rating on New Code"),

    @SerializedName("security_remediation_effort")
    SECURITY_REMEDIATION_EFFORT_KEY("Security Remediation Effort"),

    @SerializedName("new_security_remediation_effort")
    NEW_SECURITY_REMEDIATION_EFFORT_KEY("Security Remediation Effort on New Code"),

    @SerializedName("security_rating")
    SECURITY_RATING_KEY("Security Rating"),

    @SerializedName("new_security_rating")
    NEW_SECURITY_RATING_KEY("Security Rating on New Code"),

    @SerializedName("ncloc_data")
    NCLOC_DATA_KEY("ncloc_data"),

    @SerializedName("comment_lines_data")
    COMMENT_LINES_DATA_KEY("comment_lines_data"),

    @SerializedName("executable_lines_data")
    EXECUTABLE_LINES_DATA_KEY("executable_lines_data"),

    @SerializedName("alert_status")
    ALERT_STATUS_KEY("Quality Gate Status"),

    @SerializedName("quality_gate_details")
    QUALITY_GATE_DETAILS_KEY("Quality Gate Details"),

    @SerializedName("quality_profiles")
    QUALITY_PROFILES_KEY("Profiles"),

    @SerializedName("last_commit_date")
    LAST_COMMIT_DATE_KEY("Date of Last Commit");

    public String NAME;

    SonarKeys(String NAME) {
        this.NAME = NAME;
    }
}
