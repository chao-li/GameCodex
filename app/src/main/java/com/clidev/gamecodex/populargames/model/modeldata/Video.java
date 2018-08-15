
package com.clidev.gamecodex.populargames.model.modeldata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("video_id")
    @Expose
    private String videoId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Video() {
    }

    /**
     * 
     * @param videoId
     * @param name
     */
    public Video(String name, String videoId) {
        super();
        this.name = name;
        this.videoId = videoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

}
