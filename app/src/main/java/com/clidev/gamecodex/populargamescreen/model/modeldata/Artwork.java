
package com.clidev.gamecodex.populargamescreen.model.modeldata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artwork {

    @SerializedName("url")
    @Expose
    private String url;


    /**
     * No args constructor for use in serialization
     * 
     */
    public Artwork() {
    }

    /**
     *
     * @param url
     */
    public Artwork(String url) {
        super();
        this.url = url;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
