
package com.clidev.gamecodex.populargames.model.modeldata;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artwork implements Parcelable
{

    @SerializedName("url")
    @Expose
    private String url;

    public final static Creator<Artwork> CREATOR = new Creator<Artwork>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Artwork createFromParcel(Parcel in) {
            return new Artwork(in);
        }

        public Artwork[] newArray(int size) {
            return (new Artwork[size]);
        }

    }
    ;

    protected Artwork(Parcel in) {
        this.url = ((String) in.readValue((String.class.getClassLoader())));
    }

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


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(url);
    }

    public int describeContents() {
        return  0;
    }

}
