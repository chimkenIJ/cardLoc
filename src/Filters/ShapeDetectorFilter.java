package Filters;

import Interfaces.PixelFilter;
import core.DImage;


public class ShapeDetectorFilter implements PixelFilter{
    private int threshold = 20;
    private int counter = 0;
    public ShapeDetectorFilter() {

    }

    @Override
    public DImage processImage(DImage img) {
        System.out.println("hello");
        return img;
    }

    public String getShape(DImage img) {
        String answer = "error";
        boolean first = true;
        short[][] pixels = img.getBWPixelGrid();
        for (int i = pixels.length/2; i < pixels.length-threshold; i++) {
            if(pixels[i][pixels[0].length-1] == 0) {
                if(first && counter<pixels.length/4) {
                    answer = "squiggly";
                    break;
                }
                else {
                    answer = "diamond";
                }
                counter++;
            }
            first = false;
        }
        if(answer.equals("error")) {
        answer = "circle";}

        return answer;
    }


}
