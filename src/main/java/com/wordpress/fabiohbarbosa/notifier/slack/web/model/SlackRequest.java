package com.wordpress.fabiohbarbosa.notifier.slack.web.model;

import java.util.ArrayList;
import java.util.List;

public class SlackRequest {
    private boolean mrkdwn = true;
    private String text;
    private List<Attachment> attachments;

    public void addAttachment(Attachment attachment) {
        if (attachments == null) {
            attachments = new ArrayList<>();
        }
        attachments.add(attachment);
    }

    public void setText(final String text) {
        this.text = text;
    }

    public void setAttachments(final List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getText() {
        return text;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    @Override
    public String toString() {
        return "Data{" +
                "mrkdwn=" + mrkdwn +
                ", text='" + text + '\'' +
                ", attachments=" + attachments +
                '}';
    }
}
