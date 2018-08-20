package com.clidev.gamecodex.room.typeconverters;

import com.clidev.gamecodex.populargamescreen.model.modeldata.MultiplayerMode;

public class MultiplayerModeTypeConverter {

    public static String toString(MultiplayerMode multiplayerMode) {
        if (multiplayerMode != null) {
            Long platform = multiplayerMode.getPlatform();
            Boolean offlinecoop = multiplayerMode.getOfflinecoop();
            Boolean onlinecoop = multiplayerMode.getOnlinecoop();
            Boolean lancoop = multiplayerMode.getLancoop();
            Boolean campaigncoop = multiplayerMode.getCampaigncoop();
            Boolean splitscreenonline = multiplayerMode.getSplitscreenonline();
            Boolean splitscreen = multiplayerMode.getSplitscreen();
            Boolean dropin = multiplayerMode.getDropin();
            Long offlinecoopmax = multiplayerMode.getOfflinecoopmax();
            Long onlinecoopmax = multiplayerMode.getOnlinecoopmax();
            Long onlinemax = multiplayerMode.getOnlinemax();
            Long offlinemax = multiplayerMode.getOfflinemax();

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

            Long platform = Long.parseLong(split[0]);
            Boolean offlinecoop = Boolean.parseBoolean(split[1]);
            Boolean onlinecoop = Boolean.parseBoolean(split[2]);
            Boolean lancoop = Boolean.parseBoolean(split[3]);
            Boolean campaigncoop = Boolean.parseBoolean(split[4]);
            Boolean splitscreenonline = Boolean.parseBoolean(split[5]);
            Boolean splitscreen = Boolean.parseBoolean(split[6]);
            Boolean dropin = Boolean.parseBoolean(split[7]);
            Long offlinecoopmax = Long.parseLong(split[8]);
            Long onlinecoopmax = Long.parseLong(split[9]);
            Long onlinemax = Long.parseLong(split[10]);
            Long offlinemax = Long.parseLong(split[11]);

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

