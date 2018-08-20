package com.clidev.gamecodex.room.typeconverters;

import com.clidev.gamecodex.populargamescreen.model.modeldata.Cover;

public class CoverTypeConverter {

    public static String toString(Cover cover) {
        if (cover != null) {
            String url = cover.getUrl();
            return url;
        }
        return null;
    }

    public static Cover toCover(String string) {
        if (string != null) {
            Cover cover = new Cover(string);
            return cover;
        }
        return null;
    }
}
