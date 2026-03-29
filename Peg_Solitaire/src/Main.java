//import PegBoard.Type;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {
        new BoardGUI(7,PegBoard.Type.ENGLISH);
        
        
        /*b.boardState.getAllPossibleMoves();

    
        System.out.println("test");
        ArrayList<ArrayList<Integer>> list = b.boardState.getAllPossibleMovesList();
        
        int move;
        while(list.size()>0){
            move = (int) (Math.random()* (list.size()));
            clickButton(list.get(move).get(0),list.get(move).get(1),b);
            clickButton(list.get(move).get(2),list.get(move).get(3),b);
            list = b.boardState.getAllPossibleMovesList();
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                e.printStackTrace(); 
            }

        }*/


    }
/* 
    public static  void clickButton(int y, int x, BoardGUI b){
        if(b.boardState.getBoard()[y][x] != -1){
            MyButton button = b.buttonsMatrix[y][x]; 
       
            button.getButton().doClick(); 
        }
    }
*/}