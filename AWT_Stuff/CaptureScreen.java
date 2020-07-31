package JavaProjects.AWT_Stuff;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class CaptureScreen {
    public static void main(String[] args) throws AWTException, IOException {
        Robot robot = new Robot();

        Rectangle rectangle = new Rectangle();
        rectangle.setRect(0, 0, 100, 100);

        BufferedImage image = robot.createScreenCapture(rectangle);

        // Creating PNG of image
        ImageIO.write(image, "png", new File("out.png"));
    }
}
