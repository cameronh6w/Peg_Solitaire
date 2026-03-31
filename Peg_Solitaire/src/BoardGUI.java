
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;


public class BoardGUI extends JFrame{
    
    //global variables so Action Listener can access them 
    int board_size; 



    PegBoard.Type board_type;
    PegBoard boardState;
    
    MyButton[][] buttonsMatrix;

    public  void getMatrix(MyButton[][] newMatrix){
         for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                newMatrix[i][j] =  buttonsMatrix[i][j];
            }
        }

    }

    public static MyButton firstClicked = null;
    public static MyButton secondClicked = null;
    public static JLabel error_msg;
 
   
    BoardGUI(){

        board_size =  7;
        board_type = PegBoard.Type.ENGLISH;
        boardState = new PegBoard(board_size,board_type);
    
        buttonsMatrix = new MyButton[board_size][board_size];


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
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //do try catch here
                int input = Integer.parseInt(textField.getText());
                if(input % 2 != 0 && input >=7 ){
                    board_size = input;
                }
            }
        });

        topPanel.add(textField);
        
        add(topPanel, BorderLayout.NORTH);
        
        //----------------BOTTOM-----------------------
        //at the bottom of the screen there is text that tells player if move is invalid 

        Panel bottomPanel = new Panel();
        
        error_msg = new JLabel("Click a Peg to move!");
        error_msg.setVerticalAlignment(JLabel.BOTTOM);
        bottomPanel.add(error_msg);
        
        add(bottomPanel, BorderLayout.SOUTH);

        //----------------CENTER----------------------
        //in  the cennter is the main peg solitare board made up of buttons
        
        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(board_size,board_size));
        createButtonUI(boardState,buttonsMatrix,centerPanel, board_size );
        
        //adds thte wholee grid of buttons to the screen
        add(centerPanel, BorderLayout.CENTER);


        //----------------RIGHT-----------------------
        //on the right of  the screeen there is a button to start a new game 

        Panel rightPanel = new Panel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JButton new_game_button = new JButton();
        new_game_button.setText("Reset Game");
        //gives new game button action
        new_game_button.addActionListener(new ActionListener() {      
            @Override
            @SuppressWarnings("static-access")
            public void actionPerformed(ActionEvent e) {

                //reset the board matrix
                boardState.resetBoard();

                //update all the buttons to the board state
                for (int i = 0; i < board_size; i++) {
                    for (int j = 0; j < board_size; j++) {
                        if (boardState.getBoard()[i][j] != -1) 
                            buttonsMatrix[i][j].resetButton(boardState.getBoard()[i][j]);
                    }
                } 
            }
        });
        rightPanel.add(new_game_button);

