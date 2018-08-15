package com.clidev.gamecodex.populargames.model.typeconverters;

import com.clidev.gamecodex.populargames.model.modeldata.Artwork;
import com.clidev.gamecodex.populargames.model.modeldata.MultiplayerMode;

public class MultiplayerModeTypeConverter {

    public static String toString(MultiplayerMode multiplayerMode) {
        if (multiplayerMode != null) {
            Integer platform = multiplayerMode.getPlatform();
            Boolean offlinecoop = multiplayerMode.getOfflinecoop();
            Boolean onlinecoop = multiplayerMode.getOnlinecoop();
            Boolean lancoop = multiplayerMode.getLancoop();
            Boolean campaigncoop = multiplayerMode.getCampaigncoop();
            Boolean splitscreenonline = multiplayerMode.getSplitscreenonline();
            Boolean splitscreen = multiplayerMode.getSplitscreen();
            Boolean dropin = multiplayerMode.getDropin();
            Integer offlinecoopmax = multiplayerMode.getOfflinecoopmax();
            Integer onlinecoopmax = multiplayerMode.getOnlinecoopmax();
            Integer onlinemax = multiplayerMode.getOnlinemax();
            Integer offlinemax = multiplayerMode.getOfflinemax();

            String string = platform + "," +
                    offlinecoop + "," +
                    onlinecoop + "," +
                    lancoop + "," +
                    campaigncoop + "," +
                    splitscreenonline + "," +
                    splitscreen + "," +
                    dropin + "," +
                    offlinecoopmax + "," +
                    onlinecoopmax + "," +
                    onlinemax + "," +
                    offlinemax;

            return string;
        }
        return null;
    }

    public static MultiplayerMode toMultiplayerMode(String string) {
        if (string != null) {
            String[] split = string.split(",");

            Integer platform = Integer.parseInt(split[0]);
            Boolean offlinecoop = Boolean.parseBoolean(split[1]);
            Boolean onlinecoop = Boolean.parseBoolean(split[2]);
            Boolean lancoop = Boolean.parseBoolean(split[3]);
            Boolean campaigncoop = Boolean.parseBoolean(split[4]);
            Boolean splitscreenonline = Boolean.parseBoolean(split[5]);
            Boolean splitscreen = Boolean.parseBoolean(split[6]);
            Boolean dropin = Boolean.parseBoolean(split[7]);
            Integer offlinecoopmax = Integer.parseInt(split[8]);
            Integer onlinecoopmax = Integer.parseInt(split[9]);
            Integer onlinemax = Integer.parseInt(split[10]);
            Integer offlinemax = Integer.parseInt(split[11]);

            MultiplayerMode multiplayerMode = new MultiplayerMode(platform,
                    offlinecoop,
                    onlinecoop,
                    lancoop,
                    campaigncoop,
                    splitscreenonline,
                    splitscreen,
                    dropin,
                    offlinecoopmax,
                    onlinecoopmax,
                    onlinemax,
                    offlinemax);

            return multiplayerMode;
        }
        return null;
    }
}

