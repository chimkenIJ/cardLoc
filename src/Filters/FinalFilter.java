package Filters;

import CardLoc.ExtractCard;
import CardLoc.FillResult;
import CardLoc.FloodFill;
import CardLoc.FloodFillTest;
import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

import java.util.ArrayList;


public class FinalFilter implements PixelFilter, Interactive {
    private int counter = 0;
    ColorMaskingFilter colorMask1 = new ColorMaskingFilter();
    EdgeDetectionFilter edgeDetection = new EdgeDetectionFilter();
    ShapeExtractorFilter shapeExtractor = new ShapeExtractorFilter();
    ExtractNum extractNum = new ExtractNum();
    ShapeDetectorFilter extractShape = new ShapeDetectorFilter();
    getColorFilter extractColor = new getColorFilter();
    getOpacityFilter extractOpacity = new getOpacityFilter();
    RotateFilter rotateFilter = new RotateFilter();
    private DImage prevImage;
    private boolean ranOnce = false;

    public FinalFilter() {}

    public DImage processImage(DImage img) {
       /* if(ranOnce) {
        return prevImage;
        }
        ranOnce = true;*/

        DImage origImg = img.copy();

        DImage maskedImg = colorMask1.processImage(img);

        FloodFillTest floodFillTest = new FloodFillTest(maskedImg, 12);

        ExtractCard extractor = new ExtractCard(origImg, floodFillTest.getCardFills());

        DImage[] cards = extractor.getCardImages();
       System.out.println("Detected "+cards.length+" cards!");
DImage[] car = new DImage[12];
        for (int i = 0; i < cards.length; i++) {
            DImage card = cards[i];
            DImage masked = colorMask1.processImage(card);

            DImage rotated = rotateFilter.processImage(masked);
            DImage edges = edgeDetection.processImage(rotated);

            DImage extracted = shapeExtractor.processImage(edges);
            car[i] = edges;

            String num = extractNum.getNumber(masked);
            String shape = extractShape.getShape(extracted);
            String color = extractColor.getColor(card);
            String opacity = extractOpacity.getOpacity(extracted);

            System.out.println("============= Card ("+i+"): =============");
            System.out.println("Number: "+num);
            System.out.println("Shape: "+shape);
            System.out.println("Color: "+color);
            System.out.println("Opacity: "+opacity);
           // cards[i] = extracted;

        }

        img = car[counter];
//
     prevImage = img;

        return img;
    }
    private void printArr(FillResult[] arr){
        System.out.println("Arr: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+", ");
        }
    }


    @Override
    public void mouseClicked(int mouseX, int mouseY, DImage img) {

    }

    @Override
    public void keyPressed(char key) {
        if(key == 'g' && counter<12) {
            System.out.println("incr");
            counter++;
        }
        if (key == 'a' && counter>0) {
            System.out.println("decr");
            counter--;;
        }

    }
}
