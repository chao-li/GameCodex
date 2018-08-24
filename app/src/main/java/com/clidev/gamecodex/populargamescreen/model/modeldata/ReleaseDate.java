
package com.clidev.gamecodex.populargamescreen.model.modeldata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReleaseDate {

    @SerializedName("platform")
    @Expose
    private Integer platform;
    @SerializedName("date")
    @Expose
    private Long date;

    @SerializedName("human")
    @Expose
    private String human;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReleaseDate() {
    }

    /**
     * 

     * @param date

     * @param human
     */
    public ReleaseDate(Integer platform, Long date, String human) {
        super();

        this.platform = platform;
        this.date = date;
        this.human = human;

    }


    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }


    public String getHuman() {
        return human;
    }

    public void setHuman(String human) {
        this.human = human;
    }


}
