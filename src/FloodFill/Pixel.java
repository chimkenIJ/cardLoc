package FloodFill;

public class Pixel {
    private int x, y;
    public Pixel(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString(){
        return "Pixel: ("+this.x+", "+this.y+")";
    }
}
