package com.clidev.gamecodex.room.typeconverters;

import com.clidev.gamecodex.populargamescreen.model.modeldata.ReleaseDate;

public class ReleaseDateConverter {

    public static String toString(ReleaseDate releaseDate) {
        if (releaseDate != null) {
            Long date = releaseDate.getDate();
            String human = releaseDate.getHuman();

            String result = date + "," + human;
            return result;
        }

        return null;
    }


    public static ReleaseDate toReleaseDate(String releaseDateString) {
        if (releaseDateString.matches("")) {
            return null;
        } else {
            String[] split = releaseDateString.split(",");
            Long date = Long.parseLong(split[0]);
            String human = split[1];
            ReleaseDate releaseDate = new ReleaseDate(date,human);
            return releaseDate;
        }
    }
}
