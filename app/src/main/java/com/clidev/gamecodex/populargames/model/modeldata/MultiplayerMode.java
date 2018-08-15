
package com.clidev.gamecodex.populargames.model.modeldata;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MultiplayerMode implements Parcelable
{

    @SerializedName("platform")
    @Expose
    private Integer platform;
    @SerializedName("offlinecoop")
    @Expose
    private Boolean offlinecoop;
    @SerializedName("onlinecoop")
    @Expose
    private Boolean onlinecoop;
    @SerializedName("lancoop")
    @Expose
    private Boolean lancoop;
    @SerializedName("campaigncoop")
    @Expose
    private Boolean campaigncoop;
    @SerializedName("splitscreenonline")
    @Expose
    private Boolean splitscreenonline;
    @SerializedName("splitscreen")
    @Expose
    private Boolean splitscreen;
    @SerializedName("dropin")
    @Expose
    private Boolean dropin;
    @SerializedName("offlinecoopmax")
    @Expose
    private Integer offlinecoopmax;
    @SerializedName("onlinecoopmax")
    @Expose
    private Integer onlinecoopmax;
    @SerializedName("onlinemax")
    @Expose
    private Integer onlinemax;
    @SerializedName("offlinemax")
    @Expose
    private Integer offlinemax;
    public final static Creator<MultiplayerMode> CREATOR = new Creator<MultiplayerMode>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MultiplayerMode createFromParcel(Parcel in) {
            return new MultiplayerMode(in);
        }

        public MultiplayerMode[] newArray(int size) {
            return (new MultiplayerMode[size]);
        }

    }
    ;

    protected MultiplayerMode(Parcel in) {
        this.platform = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.offlinecoop = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.onlinecoop = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.lancoop = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.campaigncoop = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.splitscreenonline = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.splitscreen = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.dropin = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.offlinecoopmax = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.onlinecoopmax = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.onlinemax = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.offlinemax = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public MultiplayerMode() {
    }

    /**
     * 
     * @param dropin
     * @param offlinemax
     * @param platform
     * @param splitscreenonline
     * @param onlinecoopmax
     * @param campaigncoop
     * @param lancoop
     * @param onlinemax
     * @param onlinecoop
     * @param splitscreen
     * @param offlinecoopmax
     * @param offlinecoop
     */
    public MultiplayerMode(Integer platform, Boolean offlinecoop, Boolean onlinecoop, Boolean lancoop, Boolean campaigncoop, Boolean splitscreenonline, Boolean splitscreen, Boolean dropin, Integer offlinecoopmax, Integer onlinecoopmax, Integer onlinemax, Integer offlinemax) {
        super();
        this.platform = platform;
        this.offlinecoop = offlinecoop;
        this.onlinecoop = onlinecoop;
        this.lancoop = lancoop;
        this.campaigncoop = campaigncoop;
        this.splitscreenonline = splitscreenonline;
        this.splitscreen = splitscreen;
        this.dropin = dropin;
        this.offlinecoopmax = offlinecoopmax;
        this.onlinecoopmax = onlinecoopmax;
        this.onlinemax = onlinemax;
        this.offlinemax = offlinemax;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Boolean getOfflinecoop() {
        return offlinecoop;
    }

    public void setOfflinecoop(Boolean offlinecoop) {
        this.offlinecoop = offlinecoop;
    }

    public Boolean getOnlinecoop() {
        return onlinecoop;
    }

    public void setOnlinecoop(Boolean onlinecoop) {
        this.onlinecoop = onlinecoop;
    }

    public Boolean getLancoop() {
        return lancoop;
    }

    public void setLancoop(Boolean lancoop) {
        this.lancoop = lancoop;
    }

    public Boolean getCampaigncoop() {
        return campaigncoop;
    }

    public void setCampaigncoop(Boolean campaigncoop) {
        this.campaigncoop = campaigncoop;
    }

    public Boolean getSplitscreenonline() {
        return splitscreenonline;
    }

    public void setSplitscreenonline(Boolean splitscreenonline) {
        this.splitscreenonline = splitscreenonline;
    }

    public Boolean getSplitscreen() {
        return splitscreen;
    }

    public void setSplitscreen(Boolean splitscreen) {
        this.splitscreen = splitscreen;
    }

    public Boolean getDropin() {
        return dropin;
    }

    public void setDropin(Boolean dropin) {
        this.dropin = dropin;
    }

    public Integer getOfflinecoopmax() {
        return offlinecoopmax;
    }

    public void setOfflinecoopmax(Integer offlinecoopmax) {
        this.offlinecoopmax = offlinecoopmax;
    }

    public Integer getOnlinecoopmax() {
        return onlinecoopmax;
    }

    public void setOnlinecoopmax(Integer onlinecoopmax) {
        this.onlinecoopmax = onlinecoopmax;
    }

    public Integer getOnlinemax() {
        return onlinemax;
    }

    public void setOnlinemax(Integer onlinemax) {
        this.onlinemax = onlinemax;
    }

    public Integer getOfflinemax() {
        return offlinemax;
    }

    public void setOfflinemax(Integer offlinemax) {
        this.offlinemax = offlinemax;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(platform);
        dest.writeValue(offlinecoop);
        dest.writeValue(onlinecoop);
        dest.writeValue(lancoop);
        dest.writeValue(campaigncoop);
        dest.writeValue(splitscreenonline);
        dest.writeValue(splitscreen);
        dest.writeValue(dropin);
        dest.writeValue(offlinecoopmax);
        dest.writeValue(onlinecoopmax);
        dest.writeValue(onlinemax);
        dest.writeValue(offlinemax);
    }

    public int describeContents() {
        return  0;
    }

}
