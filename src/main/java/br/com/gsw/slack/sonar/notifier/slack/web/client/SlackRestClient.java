package br.com.gsw.slack.sonar.notifier.slack.web.client;

import br.com.gsw.slack.sonar.notifier.slack.web.model.SlackRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface SlackRestClient {
    @RequestLine("POST /{token}")
    @Headers("Content-Type: application/json")
    void post(@Param("token") String token, SlackRequest request);

}
