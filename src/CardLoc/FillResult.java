package CardLoc;

import java.util.ArrayList;
import FloodFill.Pixel;
import core.DImage;

public class FillResult {
    private ArrayList<Pixel> pixelsList;
    private int startX, startY;
    private short seekVal, replaceVal;
    private short[][] resultPixels;
    private DImage resultImg;
    public FillResult(DImage resultImg, short[][] resultPixels, ArrayList<Pixel> pixelsList, int startX, int startY, short seekVal, short replaceVal){
        this.resultImg = resultImg;
        this.resultPixels = resultPixels;
        this.pixelsList = pixelsList;
        this.startX = startX;
        this.startY = startY;
        this.seekVal = seekVal;
        this.replaceVal = replaceVal;
    }
    public int getFillSize(){
        return getPixelsList().size();
    }

    public DImage getResultImg() {
        return resultImg;
    }

    public short[][] getResultPixels() {
        return resultPixels;
    }

    public ArrayList<Pixel> getPixelsList() {
        return pixelsList;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public short getSeekVal() {
        return seekVal;
    }

    public short getReplaceVal() {
        return replaceVal;
    }
    public String toString(){
        return "R: "+getSeekVal();
    }
}
