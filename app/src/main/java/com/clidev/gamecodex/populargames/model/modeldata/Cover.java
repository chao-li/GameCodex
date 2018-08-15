
package com.clidev.gamecodex.populargames.model.modeldata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cover {

    @SerializedName("url")
    @Expose
    private String url;


    /**
     * No args constructor for use in serialization
     * 
     */
    public Cover() {
    }

    /**
     *
     */
    public Cover(String url) {
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
