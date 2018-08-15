
package com.clidev.gamecodex.populargames.model.modeldata;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReleaseDate implements Parcelable
{


    @SerializedName("date")
    @Expose
    private Long date;

    @SerializedName("human")
    @Expose
    private String human;

    public final static Creator<ReleaseDate> CREATOR = new Creator<ReleaseDate>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ReleaseDate createFromParcel(Parcel in) {
            return new ReleaseDate(in);
        }

        public ReleaseDate[] newArray(int size) {
            return (new ReleaseDate[size]);
        }

    }
    ;

    protected ReleaseDate(Parcel in) {
        this.date = ((Long) in.readValue((Long.class.getClassLoader())));
        this.human = ((String) in.readValue((String.class.getClassLoader())));
    }

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
    public ReleaseDate(Long date, String human) {
        super();
        this.date = date;
        this.human = human;

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


    public void writeToParcel(Parcel dest, int flags) {

        dest.writeValue(date);

        dest.writeValue(human);

    }

    public int describeContents() {
        return  0;
    }

}
