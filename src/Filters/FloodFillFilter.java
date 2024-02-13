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
    private int startX, startY, imgH, imgW;
    private short seekValue, seekR, seekG, seekB;
    private short replaceValue, replaceR, replaceG, replaceB;
    private short[][] pixelsBW, channelR, channelG, channelB, resultBW;
    private ArrayList<Pixel> resultPixels;
    private int resultPixelsCount;
    private boolean BW;

    public FloodFillFilter(DImage img, int startX, int startY, short seekValue, short replaceValue){
        this.img = img;
        this.imgH = img.getHeight();
        this.imgW = img.getWidth();

        this.seekValue = seekValue;
        this.replaceValue = replaceValue;

        this.BW = true;

        this.startX = startX;
        this.startY = startY;

        this.pixelsBW = img.getBWPixelGrid();
        this.resultBW = this.pixelsBW;
        this.resultPixels = floodFillBW();
        this.resultPixelsCount = 0;
    }
    public FloodFillFilter(DImage img, int startX, int startY, ColorRGB seekColor, ColorRGB replaceColor){
        this.img = img;
        this.imgH = img.getHeight();
        this.imgW = img.getWidth();

        this.seekR = seekColor.getR();
        this.seekG = seekColor.getG();
        this.seekB = seekColor.getB();

        this.replaceR = replaceColor.getR();
        this.replaceG = replaceColor.getG();
        this.replaceB = replaceColor.getB();

        this.BW = false;

        this.startX = startX;
        this.startY = startY;

        this.channelR = img.getRedChannel();
        this.channelG = img.getGreenChannel();
        this.channelB = img.getBlueChannel();
    }
    public DImage processImage(DImage img) {

        // processImage() will most likely not be used. Use getBWResult() and getColorResult() instead.

        FixedThresholdFilter fixedThresholdFilter = new FixedThresholdFilter();
        DImage thresholdImg = fixedThresholdFilter.processImage(img);

        short[][] bwPixels = thresholdImg.getBWPixelGrid();


        return thresholdImg;
    }

    // takes given parameters, performs flood fill; returns list of Pixels
    private ArrayList<Pixel> floodFillBW(){
        LinkedList<Pixel> queue = new LinkedList<>();
        ArrayList<Pixel> result = new ArrayList<>();

        queue.addLast(new Pixel(startX, startY));
        Pixel n = queue.getFirst();
        if(validPixelBW(n)){
            resultBW[n.getY()][n.getX()] = replaceValue;
        } else {return result;}

        while(queue.size() > 0){
            //System.out.println("Queue, Result size: "+queue.size()+", "+result.size());
            // since we only add valid pixels to the queue, no need to check each pixel we get from queue
            n = queue.removeFirst();

            result.add(n);
            // add all valid adjacents to the queue, to be checked and added to result
            ArrayList<Pixel> validAdjacents = getValidAdjacentsBW(n);
            for(Pixel a : validAdjacents){
                queue.addLast(a);
                resultBW[a.getY()][a.getX()] = replaceValue;
            }

        }

        return result;
    }
    // applies result from floodFillBW to the DImage in processImage().
    public DImage getBWImage(){
        img.setPixels(resultBW);
        return img;
    }

    // returns the list of pixels (result) from floodFillBW().
    public ArrayList<Pixel> getBWResult(){
        return resultPixels;
    }

    private boolean inBounds(Pixel n){
        boolean outOfBounds = (n.getX() >= 0 && n.getX() < imgW && n.getY() >= 0 && n.getY() < imgH);
        //if(outOfBounds == false) System.out.println("Out of Bounds!");
        return outOfBounds;
    }

    private boolean validPixelBW(Pixel n){
        if(!inBounds(n)) return false;
        //System.out.println("Checking: "+n);
        short val = resultBW[n.getY()][n.getX()];
        return (val == seekValue);
    }

    private ArrayList<Pixel> getValidAdjacentsBW(Pixel n){
        ArrayList<Pixel> result = new ArrayList<>();
        Pixel n1 = new Pixel(n.getX()+1, n.getY());
        Pixel n2 = new Pixel(n.getX()-1, n.getY());
        Pixel n3 = new Pixel(n.getX(), n.getY()+1);
        Pixel n4 = new Pixel(n.getX(), n.getY()-1);
        if(validPixelBW(n1)) result.add(n1);
        if(validPixelBW(n2)) result.add(n2);
        if(validPixelBW(n3)) result.add(n3);
        if(validPixelBW(n4)) result.add(n4);
        return result;
    }
}
