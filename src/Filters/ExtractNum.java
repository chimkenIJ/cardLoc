package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class ExtractNum implements PixelFilter {


    @Override
    public DImage processImage(DImage img) {
        return img;
    }

    public String getNumber(DImage img) {
        int length, width;
        short[][] image = img.getBWPixelGrid();

        if (image[0].length > image.length) {
            length = image[0].length;
            width = image.length;
        } else {
            width = image[0].length;
            length = image.length;
        }

        if (image[width / 2][length / 8] == 0) {
            return "3";
        } else if (image[width / 2][(length/3)] == 0) {
            return "2";
        } else {
            return "1";
        }

    }
}
