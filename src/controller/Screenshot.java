package controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

public class Screenshot {

    int interval =0;
    int screenShots = 0;

    public Screenshot(String interval){

        switch (interval){
            case "5 sec":
                this.interval = 5;
                break;
            case "10 sec":
                this.interval = 10;
                break;
            case "30 sec":
                this.interval = 30;
                break;
            default:
                this.interval = 30;
                break;
        }

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                capture(screenShots);
                screenShots++;
                System.out.println("fired");
            }
        },100 * this.interval, 100 * this.interval);

    }

    public boolean capture(int i){

        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture;
        try {
            Files.createDirectories(Paths.get("screenshots"));
            capture = new Robot().createScreenCapture(screenRect);
            ImageIO.write(capture, "jpg", new File("screenshots/screenshot"+i+".jpg"));
        } catch (AWTException e1) {
            e1.printStackTrace();
            return false;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean capture(String i){

        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture;
        try {
            Files.createDirectories(Paths.get("screenshots"));
            capture = new Robot().createScreenCapture(screenRect);
            ImageIO.write(capture, "jpg", new File("screenshots/screenshot"+i+".jpg"));
        } catch (AWTException e1) {
            e1.printStackTrace();
            return false;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
