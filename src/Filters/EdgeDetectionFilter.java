package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class EdgeDetectionFilter implements PixelFilter {
    private double[][] boxBlurKernel = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    private double[][] preWitt = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};
    private double[][] gaussian7x7 = {{0, 0, 0, 5, 0, 0, 0}, {0, 5, 18, 32, 18, 5, 0}, {0, 18, 64, 100, 64, 18, 0}, {5, 32, 100, 100, 100, 32, 5}, {0, 18, 64, 100, 64, 18, 0}, {0, 5, 18, 32, 18, 5, 0}, {0, 0, 0, 5, 0, 0, 0}};
    private double[][] currentKernel = gaussian7x7;
    private double[][] gx = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
    private double[][] gy = {{1, 2, 1}, {0,0,0}, {-1,-2,-1}};
    private double[][] t1 = {{0,0,0}, {999, 255, 999}, {255,255,255}};
    private double[][] t2 = {{999,0,0}, {255,255, 0}, {999,255,99}};

    public EdgeDetectionFilter() {

    }

    public DImage processImage(DImage img) {
       short[][] pixels = img.getBWPixelGrid();
        double output = 0;

        double outputX = 0;

        double outputY = 0;



        short[][] pixelImg = new short[pixels.length][pixels[0].length];

        int totalKWeightx = findWeight(gx);
        int totalKWeighty = findWeight(gy);

        for (int row = (currentKernel.length - 1) / 2; row < pixels.length - ((currentKernel.length - 1) / 2); row++) {
            for (int col = (currentKernel.length - 1) / 2; col < pixels[0].length - (currentKernel.length - 1) / 2; col++) {
                outputX = kernelWeight1(row, col, pixels, gx);
                outputY = kernelWeight1(row, col, pixels, gy);
                output = Math.sqrt(outputX * outputX + outputY * outputY);
                pixelImg[row][col] = (short) output;


            }
        }

       // pixelImg = threshold(pixelImg);


       /* for (int hi = 0; hi < 4; hi++) {
            for (int i = 1; i < pixelImg.length - 1; i++) {
                for (int j = 1; j < pixelImg[0].length - 1; j++) {
                    pixelImg = thinning(pixelImg, t1, i, j);
                    pixelImg = thinning(pixelImg, t2, i, j);
                }
            }
            t1 = rotateArr(t1);
            t2 = rotateArr(t2);
        }*/



        img.setPixels(pixelImg);
        return img;
    }

    private double[][] rotateArr(double[][] t1) {
        double[][] hi = new double[t1.length][t1[0].length];
        for (int i = 0; i < t1.length; i++) {
            for (int j = 0; j < t1[0].length; j++) {
                hi[t1[0].length-1-j][i] = t1[i][j];
            }
        }
        return hi;
    }

    private short[][] thinning(short[][] redImage, double[][] kernel, int i, int j) {
        short[][] thin = new short[redImage.length][redImage[0].length];
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l <3; l++) {
                if(kernel[k][l] == thin[i][j]) {
                    thin[i][j] = 1;
                }
                else {
                    thin[i][j] = 0;
                }
            }
        }
        return redImage;
    }

    private short[][] threshold(short[][] color) {
        short[][] thresh = new short[color.length][color[0].length];
        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j < color[0].length; j++) {
                if (color[i][j] < 127) {
                    thresh[i][j] = 0;
                } else {
                    thresh[i][j] = 255;
                }
            }
        }
        return thresh;
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
        for (int i = -(currentKernel.length - 1) / 2; i <= (currentKernel.length - 1) / 2; i++) {
            for (int j = -(currentKernel.length - 1) / 2; j <= (currentKernel.length - 1) / 2; j++) {
                output += (kernel[i + (currentKernel.length - 1) / 2][j + (currentKernel.length - 1) / 2] * color[row + i][col + j]);
            }

        }
        if (output < 0) {
            return 0;
        }
        return output / findWeight(kernel);
    }

    private double kernelWeight1(int row, int col, short[][] color, double[][] kernel) {
        double output = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                output += (kernel[i + 1][j + 1] * color[row + i][col + j]);
            }

        }
        if (output < 0) {
            return 0;
        }
        return output;
    }
}
