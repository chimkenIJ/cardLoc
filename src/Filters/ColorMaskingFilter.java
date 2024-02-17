package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class ColorMaskingFilter implements PixelFilter {
    private final short r = 255, g = 255, b = 255, g1 = 110, b1 = 9;
    private final short b2 = 139;
    /* private final short r = 255, g=255, b = 255, g1 =255, b1 = 255;
     private final short b2 =255;*/
    private final double threshold = 80;


    public ColorMaskingFilter() {
    }

    @Override
    public DImage processImage(DImage img) {
        DImage tempImg = img;
        short[][] red = tempImg.getRedChannel();
        short[][] green = tempImg.getGreenChannel();
        short[][] blue = tempImg.getBlueChannel();
        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[0].length; j++) {
                if (Math.abs(getDist(red[i][j], green[i][j], blue[i][j]) - getDist(r, g, b)) < threshold) {
                    red[i][j] = 255;
                    green[i][j] = 255;
                    blue[i][j] = 255;
                } else {
                    red[i][j] = 0;
                    green[i][j] = 0;
                    blue[i][j] = 0;
                }

            }
        }
        tempImg.setColorChannels(red, green, blue);
        return tempImg;
    }

    public DImage processImage(DImage img, DImage realImage) {
        short[][] red = realImage.getRedChannel();
        short[][] green = realImage.getGreenChannel();
        short[][] blue = realImage.getBlueChannel();
        short[][] image = img.getBWPixelGrid();
        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[0].length; j++) {

               // if (image[i][j] == 0) {
                    if (((Math.abs(red[i][j] - r) < threshold) && (Math.abs(blue[i][j] - b) > threshold)) && (Math.abs(green[i][j] - g) > threshold)) {
                        red[i][j] = 255;
                        green[i][j] = 0;
                        blue[i][j] = 0;
                    } else if (((Math.abs(red[i][j] - r) > threshold) && (Math.abs(blue[i][j] - b1) < threshold)) && (Math.abs(green[i][j] - g1) < threshold)) {
                        red[i][j] = 0;
                        green[i][j] = 255;
                        blue[i][j] = 0;
                    } else if (((Math.abs(red[i][j] - r) > threshold) && (Math.abs(blue[i][j] - b2) < threshold)) && (Math.abs(green[i][j] - g) > threshold)) {
                        red[i][j] = 0;
                        green[i][j] = 0;
                        blue[i][j] = 255;
                    } else {
                        red[i][j] = 0;
                        green[i][j] = 0;
                        blue[i][j] = 0;
                    }
                }
            }
        //}


        realImage.setColorChannels(red, green, blue);
        return realImage;
    }

    private double getDist(short r, short g, short b) {
        return Math.sqrt((r * r + g * g + b * b));
    }


}

