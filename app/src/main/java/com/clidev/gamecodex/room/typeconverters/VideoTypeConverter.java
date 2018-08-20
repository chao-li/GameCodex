package com.clidev.gamecodex.room.typeconverters;

import com.clidev.gamecodex.populargamescreen.model.modeldata.Video;

public class VideoTypeConverter {

    public static String toString(Video video) {
        if (video != null) {
            String name = video.getName();
            String videoId = video.getVideoId();

            String string = name + "," + videoId;
            return string;
        }
        return null;
    }

    public static Video toVideo(String string) {
        if (string != null) {
            String[] split = string.split(",");

            String name = split[0];
            String videoId = split[1];

            Video video = new Video(name, videoId);
            return video;
        }
        return null;
    }

}