/*
        JButton auto_play_button = new JButton();
        auto_play_button.setText("Auto test Play");
        auto_play_button.addActionListener(new ActionListener() {      
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });*/

        //rightPanel.add(auto_play_button);

       
                //ArrayList<ArrayList<Integer>> list = boardState.getAllPossibleMovesList();
        
                /* 
                int move = 0;
                while(list.size()>0){
                    move = (int) (Math.random()* (list.size()));
                    clickButton(list.get(move).get(0),list.get(move).get(1),boardState,buttonsMatrix);
                    clickButton(list.get(move).get(2),list.get(move).get(3),boardState,buttonsMatrix);
                    list = boardState.getAllPossibleMovesList();
                    try {
                        Thread.sleep(500); 
                    } catch (InterruptedException a) {
                        a.printStackTrace(); 
                    }

                }*/
            //}
       

        JButton random_play_button = new JButton();
        random_play_button.setText("Random Play");
        
        rightPanel.add(random_play_button);
        
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

        eng_rb.addActionListener(new ActionListener() {      
            @Override
            @SuppressWarnings("static-access")
            public void actionPerformed(ActionEvent e) {
                PegBoard.Type eng = PegBoard.Type.ENGLISH;
                //reset the board matrix
                boardState.setType(eng);
                board_type = eng;
            }
        });
        
        hex_rb = new JRadioButton("Hexagon");
        hex_rb.setBounds(0,180,90,50);
        hex_rb.addActionListener(new ActionListener() {      
            @Override
            @SuppressWarnings("static-access")
            public void actionPerformed(ActionEvent e) {
                PegBoard.Type hex = PegBoard.Type.HEXAGON;
                //reset the board matrix
                boardState.setType(hex);
                board_type = hex;
            }
        });
        
        diam_rb = new JRadioButton("Diamond");
        diam_rb.setBounds(0,225,90,50);

        diam_rb.addActionListener(new ActionListener() {      
            @Override
            @SuppressWarnings("static-access")
            public void actionPerformed(ActionEvent e) {
                PegBoard.Type dim = PegBoard.Type.DIAMOND;
                //reset the board matrix
                boardState.setType(dim);
                board_type = dim;
            }
        });
    
     
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

        

        //additional frame settinigs
        this.setSize(700, 500);
        this.setTitle("Sprint 2");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);  
    }


    BoardGUI(int _size, PegBoard.Type _type){
        board_size = _size;
        board_type = _type;
        boardState = new PegBoard(board_size,board_type);
    
        buttonsMatrix = new MyButton[board_size][board_size];


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
        JTextField textField = new JTextField(String.valueOf(board_size), 2);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //do try catch here
                int input = Integer.parseInt(textField.getText());
                if(input % 2 != 0 && input >=7 ){
                    board_size = input;
                }
            }
        });

        topPanel.add(textField);
        
        add(topPanel, BorderLayout.NORTH);
        
        //----------------BOTTOM-----------------------
        //at the bottom of the screen there is text that tells player if move is invalid 

        Panel bottomPanel = new Panel();
        
        error_msg = new JLabel("Click a Peg to move!");
        error_msg.setVerticalAlignment(JLabel.BOTTOM);
        bottomPanel.add(error_msg);
        
        add(bottomPanel, BorderLayout.SOUTH);

        //----------------CENTER----------------------
        //in  the cennter is the main peg solitare board made up of buttons
        
        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(board_size,board_size));
        createButtonUI(boardState,buttonsMatrix,centerPanel, board_size );
        
        //adds thte whole grid of buttons to the screen
        add(centerPanel, BorderLayout.CENTER);


        //----------------RIGHT-----------------------
        //on the right of  the screeen there is a button to start a new game 

        Panel rightPanel = new Panel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JButton new_game_button = new JButton();
        new_game_button.setText("Reset Game");
        //gives new game button action
        new_game_button.addActionListener(new ActionListener() {      
            @Override
            @SuppressWarnings("static-access")
            public void actionPerformed(ActionEvent e) {
                test(board_size, board_type);
                /* 
                //reset the board matrix
                boardState.resetBoard();

                //update all the buttons to the board state
                for (int i = 0; i < board_size; i++) {
                    for (int j = 0; j < board_size; j++) {
                        if (boardState.getBoard()[i][j] != -1) 
                            buttonsMatrix[i][j].resetButton(boardState.getBoard()[i][j]);
                    }
                } 
                */



                
            }
        });
        rightPanel.add(new_game_button);

        JButton auto_play_button = new JButton();
        auto_play_button.setText("Auto Play");
        auto_play_button.addActionListener(new ActionListener() {      
            @Override
            @SuppressWarnings("static-access")
            public void actionPerformed(ActionEvent e) {
                
                ArrayList<ArrayList<Integer>> list = boardState.getAllPossibleMovesList();

                int move = (int) (Math.random()* (list.size()));

                clickButton(list.get(move).get(0),list.get(move).get(1),boardState,buttonsMatrix);
                System.out.println("move 1");
                clickButton(list.get(move).get(2),list.get(move).get(3),boardState,buttonsMatrix);
                list = boardState.getAllPossibleMovesList();
                System.out.println("move 2");


                
                try {
                    Thread.sleep(200); 
                } catch (InterruptedException a) {
                    a.printStackTrace(); 
                }
                
                  /* 
                int move = 0;
                while(list.size()>0){
                    
                    move = (int) (Math.random()* (list.size()));
                    System.out.println(move);
                    clickButton(list.get(move).get(0),list.get(move).get(1),boardState,buttonsMatrix);
                    System.out.println("move 1");
                    clickButton(list.get(move).get(2),list.get(move).get(3),boardState,buttonsMatrix);
                    System.out.println("move 2");

                    try {
                        Thread.sleep(1000); 
                    } catch (InterruptedException a) {
                        a.printStackTrace(); 
                    }
                    list = boardState.getAllPossibleMovesList();

                }*/

            }
        });
        rightPanel.add(auto_play_button);

        JButton random_play_button = new JButton();
        random_play_button.setText("Random Play");
        rightPanel.add(random_play_button);
        
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
        if(board_type == PegBoard.Type.ENGLISH)
            eng_rb.setSelected(true);

        eng_rb.addActionListener(new ActionListener() {      
            @Override
            @SuppressWarnings("static-access")
            public void actionPerformed(ActionEvent e) {
                PegBoard.Type eng = PegBoard.Type.ENGLISH;
                //reset the board matrix
                boardState.setType(eng);
                board_type = eng;
            }
        });
        
        hex_rb = new JRadioButton("Hexagon");
        hex_rb.setBounds(0,180,90,50);
        if(board_type == PegBoard.Type.HEXAGON)
            hex_rb.setSelected(true);

        hex_rb.addActionListener(new ActionListener() {      
            @Override
            @SuppressWarnings("static-access")
            public void actionPerformed(ActionEvent e) {
                PegBoard.Type hex = PegBoard.Type.HEXAGON;
                //reset the board matrix
                boardState.setType(hex);
                board_type = hex;
            }
        });
        
        diam_rb = new JRadioButton("Diamond");
        diam_rb.setBounds(0,225,90,50);
        if(board_type == PegBoard.Type.DIAMOND)
            diam_rb.setSelected(true);
        diam_rb.addActionListener(new ActionListener() {      
            @Override
            @SuppressWarnings("static-access")
            public void actionPerformed(ActionEvent e) {
                PegBoard.Type dim = PegBoard.Type.DIAMOND;
                //reset the board matrix
                boardState.setType(dim);
                board_type = dim;
            }
        });
    
     
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

        

        //additional frame settinigs
        this.setSize(700, 500);
        this.setTitle("Sprint 2");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);  
    }


    public static void test(int _size, PegBoard.Type _type){
        new BoardGUI(_size,_type);
    }

     
    public static  void clickButton(int y, int x, PegBoard board,  MyButton[][] buttonsMatrix){
        if(board.getBoard()[y][x] != -1){
            MyButton button = buttonsMatrix[y][x]; 
       
            button.getButton().doClick(); 

            /* 
            try {
                Thread.sleep(200); 
            } catch (InterruptedException a) {
                a.printStackTrace(); 
            }*/
        }
    }


    @SuppressWarnings("static-access")
    public static void createButtonUI(PegBoard boardState, MyButton[][] buttonsMatrix, Panel centerPanel, int  board_size){
        //fill the grid with buttons
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                
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
    }
}