package Filters;

import CardLoc.ExtractCard;
import CardLoc.FillResult;
import CardLoc.FloodFill;
import CardLoc.FloodFillTest;
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
        DImage origImg = img.copy();
        DImage maskedImg = colorMask1.processImage(img);

        FloodFillTest floodFillTest = new FloodFillTest(maskedImg, 12);

        ExtractCard extractor = new ExtractCard(origImg, floodFillTest.getCardFills());
        DImage[] cards = extractor.getCardImages();
        System.out.println("Detected "+cards.length+" cards!");
        img = cards[11];
        for (int i = 0; i < cards.length; i++) {
            DImage card = cards[i];
            System.out.println("============= Card ("+i+"): ============= ");
            String shape = new ShapeDetectorFilter().getShape(card);
            System.out.println("Shape: "+shape);
        }
        //img = floodFillTest.getCardImage();
        //img = floodFillTest.getFinalImage();
        //FillResult[] sortedResults = floodFillTest.getSortedResults();
        //printArr(sortedResults);

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
    private void printArr(FillResult[] arr){
        System.out.println("Arr: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+", ");
        }
    }
}
