import controller.Controller;
import frame.Display;

public class Driver {

    public static void main(String[] args){
        Controller con = new Controller();
        Display dis = new Display();
        con.setDisplay(dis);
    }
}
