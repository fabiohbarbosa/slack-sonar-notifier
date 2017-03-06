package com.wordpress.fabiohbarbosa.notifier.slack.service;

import com.wordpress.fabiohbarbosa.notifier.plugin.factory.LogFactory;
import com.wordpress.fabiohbarbosa.notifier.slack.model.Slack;
import com.wordpress.fabiohbarbosa.notifier.slack.web.client.SlackRestClient;
import com.wordpress.fabiohbarbosa.notifier.slack.web.model.SlackRequest;
import com.wordpress.fabiohbarbosa.notifier.web.client.FeignFactory;
import com.google.gson.GsonBuilder;
import org.apache.maven.plugin.logging.Log;

public class SlackPusher {
    private static final Log LOGGER = LogFactory.getInstance();
    private final static String SLACK_URL = "https://hooks.slack.com/services/";


    public void slackPusher(final Slack slack, final SlackRequest slackRequest) {
        LOGGER.info(String.format("Trying to post sonar stats into slack %s", slack.getWebhook()));

        // Remove slack uri
        final String token = slack.getWebhook().split(SLACK_URL)[1];
        final SlackRestClient client = FeignFactory.build(SLACK_URL, SlackRestClient.class);

        LOGGER.debug(String.format("Slack POST URL: %s", SLACK_URL));
        LOGGER.debug(new GsonBuilder().setPrettyPrinting().create().toJson(slackRequest));

        client.post(token, slackRequest);

        LOGGER.info(String.format("Notifier slack %s", slack.getWebhook()));
    }
}
