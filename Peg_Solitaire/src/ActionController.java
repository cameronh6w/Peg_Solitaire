
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.Timer;


import javax.swing.JLabel;
import javax.swing.JTextField;

public class ActionController {
    
    
    public  static MyButton buttonAction( MyButton firstClicked, MyButton button, PegBoard boardState,  MyButton[][] buttonsMatrix, JLabel error_msg){
        
        
        //if it's the first button selcted, all it does is change the coloor
        if(firstClicked == null){
            button.firstClickButton();
            firstClicked = button;
            
        }
        //if its the second button selected, the buttons will check if it's a valid move 
        else{
            
            
            //this makes the move for the buttons on tthe screen
            button.secondClickButton(firstClicked, buttonsMatrix, error_msg);

            //this makes the move for the peg board to stay updated 
            int[] source = {firstClicked.y, firstClicked.x};
            int[] goal = {button.y, button.x};
            boardState.makeMove(source, goal);


           
            

            //randomly restgame with random?
            //if autoplay is not running, every time a abutton click happns, have a raandom chanceto reset ranadom board.
            //also needs to record moves 

            //resets the move
            firstClicked = null;
            
        }

        //updates for when  the game is over
        if(boardState.getGameOver()){
            error_msg.setText("No More Possible Moves! Game Over!");
        }
        return firstClicked;
    }

    

    public static void autoPlayAction( PegBoard boardState,  MyButton[][] buttonsMatrix,JLabel error_msg){
        PrintWriter moveWriter;
        try {
            moveWriter = new PrintWriter("automoves.txt");
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        Timer timer = new Timer(300, null);

        timer.addActionListener(ev -> {

            // get all possible moves
            ArrayList<ArrayList<Integer>> list = boardState.getAllPossibleMovesList();

            // stop if no moves left
            if (list.isEmpty()) {
                timer.stop();
                moveWriter.close();
                error_msg.setText("Game Over! Moves saved to file.");
                return;
            }

            // pick random move
            int moveIndex = (int) (Math.random() * list.size());
            ArrayList<Integer> move = list.get(moveIndex);

            int y1 = move.get(0);
            int x1 = move.get(1);
            int y2 = move.get(2);
            int x2 = move.get(3);

            // log move to file
            moveWriter.println("auto-(" + y1 + "," + x1 + ") -> (" + y2 + "," + x2 + ")");

            // perform move (simulate clicks)
            clickButton(y1, x1, boardState, buttonsMatrix);
            clickButton(y2, x2, boardState, buttonsMatrix);

        });

        timer.start();
    }


    public static  void clickButton(int y, int x, PegBoard board,  MyButton[][] buttonsMatrix){
        if(board.getBoard()[y][x] != -1){
            MyButton button = buttonsMatrix[y][x]; 
       
            button.getButton().doClick(); 
        }
    }

    public static int getSizeInput(JTextField textField, int board_size){
        int input = Integer.parseInt(textField.getText());
        if(input % 2 != 0 && input >=7 ){
            board_size = input;
        }
        return board_size;
    }
    


}
