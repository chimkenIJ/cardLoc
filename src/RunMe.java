import core.DisplayWindow;

public class RunMe {
    public static void main(String[] args) {
        // --== Load an image to filter ==--
       //DisplayWindow.showFor("images/imgDetection/TEST6.png", 800, 600, "DoNothingFilter");
      // DisplayWindow.showFor("images/set.jpg", 800, 600, "DoNothingFilter");
     //   DisplayWindow.showFor("images/imgDetection/TEST7.png", 800, 600, "DoNothingFilter");

        // --== Determine your input interactively with menus ==--
     DisplayWindow.getInputInteractively(800,600);
    }
}
