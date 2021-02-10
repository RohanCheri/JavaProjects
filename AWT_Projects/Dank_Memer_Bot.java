package JavaProjects.AWT_Projects;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.io.*;

/**
 * This program outputs command for the dank memer bot
 *
 * @author Rohan
 * @version 2/10/2021
 */
public class Dank_Memer_Bot {
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
    public static int waitTime = 45000;

    // Text to send
    public static String[] commands = {"pls_beg", "pls_highlow L", "pls_fish", "pls_postmemes c"};
    public static int[] times = {46000, 21500, 61000, 61000};
    public static int[] cTimes = {0, 0, 0, 0};

    public static void main(String[] args) throws AWTException, InterruptedException {
        // Instantiates robot object
        robot = new Robot();

        // Runs startup delay
        Thread.sleep(startDelay);

        long lTime = System.currentTimeMillis(), cTime, aTime;
        // Main repeat loop
        while(true){
            cTime = System.currentTimeMillis();
            aTime = cTime - lTime;
            lTime = cTime;

            for(int i = 0; i < cTimes.length; i++){
                cTimes[i] += aTime;

                if(cTimes[i] > times[i]){
                    cTimes[i] = 0;
                    String[] cmds = commands[i].split(" ");

                    for(String c : cmds){
                        typeText(c);
                        Thread.sleep(1000);
                    }
                }
            }
        }
    }

    public static void typeText(String str){
        // Type letter by letter
        for(int i = 0; i < str.length(); i++){
            type(str.substring(i, i + 1));
        }

        // Hits enter to send text
        click(KeyEvent.VK_ENTER);

        // Wait for idle processes to complete
        robot.waitForIdle();
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
        if(letter.equals(" ") || letter.equals("_")){
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
