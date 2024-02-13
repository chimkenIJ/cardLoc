package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class ExtractNum implements PixelFilter {
    @Override
    public DImage processImage(DImage img) {
        return null;
    }


//    @Override
//    public String processImage(DImage img) {
//        int length, width;
//        short[][] image = img.getBWPixelGrid();
//
//        if (image[0].length > image.length) {
//            length = image[0].length;
//            width = image.length;
//        } else {
//            width = image[0].length;
//            length = image.length;
//        }
//
//        if (image[width / 2][length / 8] == 255) {
//            return "3";
//        } else if (image[width / 2][(2 * length / 5)] == 255) {
//            return "2";
//        } else {
//            return "1";
//        }
//
//
//        return "error";
//    }
}

