package com.wordpress.fabiohbarbosa.notifier.sonar.web.client;

import com.wordpress.fabiohbarbosa.notifier.sonar.web.model.IssueResponse;
import com.wordpress.fabiohbarbosa.notifier.sonar.web.model.ResourceResponse;
import com.wordpress.fabiohbarbosa.notifier.sonar.web.model.Severity;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface SonarRestClient {
    @RequestLine("GET /api/resources?metrics=sqale_rating,sqale_debt_ratio&resource={key}")
    @Headers("Accept: application/json")
    List<ResourceResponse> ratings(@Param("key") String key);

    @RequestLine("GET /api/issues/search?severities={severity}&projectKeys={key}&statuses=OPEN,REOPENED")
    @Headers("Accept: application/json")
    IssueResponse issues(@Param("severity") Severity severity, @Param("key") String key);

    @RequestLine("GET /api/resources?resource={key}&metrics=duplicated_lines_density,duplicated_lines,duplicated_blocks,duplicated_files")
    @Headers("Accept: application/json")
    List<ResourceResponse> duplications(@Param("key") String key);

    @RequestLine("GET /api/resources?metrics=coverage,test_success_density,test_failures,test_errors,tests,skipped_tests&resource={key}")
    @Headers("Accept: application/json")
    List<ResourceResponse> tests(@Param("key") String key);
}
