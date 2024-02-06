package FloodFill;

public class BWPixel extends Pixel{
    private short value;
    public BWPixel(int x, int y, short value){
        super(x, y);
        this.value = value;
    }

    public short getValue() {
        return value;
    }
}
