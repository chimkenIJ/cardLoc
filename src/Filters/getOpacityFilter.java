package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class getOpacityFilter implements PixelFilter {
    public DImage processImage(DImage img) {
        return img;
    }

    public String getOpacity(DImage img) {
        int add = 0;
        short[][] pixels = img.getBWPixelGrid();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                if (pixels[i][j] == 0) {
                    add++;
                }
            }
        }
        System.out.println(add);
        if (add + 500 >= pixels.length * pixels[0].length) {
            return "fully shaded";
        } else if (add - 100 <= 0) {
            return "transparent";
        } else {
            return "half shaded";
        }
    }


}
