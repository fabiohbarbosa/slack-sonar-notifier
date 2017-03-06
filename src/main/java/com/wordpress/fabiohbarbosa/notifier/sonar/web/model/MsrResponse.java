package com.wordpress.fabiohbarbosa.notifier.sonar.web.model;

import com.google.gson.annotations.SerializedName;

public class MsrResponse {
    private String key;
    private String val;
    @SerializedName("frmt_val")
    private String frmtVal;

    public MsrResponse() {

    }

    public MsrResponse(final String key, final String val, final String frmtVal) {
        this.key = key;
        this.val = val;
        this.frmtVal = frmtVal;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(final String val) {
        this.val = val;
    }

    public String getFrmtVal() {
        return frmtVal;
    }

    public void setFrmtVal(final String frmtVal) {
        this.frmtVal = frmtVal;
    }

    @Override
    public String toString() {
        return "Msr{" +
                "key='" + key + '\'' +
                ", val='" + val + '\'' +
                ", frmtVal='" + frmtVal + '\'' +
                '}';
    }
}
