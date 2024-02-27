package Filters;

import CardLoc.ExtractCard;
import CardLoc.FillResult;
import CardLoc.FloodFill;
import CardLoc.FloodFillTest;
import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    private DImage prevImage;
    private boolean ranOnce = false;
    private String cardInfo;

    public FinalFilter() {
    }

    public DImage processImage(DImage img) throws IOException{
        if (ranOnce) {
            writeDataToFile("cardsInfo.txt", cardInfo);
            return prevImage;
        }
        ranOnce = true;

        DImage origImg = img.copy();

        DImage maskedImg = colorMask1.processImage(img);

        FloodFillTest floodFillTest = new FloodFillTest(maskedImg, 12);

        ExtractCard extractor = new ExtractCard(origImg, floodFillTest.getCardFills());

        DImage[] cards = extractor.getCardImages();
        String detection = "Detected " + cards.length + " cards!";
        cardInfo = detection + "\n\n\n";
        System.out.println(detection);
        DImage[] car = new DImage[12];
        for (int i = 0; i < cards.length; i++) {
            DImage card = cards[i];
            DImage masked = colorMask1.processImage(card);

            DImage extracted = shapeExtractor.processImage(masked);
            car[i] = extracted;

            String num = extractNum.getNumber(masked);
            String shape = extractShape.getShape(extracted, Integer.parseInt(num));
            String color = extractColor.getColor(card);
            String opacity = extractOpacity.getOpacity(extracted);
            String cardNum = "============= Card (" + i + "): =============";
            String numberAdd = "Number: " + num;
            String shapeAdd = "Shape: " + shape;
            String colorAdd = "Color: " + color;
            String opacityAdd = "Opacity: " + opacity;
            System.out.println(cardNum);
            System.out.println(numberAdd);
            System.out.println(shapeAdd);
            System.out.println(colorAdd);
            System.out.println(opacityAdd);
            cardInfo = cardInfo + "\n\n" + cardNum + "\n" +numberAdd+"\n" +shapeAdd+ "\n" +colorAdd + "\n" +opacityAdd + "\n\n";
            cards[i] = extracted;
        }

        img = car[counter];

        prevImage = img;



        return img;
    }

    public static void writeDataToFile(String filePath, String data) throws IOException {
        try (FileWriter f = new FileWriter(filePath);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter writer = new PrintWriter(b);) {


            writer.println(data);


        } catch (IOException error) {
            System.err.println("There was a problem writing to the file: " + filePath);
            error.printStackTrace();
        }
    }


    private void printArr(FillResult[] arr) {
        System.out.println("Arr: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }


    @Override
    public void mouseClicked(int mouseX, int mouseY, DImage img) {

    }

    @Override
    public void keyPressed(char key) {
        if (key == 'g' && counter < 12) {
            System.out.println("incr");
            counter++;
        }
        if (key == 'a' && counter > 0) {
            System.out.println("decr");
            counter--;
            ;
        }

    }
}
