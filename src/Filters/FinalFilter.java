package Filters;

import FloodFill.Pixel;
import Interfaces.PixelFilter;
import core.DImage;

import java.util.ArrayList;

public class FinalFilter implements PixelFilter {
    ColorMaskingFilter colorMask1 = new ColorMaskingFilter();
    ColorMaskingFilter colorMask2 = new ColorMaskingFilter();
    //ExtractNum num = new ExtractNum();
    ShapeDetectorFilter shapedetector = new ShapeDetectorFilter();
    EdgeDetectionFilter edgeDetection = new EdgeDetectionFilter();
    private DImage prevImage;
    private DImage prev2Image;
    private ArrayList<DImage> arr;
    private ArrayList<String> arr2;
    private String number;
    private boolean ranOnce = false;
    private DImage savedFloodFill;

    public FinalFilter() {

    }
    public DImage processImage(DImage img) {
        if(ranOnce) {return savedFloodFill;}
        ranOnce = true;

        prevImage = img;

        // Testing Flood Fill (Black and White)
        img = colorMask1.processImage(img);

        FloodFillFilter floodFill = new FloodFillFilter(img, 15, 15, (short)0, (short)128);
        img = floodFill.getBWImage();
        FloodFillFilter floodFill2 = new FloodFillFilter(img, 400, 450, (short)0, (short)200);
        img = floodFill2.getBWImage();
        FloodFillFilter floodFill3 = new FloodFillFilter(img, 130, 70, (short)255, (short)0);
        img = floodFill3.getBWImage();

        savedFloodFill = img;

       // img = colorMask1.processImage(img);
       /*for (int i = 0; i < 12; i++) {
       findimage()
            number = num.processImage(img);
            String answer = "Card: " + (i+1) + ", Number: " + num;
            arr2.add(answer);
        }*/

      //  img = colorMask2.processImage(img, prevImage);
       // System.out.println(shapedetector.getShape(img));
        //img = colorMask2.processImage(img, prevImage);

        return img;
    }
}
