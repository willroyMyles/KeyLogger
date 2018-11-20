package controller;

import frame.Display;

public class Controller {


    private KeyLogger keyLogger;
    private Display display;

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

    public void hideDisplay(){
        display.setVisible(false);
    }

    public void showDisplay(){
        display.setVisible(true);
    }


}
