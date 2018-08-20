package com.clidev.gamecodex.populargamescreen.model.modeldata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Screenshot {

    @SerializedName("url")
    @Expose
    private String url = null;

    public Screenshot(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
