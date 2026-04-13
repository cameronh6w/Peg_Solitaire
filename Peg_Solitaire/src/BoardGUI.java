
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
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

    public  static PrintWriter moveWriter;

    public static Boolean isRunningRandom = false;
 
   /*
    BoardGUI(){

        board_size =  7;
        board_type = PegBoard.Type.ENGLISH;
        boardState = new PegBoard(board_size,board_type, false);
    
        buttonsMatrix = new MyButton[board_size][board_size];


        setLayout(new BorderLayout());


        //-------------TOP--------------------------
        //at the top of the screen there is input for board size 

        JPanel topPanel = new JPanel();
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

        JPanel bottomPanel = new JPanel();
        
        error_msg = new JLabel("Click a Peg to move!");
        error_msg.setVerticalAlignment(JLabel.BOTTOM);
        bottomPanel.add(error_msg);
        
        add(bottomPanel, BorderLayout.SOUTH);

        //----------------CENTER----------------------
        //in  the cennter is the main peg solitare board made up of buttons
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(board_size,board_size));
        createButtonUI(boardState,buttonsMatrix,centerPanel, board_size );
        
        //adds thte wholee grid of buttons to the screen
        add(centerPanel, BorderLayout.CENTER);


        //----------------RIGHT-----------------------
        //on the right of  the screeen there is a button to start a new game 

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JButton new_game_button = new JButton();
        new_game_button.setText("Reset Game");
        rightPanel.add(new_game_button);

        JButton auto_play_button = new JButton();
        auto_play_button.setText("Auto Play");
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
        //add(board_title, BorderLayout.WEST);
        //add(eng_rb, BorderLayout.WEST);
        //add(hex_rb, BorderLayout.WEST);
        //add(diam_rb, BorderLayout.WEST);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        leftPanel.add(board_title);
        leftPanel.add(eng_rb);
        leftPanel.add(hex_rb);
        leftPanel.add(diam_rb);

        add(leftPanel, BorderLayout.WEST);

        

        //additional frame settinigs
        this.setSize(700, 500);
        this.setTitle("Sprint 2");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);  
    }

*/
    BoardGUI(int _size, PegBoard.Type _type, Boolean random, PrintWriter _moveWriter){
   
        /* 
        try {
            moveWriter = new PrintWriter("moves.txt");
            moveWriter.println("New Game Started");
            System.out.println("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        isRunningRandom =random;
    
        moveWriter =_moveWriter;


        board_size = _size;
        board_type = _type;
        boardState = new PegBoard(board_size,board_type, random);
        
        buttonsMatrix = new MyButton[board_size][board_size];


        setLayout(new BorderLayout());

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (moveWriter != null) {
                    moveWriter.println("Game closed by user.");
                    moveWriter.close();
                }

                dispose(); // closes window
                System.exit(0); // fully exits program
            }
        });


        //-------------TOP--------------------------
        //at the top of the screen there is input for board size 

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        
        //board size text
        JLabel label = new JLabel("Enter Board Size: ");
        label.setVerticalAlignment(JLabel.CENTER);
        topPanel.add(label);

        //board size input
        JTextField textField = new JTextField(String.valueOf(board_size), 2);
        textField.addActionListener(e -> board_size= ActionController.getSizeInput(textField,  board_size));

        topPanel.add(textField);
        
        add(topPanel, BorderLayout.NORTH);
        
        //----------------BOTTOM-----------------------
        //at the bottom of the screen there is text that tells player if move is invalid 

        JPanel bottomPanel = new JPanel();
        
        error_msg = new JLabel("Click a Peg to move!");
        error_msg.setVerticalAlignment(JLabel.BOTTOM);
        bottomPanel.add(error_msg);
        
        add(bottomPanel, BorderLayout.SOUTH);

        //----------------CENTER----------------------
        //in  the cennter is the main peg solitare board made up of buttons
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(board_size,board_size));
        createButtonUI(boardState,buttonsMatrix,centerPanel, board_size );
        
        //adds thte whole grid of buttons to the screen
        add(centerPanel, BorderLayout.CENTER);


        //----------------RIGHT-----------------------
        //on the right of  the screeen there is a button to start a new game 

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JButton new_game_button = new JButton();
        new_game_button.setText("Reset Game");
        new_game_button.addActionListener(e -> resetBoard(board_size, board_type, false));
        rightPanel.add(new_game_button);

        JButton auto_play_button = new JButton();
        auto_play_button.setText("Auto Play");
        auto_play_button.addActionListener(e -> ActionController.autoPlayAction(  moveWriter, boardState,  buttonsMatrix, error_msg, isRunningRandom));
        rightPanel.add(auto_play_button);

        JButton random_play_button = new JButton();
        random_play_button.setText("Random Play");
        random_play_button.addActionListener(e -> resetBoard(board_size, board_type, true));
        
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


    public static void resetBoard(int _size, PegBoard.Type _type, Boolean random){
        //ActionController.closeWriter();
        //this.dispose();
        if(random)
            moveWriter.println("Randomize board");
        else
            moveWriter.println("Reset board");
        moveWriter.flush();
        new BoardGUI(_size,_type,random, moveWriter);
    }

    public static void createButtonUI(PegBoard boardState, MyButton[][] buttonsMatrix, JPanel centerPanel, int  board_size){
       
        
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

                    
                    button.getButton().addActionListener(e -> firstClicked = ActionController.buttonAction( moveWriter, firstClicked,  button,  boardState,  buttonsMatrix,  error_msg, isRunningRandom));
   


                    //add each button to the panel
                    centerPanel.add(buttonsMatrix[i][j].getButton());

                } 
                // adds an empty label to the panel where ther is no buttons 
                else {
                    centerPanel.add(new JLabel("")); // Add an empty label for inaccessible areas
                }
            }
        }  
    }
}