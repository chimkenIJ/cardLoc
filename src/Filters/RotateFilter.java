package Filters;

import core.DImage;

public class RotateFilter {
    public RotateFilter()
    {

    }
    public DImage processImage(DImage img) {
        short[][] pixels = img.getBWPixelGrid();

        img.setPixels(pixels);
        return img;
    }
}
