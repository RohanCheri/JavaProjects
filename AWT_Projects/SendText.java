package JavaProjects.AWT_Projects;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

/**
 * This program takes any string and types it out
 * a specified amount of times.
 *
 * @author Rohan
 * @version 9/26/2020
 */
public class SendText {
    // Variables that index letters and symbols
    public static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static String symbols = ",-./0123456789;=";
    public static String shiftSymbols = "<_>?)!@#$%^&*(:+";

    // Object for accessing robot methods
    public static Robot robot;

    // Variables to change to impact program

    // Delay until the program starts
    public static int startDelay = 2500;

    // Delay between different messages
    public static int waitTime = 200;

    // Text to send
    public static String sendString = "I'm not a perv.";

    // How many times the text repeats (Use 0 for infinite)
    public static int repeats = 5;

    public static void main(String[] args) throws AWTException, InterruptedException {
        // Instantiates robot object
        robot = new Robot();

        // Runs startup delay
        Thread.sleep(startDelay);

        // Main repeat loop
        while(repeats --> 0){
            // Type letter by letter
            for(int i = 0; i < sendString.length(); i++){
                type(sendString.substring(i, i + 1));
            }

            // Hits enter to send text
            click(KeyEvent.VK_ENTER);

            // Wait for idle processes to complete
            robot.waitForIdle();
            Thread.sleep(waitTime);
        }
    }

    // Takes in a letter and types it out to the robot
    public static void type(String letter){
        // Convert letter to index
        int ind = 65 + alphabet.indexOf(letter.toLowerCase());
        boolean useShift = false;

        // Check if its uppercase
        if(!letter.toLowerCase().equals(letter)){
            useShift = true;
        }

        // Check for specific symbols
        if(letter.equals(" ")){
            click(KeyEvent.VK_SPACE);
            robot.waitForIdle();
        }
        else if(letter.equals("'")){
            click(KeyEvent.VK_QUOTE);
        }
        else if(letter.equals("\"")){
            shiftClick(KeyEvent.VK_QUOTE);
        }
        else{
            // Check for other symbols
            if(symbols.contains(letter)){
                ind = 44 + symbols.indexOf(letter);
                useShift = false;
            }
            else if(shiftSymbols.contains(letter)){
                ind = 44 + shiftSymbols.indexOf(letter);
                useShift = true;
            }

            // Type out the key
            if(useShift){
                shiftClick(ind);
            }
            else{
                click(ind);
            }
        }
    }

    // Simulates singular key press
    public static void click(int ind){
        robot.keyPress(ind);
        robot.keyRelease(ind);
    }

    // Simulates shift key press
    public static void shiftClick(int ind){
        robot.keyPress(KeyEvent.VK_SHIFT);
        click(ind);
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }
}
