package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class getColorFilter implements PixelFilter {
    public DImage processImage(DImage img) {
        return img;
    }

    public String getColor(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        int red1 = 0, green1 = 0, blue1 = 0;

        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[0].length; j++) {
                red1 += red[i][j];
                green1 += green[i][j];
                blue1 += blue[i][j];
            }

        }
        return getMax(red1, green1, blue1);
    }

    private String getMax(int a, int b, int c) {
        if (a > b && a > c) {
            return "red";
        } else if (b > a && b > c) {
            return "green";
        } else {

            return "blue";
        }
    }
}
