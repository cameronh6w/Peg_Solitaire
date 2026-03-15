
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class BoardGUI extends JFrame{
    
    //global variables so Action Listener can access them 
    public MyButton[][] buttonsMatrix = new MyButton[7][7];

    public MyButton firstClicked = null;
    public MyButton secondClicked = null;
    JLabel error_msg;
 
    PegBoard boardState = new PegBoard();
    
    BoardGUI(){
        
        setLayout(new BorderLayout());


        //-------------TOP--------------------------
        //at the top of the screen there is input for board size 

        Panel topPanel = new Panel();
        topPanel.setLayout(new FlowLayout());
        
        //board size text
        JLabel label = new JLabel("Enter Board Size: ");
        label.setVerticalAlignment(JLabel.CENTER);
        topPanel.add(label);

        //board size input (doesn't do anything right now)
        JTextField textField = new JTextField("7", 2);
        topPanel.add(textField);
        
        add(topPanel, BorderLayout.NORTH);
        
        //----------------BOTTOM-----------------------
        //at the bottom of the screen there is text that tells player if move is invalid 

        Panel bottomPanel = new Panel();
        
        error_msg = new JLabel("Click a Peg to move!");
        error_msg.setVerticalAlignment(JLabel.BOTTOM);
        bottomPanel.add(error_msg);
        
        add(bottomPanel, BorderLayout.SOUTH);

        //----------------RIGHT-----------------------
        //on the right of  the screeen there is a button to start a new game 

        Panel rightPanel = new Panel();
        rightPanel.setLayout(new FlowLayout());

        JButton new_game_button = new JButton();
        new_game_button.setText("New Game");
        
        //gives new game button action
        new_game_button.addActionListener(new ActionListener() {      
            @Override
            public void actionPerformed(ActionEvent e) {
                
                //reset the board matrix
                boardState.resetBoard();
 
                //update all the buttons to the board state
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 7; j++) {
                        if (boardState.getBoard()[i][j] != -1) 
                            buttonsMatrix[i][j].resetButton(boardState.getBoard()[i][j]);
                    }
                }
            }
        });

        rightPanel.add(new_game_button);
        
        add(rightPanel, BorderLayout.EAST);

        //----------------LEFT-----------------------
        //on the left of the screen, there  are radio button options for board type 

        //create button label
        JLabel board_title = new JLabel();
        board_title.setText("Board Type");
        board_title.setFont(new Font("MV  Boli", Font.PLAIN,16));
        board_title.setOpaque(true);
        board_title.setBounds(0,125,90,30);
        
        // Create the radio buttons (these dont do anything right now) 
        JRadioButton eng_rb,hex_rb,diam_rb;
        eng_rb = new JRadioButton("English");
        eng_rb.setBounds(0,150,90,50);
        eng_rb.setSelected(true);
        
        hex_rb = new JRadioButton("Hexagon");
        hex_rb.setBounds(0,180,90,50);
        
        diam_rb = new JRadioButton("Diamond");
        diam_rb.setBounds(0,225,90,50);
    
     
        // Group the radio buttons. This ensures only one is selectable.
        ButtonGroup group = new ButtonGroup();
        group.add(eng_rb);
        group.add(hex_rb);
        group.add(diam_rb);

        //add elements to the left screen
        add(board_title, BorderLayout.WEST);
        add(eng_rb, BorderLayout.WEST);
        add(hex_rb, BorderLayout.WEST);
        add(diam_rb, BorderLayout.WEST);

        //----------------CENTER----------------------
        //in  the cennter is the main peg solitare board made up of buttons
        
        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(7,7));

        //fill the grid with buttons
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                
                //board state = -1 means there is no button  there
                if (boardState.getBoard()[i][j] != -1) {
                   
                    //create parameters for a MyButton object
                    JButton new_button = new JButton();
                    int state = boardState.getBoard()[i][j];
                    MyButton button = new MyButton(new_button, state, j,i);

                    //put button in the array 
                    buttonsMatrix[i][j] = button;
                    
                    //give every button an action when clicked
                    button.getButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            
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
                                

                                //resets the move
                                firstClicked = null;
                            }

                            //updates for when  the game is over
                            if(boardState.getGameOver()){
                                error_msg.setText("No More Possible Moves! Game Over!");
                            }
                        } 
                    });
            
                    //add each button to the panel
                    centerPanel.add(buttonsMatrix[i][j].getButton());

                } 
                // adds an empty label to the panel where ther is no buttons 
                else {
                    centerPanel.add(new Label("")); // Add an empty label for inaccessible areas
                }
            }
        }
        
        //adds thte wholee grid of buttons to the screen
        add(centerPanel, BorderLayout.CENTER);


        //additional frame settinigs
        this.setSize(700, 500);
        this.setTitle("Sprint 2");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);  
    }
}