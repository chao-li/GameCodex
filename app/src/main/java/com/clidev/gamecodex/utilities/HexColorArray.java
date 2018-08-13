package com.clidev.gamecodex.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class HexColorArray {

    private static List<String> getHexColorArray() {
        List<String> hexColorArray = new ArrayList<>();


        String[] colorArray = new String[]{
                "#ff0000",
                "#800080",
                "#f85a40",
                "#3369e7",
                "#a51890",
                "#0389ff",
                "#fc636b",
                "#6534ac",
                "#2db928",
                "#cf0072",
                "#14d4f4"
        };

        hexColorArray = Arrays.asList(colorArray);



        return hexColorArray;
    }

    public static String getRandomColor() {
        int min = 0;
        int max = getHexColorArray().size() - 1;

        Random random = new Random();

        int randomIndex = random.nextInt(max - min + 1) + min;

        List<String> hexColorArray = getHexColorArray();

        return hexColorArray.get(randomIndex);
    }
}
