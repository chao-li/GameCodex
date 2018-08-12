package com.clidev.gamecodex.utilities;

public final class ImageUrlEditor {

    public static String getBigCoverUrl(String imageUrl) {
        String bigCoverUrl = "";

        //images.igdb.com/igdb/image/upload/t_thumb/fxgwm1nnyexhvauqv0ds.jpg"
        String[] splitUrl = imageUrl.split("t_thumb");

        if (splitUrl.length >= 2) {
            bigCoverUrl = "https:" + splitUrl[0] + "t_cover_big" + splitUrl[1];
        } else {
            return imageUrl;
        }

        return bigCoverUrl;
    }

}


