package Filters;

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

    public FinalFilter() {

    }
    public DImage processImage(DImage img) {
        //prevImage = img;
        img = colorMask1.processImage(img);
        img = edgeDetection.processImage(img);
        //FloodFillFilter floodFilter = new FloodFillFilter();
//        prev2Image = img;
//        img = colorMask2.processImage(img, prevImage);
//

       /*for (int i = 0; i < 12; i++) {
       findimage()
            number = num.processImage(img);
            String answer = "Card: " + (i+1) + ", Number: " + num;
            arr2.add(answer);
        }*/

      //  img = colorMask2.processImage(img, prevImage);
       // System.out.println(shapedetector.getShape(img));

        return img;
    }
}
