package CardLoc;

import FloodFill.Pixel;
import core.DImage;

import java.util.ArrayList;
import java.util.LinkedList;

public class FloodFillTest {
    private static int gridSizeX = 15;
    private static int gridSizeY = 10;
    private static int totalFills = gridSizeX * gridSizeY;
    private int expectedCards;
    private ArrayList<FillResult> resultFills = new ArrayList<>();
    private FillResult[] sortedResults;
    private FillResult[] cardFills;
    private DImage img;
    private short[][] pixelsBW, resultBW;
    public FloodFillTest(DImage img, int expectedCards){
        this.img = img;
        this.expectedCards = expectedCards;
        this.pixelsBW = img.getBWPixelGrid();
        this.resultBW = pixelsBW;
        runTestGrid();
        sortedResults = getSortedResults();
        cardFills = getCardFills();
    }
    public FillResult[] getSortedResults(){
        if(sortedResults != null) return sortedResults;
        LinkedList<FillResult> sortedList = new LinkedList<>();
        for (int i = 0; i < resultFills.size(); i++) {
            FillResult temp = resultFills.get(i);
            boolean foundPlace = false;
            for (int j = 0; j < sortedList.size(); j++) {
                FillResult comp = sortedList.get(j);
                if(temp.getFillSize() > comp.getFillSize()){
                    sortedList.add(j, temp);
                    foundPlace = true;
                    break;
                } // need to add temp to sortedList if list is empty or if it wasn't bigger than any comp
            }
            if(foundPlace == false) sortedList.addLast(temp);
        }
        sortedResults = sortedListToArr(sortedList);
        return sortedResults;
    }

    public FillResult[] getCardFills(){
        if(cardFills != null) return cardFills;
        //System.out.println("Running GetCardFills()");
        int countWhite = 0;
        FillResult[] results = new FillResult[expectedCards];
        for (int i = 0; i < sortedResults.length; i++) {
            if(countWhite >= expectedCards) break;
            FillResult r = sortedResults[i];
            if(r.getSeekVal() == 255){
                results[countWhite] = r;
                countWhite++;
            }
        }
        cardFills = results;
        //System.out.println("Count White: "+countWhite);
        return cardFills;
    }

    public DImage getCardImage(){
        DImage finalImg = img;
        short[][] finalPixels = new short[img.getHeight()][img.getWidth()];
        //finalPixels = addToPixels(finalPixels, sortedResults[0].getPixelsList(), (short)50);
        for (int i = 0; i < cardFills.length; i++) {
            finalPixels = addToPixels(finalPixels, cardFills[i].getPixelsList(), (short)(100+(i*10)));
        }
        finalImg.setPixels(finalPixels);
        return finalImg;
    }
    public DImage getFinalImage(){
        DImage finalImg = img;
        finalImg.setPixels(resultBW);
        return finalImg;
    }
    private void runTestGrid(){
        //System.out.println("Running Test Grid");
        // fills the resultFills array
        int deltaX = img.getWidth()/gridSizeX;
        int deltaY = img.getHeight()/gridSizeY;
        for (int gridX = 0; gridX < gridSizeX; gridX++) {
            for (int gridY = 0; gridY < gridSizeY; gridY++) {

                int fillTestCount = (gridX*gridSizeY)+gridY;
                //System.out.println("New fill test: "+gridX+", "+gridY+" (count "+fillTestCount+")");
                int startX = deltaX * gridX + deltaX/2;
                int startY = deltaY * gridY + deltaY/2;
                //System.out.println("Start X, Y: "+startX+", "+startY);
                if(isNewFill(startX, startY)) resultFills.add(runFillTest(startX, startY, (short)(fillTestCount*255/totalFills)));
            }
        }
        System.out.println("DONE RUNNING TEST GRID!");
    }

    private FillResult runFillTest(int startX, int startY, short finalReplaceVal){
        short seekVal = pixelsBW[startY][startX];
        short replaceVal = getReplaceVal(startX, startY);
        //System.out.println("Running FloodFill(img, "+startX+", "+startY+", "+seekVal+", "+replaceVal);
        FloodFill fill = new FloodFill(img, startX, startY, seekVal, replaceVal);
        FillResult fillResult = fill.getBWResult();
        //resultBW = addToPixels(resultBW, fillResult.getPixelsList(), finalReplaceVal);
        return fillResult;
    }

    private short[][] addToPixels(short[][] pixelGrid, ArrayList<Pixel> pixels, short replaceVal){
        short[][] newPixels = pixelGrid;
        for (int i = 0; i < pixels.size(); i++) {
            Pixel n =  pixels.get(i);
            newPixels[n.getY()][n.getX()] = replaceVal;
        }
        return newPixels;
    }
    private short getReplaceVal(int startX, int startY){
        short pixelVal = pixelsBW[startY][startX];
        short mag = (short)((pixelVal/2)*2-127);
        short flipped = (short)(127-mag);
        return flipped;
    }
    private boolean isNewFill(int startX, int startY){
        boolean isNew = true;
        for (int i = 0; i < resultFills.size(); i++) {
            FillResult result = resultFills.get(i);
            ArrayList<Pixel> pixels = result.getPixelsList();
            for (int j = 0; j < pixels.size(); j++) {
                Pixel p = pixels.get(j);
                if(p.getX() == startX && p.getY() == startY){
                    isNew = false;
                    return false;
                    // if isNew is false, break out of both loops and return false
                }
            }
        }
        return isNew;
    }
    private FillResult[] sortedListToArr(LinkedList<FillResult> list){
        FillResult[] arr = new FillResult[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
