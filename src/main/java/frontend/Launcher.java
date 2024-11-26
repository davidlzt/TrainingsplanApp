package frontend;

import java.awt.*;

public class Launcher {
    public static void main(String[] args) {

        Thread appThread = new Thread(String.valueOf(new LandingPage(new Dimension(800, 600))));
        appThread.start();
    }
}
