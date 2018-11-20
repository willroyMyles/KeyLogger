package controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Screenshot {

    public Screenshot(){

    }

    public boolean capture(int i){

        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture;
        try {
            Files.createDirectories(Paths.get("screenshots"));
            capture = new Robot().createScreenCapture(screenRect);
            ImageIO.write(capture, "jpg", new File("screenshots\\sreenshot"+i+".jpg"));
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
