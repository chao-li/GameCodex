
package com.clidev.gamecodex.populargames.model.modeldata;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cover implements Parcelable
{

    @SerializedName("url")
    @Expose
    private String url;


    public final static Creator<Cover> CREATOR = new Creator<Cover>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Cover createFromParcel(Parcel in) {
            return new Cover(in);
        }

        public Cover[] newArray(int size) {
            return (new Cover[size]);
        }

    }
    ;

    protected Cover(Parcel in) {
        this.url = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Cover() {
    }

    /**
     *
     * @param url
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



    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(url);

    }

    public int describeContents() {
        return  0;
    }

}
