package com.clidev.gamecodex.room.typeconverters;

import com.clidev.gamecodex.populargamescreen.model.modeldata.ReleaseDate;

public class ReleaseDateConverter {

    public static String toString(ReleaseDate releaseDate) {
        if (releaseDate != null) {
            Integer platform = releaseDate.getPlatform();
            Long date = releaseDate.getDate();
            String human = releaseDate.getHuman();

            String result = platform + "," + date + "," + human;
            return result;
        }

        return null;
    }


    public static ReleaseDate toReleaseDate(String releaseDateString) {
        if (releaseDateString.matches("")) {
            return null;
        } else {
            String[] split = releaseDateString.split(",");
            Integer platform = Integer.parseInt(split[0]);
            Long date = Long.parseLong(split[1]);
            String human = split[2];
            ReleaseDate releaseDate = new ReleaseDate(platform,date,human);
            return releaseDate;
        }
    }
}
