package br.com.gsw.slack.sonar.notifier.slack.web.model;

public class SlackRequestFixture {
    private SlackRequestFixture() {
        // block constructor
    }

    public static SlackRequest newSlackRequest() {
        final SlackRequest slackRequest = new SlackRequest();
        slackRequest.setMrkdwn(true);
        slackRequest.setText("Text");
        final Attachment attachment = new Attachment();
        attachment.addField(new Field("field1", true));
        attachment.addField(new Field("field2", true));
        slackRequest.addAttachment(attachment);
        return slackRequest;
    }
}