package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class EdgeDetectionFilter implements PixelFilter {
    private double[][] preWitt = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};

    public EdgeDetectionFilter() {

    }

    public DImage processImage(DImage img) {
        DImage tempImg = img.copy();
        short[][] pixels = tempImg.getBWPixelGrid();
        double output = 0;


        short[][] pixelImg = new short[pixels.length][pixels[0].length];


        for (int row = (preWitt.length - 1) / 2; row < pixels.length - ((preWitt.length - 1) / 2); row++) {
            for (int col = (preWitt.length - 1) / 2; col < pixels[0].length - (preWitt.length - 1) / 2; col++) {

                output = kernelWeight(row, col, pixels, preWitt);
                pixelImg[row][col] = (short) output;


            }
        }

        tempImg.setPixels(pixelImg);
        return tempImg;
    }


    private int findWeight(double[][] kernel) {
        int weight = 0;
        for (int i = 0; i < kernel.length; i++) {
            for (int j = 0; j < kernel[0].length; j++) {
                weight += kernel[i][j];
            }
        }
        if (weight == 0) {
            return 1;
        }
        return weight;
    }

    private double kernelWeight(int row, int col, short[][] color, double[][] kernel) {
        double output = 0;
        for (int i = -(kernel.length - 1) / 2; i <= (kernel.length - 1) / 2; i++) {
            for (int j = -(preWitt.length - 1) / 2; j <= (kernel.length - 1) / 2; j++) {
                output += (kernel[i + (kernel.length - 1) / 2][j + (kernel.length - 1) / 2] * color[row + i][col + j]);
            }

        }
        if (output < 0) {
            return 0;
        }
        return output / findWeight(kernel);
    }

}
