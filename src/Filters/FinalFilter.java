package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import java.util.ArrayList;

public class FinalFilter implements PixelFilter {
    ColorMaskingFilter colorMask1 = new ColorMaskingFilter();
    FloodFillFilter floodFilter = new FloodFillFilter();
    ColorMaskingFilter colorMask2 = new ColorMaskingFilter();
    ExtractNum num = new ExtractNum();
    private DImage prevImage;
    private DImage prev2Image;
    private ArrayList<DImage> arr;
    private ArrayList<String> arr2;
    private String number;

    public FinalFilter() {

    }
    public DImage processImage(DImage img) {
        prevImage = img;
       // img = colorMask1.processImage(img);
       /*for (int i = 0; i < 12; i++) {
            img = floodFilter.processImage(img);
            prev2Image = img;
            img = colorMask2.processImage(img, prevImage);
            number = num.processImage(img);
            String answer = "Card: " + (i+1) + ", Number: " + num;
            arr2.add(answer);
        }*/

        img = colorMask2.processImage(img, prevImage);

        return img;
    }
}
