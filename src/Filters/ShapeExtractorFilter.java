package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class ShapeExtractorFilter implements PixelFilter {
    public ShapeExtractorFilter() {

    }

    public DImage processImage(DImage img) {
        short[][] pixels = img.getBWPixelGrid();
        int top = -1;
        int bottom = 0;
        int right = 0;
        int left = pixels[0].length;
        for (int i = 0; i < pixels.length; i++) {
            for (int j = pixels[0].length - 1; j >= 0; j--) {
                if (pixels[i][j] == 255) {
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
        System.out.println("TOP: " + top + ", BOTTOM: " + bottom + ", LEFT: " + left + ", RIGHT: " + right);

        short[][] newImg = new short[bottom - top + 1][right - left + 1];
        for (int i = top; i <= bottom; i++) {
            for (int j = left; j <= right; j++) {
                newImg[i - top][j - left] = pixels[i][j];
            }
        }
        img.setPixels(newImg);
        return img;

    }
}
