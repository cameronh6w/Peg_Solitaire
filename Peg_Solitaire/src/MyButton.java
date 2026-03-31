
import java.awt.Color;
import javax.swing.*;

public class MyButton {
    int x;
    int y;
    int state;
    JButton button;


    //constructor 
    //PRE:  _state can only be  1 or 0
    //      _x and _y must be withing the board size
    //POST: creattes a button that also keeps track  of its locaiton and current state (empty=0 or filled=1)
    public MyButton(JButton _button, int _state, int _x, int _y){
        button = _button;
        setState(_state);
        button.setOpaque(true);
        button.setBackground(Color.WHITE);
        
        x = _x;
        y = _y;

    }

    //PRE: _state can only be  1 or 0
    //POST: reset button to the inputed state
    public void resetButton(int _state){
        setState(_state);
        button.setOpaque(true);
        button.setBackground(Color.WHITE);
    }

    //getters and setters
    public int getX(){return x;}
    public int getY(){ return y;}
    public int getState() {return state;}
    public JButton getButton() {return button; }
    
    public void setState(int _state){
        state = _state;
        button.setText(_state == 1 ? "P" : "O");
    }

    //POST: changes backgound of button to  blue to indicate it's been selectetd
    public void firstClickButton(){
        button.setBackground(Color.BLUE);
    }

    //cratted to reduce repeteativeness 
    public void deselectButtons(String text, MyButton lastClickedButton, JLabel error){
        error.setText(text);

        button.setBackground(Color.WHITE);
        lastClickedButton.getButton().setBackground(Color.WHITE);
    }

    //PRE:  lastClickedButton must be the source pin for the attempted move
    //POST: if the move from lastClickedButton to the current button is valid, update buttons
    public void secondClickButton(MyButton lastClickedButton, MyButton[][] buttonsMatrix, JLabel error){
        //check if source is valid
        if(lastClickedButton.getState() != 1){
            deselectButtons("Invalid Source", lastClickedButton, error);
            return;
        }

        //check if goal is valid
        if(state != 0){
            deselectButtons("Invalid Goal", lastClickedButton, error);
            return;
        }

        int y_dif= y-lastClickedButton.getY();
        int x_dif = x-lastClickedButton.getX();
        
        //check if there is a pin between goal and source
        if(Math.abs(y_dif+x_dif) != 2){
            deselectButtons("Invalid Move", lastClickedButton, error);
            return;
        }

        int[] middle = findMiddleLocation( y_dif,  x_dif, lastClickedButton);

        //check  if the middle  pin  is valid
        if(buttonsMatrix[middle[0]][middle[1]].getState() == 0){
            deselectButtons("Invalid Middle Pin", lastClickedButton, error);
            return;
        }

        //if all is valid deslect with no error  message
        deselectButtons(" ", lastClickedButton, error);

        //update all buttons  
        buttonsMatrix[middle[0]][middle[1]].setState(0);
        lastClickedButton.setState(0);
        setState(1);
        
    }

    //uses same calcualtion for finding middle as peg board does
    public int[] findMiddleLocation(int y_dif, int x_dif, MyButton source){
        //find middle pin location
        int[] middle = {-1,-1};

        if(y_dif == 2){
            middle[0] = source.getY()+1;
            middle[1] = source.getX();
        }
        else if(y_dif == -2){
            middle[0] = source.getY()-1;
            middle[1] = source.getX();
        }
        else if(x_dif == 2){
            middle[0] = source.getY();
            middle[1] = source.getX()+1;
        }
        else if(x_dif == -2){
            middle[0] = source.getY();
            middle[1] = source.getX()-1;
        }
        //Sytem.out.println(middle[0]+","+middle[1]);
        return middle;
    }
}
