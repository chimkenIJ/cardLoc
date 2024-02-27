package Filters;

import Interfaces.PixelFilter;
import core.DImage;


public class ShapeDetectorFilter implements PixelFilter {
    private int threshold = 20;
    private int counter = 0;

    public ShapeDetectorFilter() {

    }

    @Override
    public DImage processImage(DImage img) {
        return img;
    }

    public String getShape(DImage img, int num) {
        counter = 0;
        if (num != 1) {
            img = divide(img, num);
        }
        short[][] pixels = img.getBWPixelGrid();

        double length = pixels.length * 0.75;
        double width = pixels[0].length * 0.75;
        for (int i = (int) length; i < pixels.length; i++) {
            for (int j = (int) width; j < pixels[0].length; j++) {
                if (pixels[i][j] == 255) {
                    counter++;
                }
            }
        }
        if ((counter + 5 >= (pixels.length * pixels[0].length / 16))) {
            //  System.out.println(counter + ", " + pixels.length * pixels[0].length / 16);
            return "diamond";
        } else {
            counter = 0;
            for (int i = 0; i < pixels.length; i++) {
                if (pixels[i][pixels[0].length - threshold] == 0) {
                    counter++;
                }
            }
            if (counter + 5 >= pixels.length) {
                return "circle";
            } else {
                return "squiggly";
            }
        }
    }

    private DImage divide(DImage tempImg, int num) {
        short[][] pixels = tempImg.getBWPixelGrid();
        short[][] newPix = new short[pixels.length][pixels[0].length / num];

        for (int i = 0; i < newPix.length; i++) {
            for (int j = 0; j < newPix[0].length; j++) {
                newPix[i][j] = pixels[i][(pixels[0].length - pixels[0].length / num)+j];
            }
        }
        tempImg.setPixels(newPix);
        return tempImg;

    }
}
