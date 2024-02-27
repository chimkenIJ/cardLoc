package Filters;

import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

public class ShapeExtractorFilter implements PixelFilter, Interactive {
    private int threshold = 20;
    public ShapeExtractorFilter() {

    }

    public DImage processImage(DImage img) {
        DImage tempImg = img.copy();
        tempImg = crop(tempImg);
        short[][] pixels = tempImg.getBWPixelGrid();
        int top = -1;
        int bottom = 0;
        int right = 0;
        int left = pixels[0].length;
        for (int i = 0; i < pixels.length; i++) {
            for (int j = pixels[0].length - 1; j >= 0; j--) {
                if (pixels[i][j] == 0) {
                    if (top == -1) {
                        top = i;
                    }
                    if (i > bottom) {
                        bottom = i;
                    }
                    if (j > right) {
                        right = j;
                    }
                    if (j < left) {
                        left = j;
                    }
                }
            }
        }
      // System.out.println("TOP: " + top + ", BOTTOM: " + bottom + ", LEFT: " + left + ", RIGHT: " + right);

        short[][] newImg = new short[bottom - top + 1][right - left + 1];
        for (int i = top; i <= bottom; i++) {
            for (int j = left; j <= right; j++) {
                newImg[i - top][j - left] = pixels[i][j];
            }
        }
        tempImg.setPixels(newImg);
        return tempImg;

    }

    private DImage crop(DImage tempImg) {
        short[][] pixels = tempImg.getBWPixelGrid();
        short[][] cropPix = new short[pixels.length-(2*threshold)][pixels[0].length-(2*threshold)];
        for (int i = 0; i < cropPix.length; i++) {
            for (int j = 0; j < cropPix[0].length; j++) {
                cropPix[i][j] = pixels[i+threshold][j+threshold];
            }
        }
        tempImg.setPixels(cropPix);
        return tempImg;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, DImage img) {

    }

    @Override
    public void keyPressed(char key) {
        if(key == 'l') {
            threshold+=5;
        }
        if (key == 'k' && threshold>=5) {
            threshold-=5;
        }
    }
}
