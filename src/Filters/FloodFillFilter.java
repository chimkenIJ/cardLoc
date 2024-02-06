package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class FloodFillFilter implements PixelFilter {
    public FloodFillFilter(){

    }
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();


        img.setColorChannels(red, green, blue);
        return img;
    }
}
