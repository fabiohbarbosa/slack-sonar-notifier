package com.wordpress.fabiohbarbosa.notifier.sonar.provider.client;

import com.wordpress.fabiohbarbosa.notifier.sonar.model.Project;
import com.wordpress.fabiohbarbosa.notifier.sonar.provider.model.QualityGate;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface SonarRestClient {
    @RequestLine("GET /api/qualitygates/project_status?projectKey={key}")
    @Headers("Accept: application/json")
    QualityGate qualityGates(@Param("key") String key);

    @RequestLine("GET /api/projects/index?key={key}&versions=last")
    @Headers("Accept: application/json")
    List<Project> findProject(@Param("key") String key);
}
