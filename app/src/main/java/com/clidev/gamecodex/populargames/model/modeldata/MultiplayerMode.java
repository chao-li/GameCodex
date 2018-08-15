
package com.clidev.gamecodex.populargames.model.modeldata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MultiplayerMode {

    @SerializedName("platform")
    @Expose
    private Long platform;
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
    private Long offlinecoopmax;
    @SerializedName("onlinecoopmax")
    @Expose
    private Long onlinecoopmax;
    @SerializedName("onlinemax")
    @Expose
    private Long onlinemax;
    @SerializedName("offlinemax")
    @Expose
    private Long offlinemax;

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
    public MultiplayerMode(Long platform, Boolean offlinecoop, Boolean onlinecoop, Boolean lancoop, Boolean campaigncoop, Boolean splitscreenonline, Boolean splitscreen, Boolean dropin, Long offlinecoopmax, Long onlinecoopmax, Long onlinemax, Long offlinemax) {
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

    public Long getPlatform() {
        return platform;
    }

    public void setPlatform(Long platform) {
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

    public Long getOfflinecoopmax() {
        return offlinecoopmax;
    }

    public void setOfflinecoopmax(Long offlinecoopmax) {
        this.offlinecoopmax = offlinecoopmax;
    }

    public Long getOnlinecoopmax() {
        return onlinecoopmax;
    }

    public void setOnlinecoopmax(Long onlinecoopmax) {
        this.onlinecoopmax = onlinecoopmax;
    }

    public Long getOnlinemax() {
        return onlinemax;
    }

    public void setOnlinemax(Long onlinemax) {
        this.onlinemax = onlinemax;
    }

    public Long getOfflinemax() {
        return offlinemax;
    }

    public void setOfflinemax(Long offlinemax) {
        this.offlinemax = offlinemax;
    }

}
