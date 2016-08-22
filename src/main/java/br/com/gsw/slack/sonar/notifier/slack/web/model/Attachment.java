package br.com.gsw.slack.sonar.notifier.slack.web.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Attachment {
    private String title;
    @SerializedName("title_link")
    private String titleLink;
    @SerializedName("pretext")
    private String preText;
    private String color;
    private List<Field> fields;
    @SerializedName("mrkdwn_in")
    private List<String> mrkdwnIn;

    public Attachment() {
        if (mrkdwnIn == null) {
            mrkdwnIn = new ArrayList<>();
        }
        mrkdwnIn.add("fields");
        mrkdwnIn.add("pretext");
    }

    public void addField(Field field) {
        if (field == null) {
            return;
        }
        if (fields == null) {
            fields = new ArrayList<>();
        }
        fields.add(field);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getTitleLink() {
        return titleLink;
    }

    public void setTitleLink(final String titleLink) {
        this.titleLink = titleLink;
    }

    public String getPreText() {
        return preText;
    }

    public void setPreText(final String preText) {
        this.preText = preText;
    }

    public List<String> getMrkdwnIn() {
        return mrkdwnIn;
    }

    public void setMrkdwnIn(final List<String> mrkdwnIn) {
        this.mrkdwnIn = mrkdwnIn;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(final List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "color='" + color + '\'' +
                ", fields=" + fields +
                '}';
    }
}
