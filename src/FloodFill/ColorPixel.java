package FloodFill;

public class ColorPixel extends Pixel{
    private short r, g, b;
    public ColorPixel(int x, int y, short r, short g, short b){
        super(x, y);
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public short getR() {
        return r;
    }

    public short getG() {
        return g;
    }

    public short getB() {
        return b;
    }
}
