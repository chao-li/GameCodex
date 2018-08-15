package com.clidev.gamecodex.populargames.model.typeconverters;

import com.clidev.gamecodex.populargames.model.modeldata.Artwork;
import com.clidev.gamecodex.populargames.model.modeldata.Cover;

public class ArtworkTypeConverter {

    public static String toString(Artwork artwork) {
        if (artwork != null) {
            String url = artwork.getUrl();
            return url;
        }
        return null;
    }

    public static Artwork toArtwork(String string) {
        if (string != null) {
            Artwork artwork = new Artwork(string);
            return artwork;
        }
        return null;
    }

}
