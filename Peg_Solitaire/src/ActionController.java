
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.Timer;


import javax.swing.JLabel;
import javax.swing.JTextField;

public class ActionController {
    //private  static PrintWriter moveWriter;

 

    private static void logText(PrintWriter moveWriter, String text) {
        if (moveWriter != null) {
            moveWriter.println(text);
            moveWriter.flush(); // ensures it's written immediately
        }
    }
    
    private static void logMove(PrintWriter moveWriter, int y1, int x1, int y2, int x2) {
      
        if (moveWriter != null) {
            moveWriter.printf("(%d,%d) -> (%d,%d)%n", y1, x1, y2, x2);
            
            moveWriter.flush(); // ensures it's written immediately
        }
    }
    
    public  static MyButton buttonAction( PrintWriter moveWriter, MyButton firstClicked, MyButton button, PegBoard boardState,  MyButton[][] buttonsMatrix, JLabel error_msg){
        
     

        //if it's the first button selcted, all it does is change the coloor
        if(firstClicked == null){
            button.firstClickButton();
            firstClicked = button;
         