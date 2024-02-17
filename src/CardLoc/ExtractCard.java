package CardLoc;

import core.DImage;

import java.util.ArrayList;
import FloodFill.Pixel;

public class ExtractCard {
    DImage origImg;
    short[][] channelR, channelG, channelB;
    FillResult[] cardFills;
    public ExtractCard(DImage origImg, FillResult[] cardFills){
        this.origImg = origImg;
        this.channelR = origImg.getRedChannel();
        this.channelG = origImg.getGreenChannel();
        this.channelB = origImg.getBlueChannel();
        this.cardFills = cardFills;
    }
    public DImage[] getCardImages(){
        DImage[] imgs = new DImage[cardFills.length];
        for (int i = 0; i < cardFills.length; i++) {
            imgs[i] = cardFromFill(cardFills[i]);
        }
        return imgs;
    }
    private DImage cardFromFill(FillResult fill){
        ArrayList<Pixel> pixels = fill.getPixelsList();
        int left = getLeftBound(pixels);
        int right = getRightBound(pixels);
        int up = getUpperBound(pixels);
        int down = getLowerBound(pixels);
        int height = down-up;
        int width = right-left;
        short[][] red = new short[height][width];
        short[][] green = new short[height][width];
        short[][] blue = new short[height][width];
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                red[r][c] = channelR[up+r][left+c];
                green[r][c] = channelG[up+r][left+c];
                blue[r][c] = channelB[up+r][left+c];
            }
        }
        DImage card = new DImage(width, height);
        card.setRedChannel(red);
        card.setGreenChannel(green);
        card.setBlueChannel(blue);
        return card;
    }
    private int getLeftBound(ArrayList<Pixel> pixels){
        int left = pixels.get(0).getX();
        for (int i = 1; i < pixels.size(); i++) {
            int pos = pixels.get(i).getX();
            if(pos < left) left = pos;
        }
        return left;
    }
    private int getRightBound(ArrayList<Pixel> pixels){
        int right = pixels.get(0).getX();
        for (int i = 1; i < pixels.size(); i++) {
            int pos = pixels.get(i).getX();
            if(pos > right) right = pos;
        }
        return right;
    }
    private int getUpperBound(ArrayList<Pixel> pixels){
        int up = pixels.get(0).getY();
        for (int i = 1; i < pixels.size(); i++) {
            int pos = pixels.get(i).getY();
            if(pos < up) up = pos;
        }
        return up;
    }
    private int getLowerBound(ArrayList<Pixel> pixels){
        int down = pixels.get(0).getY();
        for (int i = 1; i < pixels.size(); i++) {
            int pos = pixels.get(i).getY();
            if(pos > down) down = pos;
        }
        return down;
    }
}
