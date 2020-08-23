package JavaProjects.AWT_Projects;

import org.w3c.dom.events.MouseEvent;

import java.awt.*;
import java.io.IOException;

public class CaptureScreen {
    public static int screenWidth = 1920, screenHeight = 1080;

    public static void main(String[] args) throws AWTException, IOException, InterruptedException {
        PointerInfo pointer;
        while(true){
            pointer = MouseInfo.getPointerInfo();

            System.out.println(pointer.getLocation().x + " " + pointer.getLocation().y);
            Thread.sleep(250);
        }

//        Thread.sleep(2000);
//
//        Robot robot = new Robot();
//
//        Rectangle rectangle = new Rectangle();
//        rectangle.setRect(0, 0, 100, 100);
//
//        BufferedImage image = robot.createScreenCapture(rectangle);
//
//        System.out.println(Color.BLACK.getRGB());
//
//        // Creating PNG of image
//        ImageIO.write(image, "png", new File("out.png"));
//
//        // Pressing Mouse Buttons
//        // 1 is right click; 3 is left click (2 is middle i think)
//        Thread.sleep(1000);
//        robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
//        Thread.sleep(1000);
//        robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
    }
}
