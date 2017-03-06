package com.wordpress.fabiohbarbosa.notifier.slack.web.model;

import com.google.gson.annotations.SerializedName;

public class Field {
    private String value;
    @SerializedName("short")
    private Boolean isShort;

    public Field() {
    }

    public Field(final String value, final Boolean isShort) {
        this.value = value;
        this.isShort = isShort;
    }

    public String getValue() {
        return value;
    }

    public Boolean getShort() {
        return isShort;
    }

    @Override
    public String toString() {
        return "Field{" +
                ", value='" + value + '\'' +
                ", isShort=" + isShort +
                '}';
    }
}
