package com.clidev.gamecodex.utilities;

import com.clidev.gamecodex.R;

import java.util.Random;

public class NavHeadImageRandomizer {

    public static int getRandomImage() {
        int[] imageIdArray = new int[]{
                R.drawable.mhw_wallpaper,
                R.drawable.dmc_wallpaper,
                R.drawable.ds_wallpaper,
                R.drawable.gow_wallpaper,
                R.drawable.sc2_wallpaper
        };

        int min = 0;
        int max = imageIdArray.length - 1;

        Random random = new Random();

        int randomIndex = random.nextInt(max - min + 1) + min;

        return imageIdArray[randomIndex];
    }
}
