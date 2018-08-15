package com.clidev.gamecodex.populargames.model.typeconverters;

import com.clidev.gamecodex.populargames.model.modeldata.Cover;

public class CoverTypeConverter {

    public static String toString(Cover cover) {
        if (cover != null) {
            String url = cover.getUrl();
            return url;
        }
        return null;
    }

    public static Cover toCover(String string) {
        if (string.matches("")) {
            return null;
        } else {
            Cover cover = new Cover(string);
            return cover;
        }
    }
}
