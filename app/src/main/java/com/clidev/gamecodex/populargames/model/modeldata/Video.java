
package com.clidev.gamecodex.populargames.model.modeldata;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("video_id")
    @Expose
    private String videoId;
    public final static Creator<Video> CREATOR = new Creator<Video>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        public Video[] newArray(int size) {
            return (new Video[size]);
        }

    }
    ;

    protected Video(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.videoId = ((String) in.readValue((String.class.getClassLoader())));
    }

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(videoId);
    }

    public int describeContents() {
        return  0;
    }

}
