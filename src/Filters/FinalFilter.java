package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class FinalFilter implements PixelFilter {
    ColorMaskingFilter colorMask1 = new ColorMaskingFilter();
    FloodFillFilter floodFilter = new FloodFillFilter();
    ColorMaskingFilter colorMask2 = new ColorMaskingFilter();
    private DImage prevImage;

    public FinalFilter() {

    }
    public DImage processImage(DImage img) {
        prevImage = img;
        img = colorMask1.processImage(img);
       // img = floodFilter.processImage(img);
        img = colorMask2.processImage(img, prevImage);
        return img;
    }
}
