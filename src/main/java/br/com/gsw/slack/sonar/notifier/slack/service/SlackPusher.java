package br.com.gsw.slack.sonar.notifier.slack.service;

import br.com.gsw.slack.sonar.notifier.plugin.factory.LogFactory;
import br.com.gsw.slack.sonar.notifier.slack.model.Slack;
import br.com.gsw.slack.sonar.notifier.slack.web.client.SlackRestClient;
import br.com.gsw.slack.sonar.notifier.slack.web.model.SlackRequest;
import br.com.gsw.slack.sonar.notifier.web.client.FeignFactory;
import com.google.gson.Gson;
import org.apache.maven.plugin.logging.Log;

public class SlackPusher {
    private static final Log LOGGER = LogFactory.getInstance();
    private final static String SLACK_URL = "https://hooks.slack.com/services/";


    public void slackPusher(final Slack slack, final SlackRequest slackRequest) {
        LOGGER.info(String.format("Trying to post sonar stats into slack %s", slack.getWebhook()));

        final Gson gson = new Gson();
        LOGGER.debug(gson.toJson(slackRequest));

        // Remove slack uri
        final String token = slack.getWebhook().split(SLACK_URL)[1];

        final SlackRestClient client = FeignFactory.build(SLACK_URL, SlackRestClient.class);
        client.post(token, slackRequest);

        LOGGER.info(String.format("Notifier slack %s", slack.getWebhook()));
    }
}
