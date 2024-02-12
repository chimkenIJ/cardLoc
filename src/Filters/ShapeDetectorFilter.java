package Filters;

import Interfaces.PixelFilter;
import core.DImage;
import processing.core.PImage;
import processing.core.PApplet;


public class ShapeDetectorFilter implements PixelFilter {
    private int threshold = 20;
    public ShapeDetectorFilter() {

    }

   public DImage processImage(DImage img) {
return img;

    }

    public String getShape(DImage img) {
        String answer = "error";
        boolean first = true;
        short[][] pixels = img.getBWPixelGrid();
        for (int i = pixels.length/2; i < pixels.length-threshold; i++) {
            if(pixels[i][pixels[0].length-1] == 255) {
                if(first) {
                    answer = "squiggly";
                    break;
                }
                else {
                    answer = "diamond";
                }
            }
            first = false;
        }
        if(answer.equals("error")) {
        answer = "circle";}

        return answer;
    }


}
