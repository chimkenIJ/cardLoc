package Filters;

import FloodFill.BWPixel;
import FloodFill.ColorRGB;
import FloodFill.Pixel;
import Interfaces.PixelFilter;
import core.DImage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class FloodFillFilter implements PixelFilter {
    private DImage img;
    private int startX, startY;
    private short seekValue, seekR, seekG, seekB;
    private short replaceValue, replaceR, replaceG, replaceB;
    private boolean BW;

    public FloodFillFilter(DImage img, int startX, int startY, short seekValue, short replaceValue){
        this.img = img;
        this.seekValue = seekValue;
        this.replaceValue = replaceValue;
        this.BW = true;
        this.startX = startX;
        this.startY = startY;
    }
    public FloodFillFilter(DImage img, int startX, int startY, ColorRGB seekColor, ColorRGB replaceColor){
        this.img = img;
        this.seekR = seekColor.getR();
        this.seekG = seekColor.getG();
        this.seekB = seekColor.getB();
        this.replaceR = replaceColor.getR();
        this.replaceG = replaceColor.getG();
        this.replaceB = replaceColor.getB();
        this.BW = false;
        this.startX = startX;
        this.startY = startY;
    }
    public DImage processImage(DImage img) {

        // processImage() will most likely not be used. Use getBWResult() and getColorResult() instead.

        FixedThresholdFilter fixedThresholdFilter = new FixedThresholdFilter();
        DImage thresholdImg = fixedThresholdFilter.processImage(img);

        short[][] bwPixels = thresholdImg.getBWPixelGrid();


        return thresholdImg;
    }

    // takes given parameters, performs flood fill; returns list of Pixels
    private ArrayList<Pixel> floodFillBW(short[][] pixels, int startX, int startY){
        LinkedList<Pixel> queue = new LinkedList<>();
        ArrayList<Pixel> result = new ArrayList<>();



        return result;
    }
    // applies result from floodFillBW to the DImage in processImage().
    private void applyBWResult(){}
    // returns the list of pixels (result) from floodFillBW().
//    public ArrayList<BWPixel> getBWResult(){
//        return floodFillBW()
//    }
}
