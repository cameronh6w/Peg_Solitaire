
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class BoardGUI extends JFrame{
    
    //global variables so Action Listener can access them 
    private JButton[][] buttons = new JButton[7][7];


    private int[][] boardState = {
        {-1, -1, 1, 1, 1, -1, -1},
        {-1, -1, 1, 1, 1, -1, -1},
        {1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 0, 1, 1, 1}, 
        {1, 1, 1, 1, 1, 1, 1},
        {-1, -1, 1, 1, 1, -1, -1},
        {-1, -1, 1, 1, 1, -1, -1}
    };

    
    BoardGUI(){
        
        setLayout(new BorderLayout());


        //-------------TOP--------------------------
        Panel topPanel = new Panel();
        topPanel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Enter Board Size: ");
        label.setVerticalAlignment(JLabel.BOTTOM);
        topPanel.add(label);
        
        JTextField textField = new JTextField(2);
        topPanel.add(textField);
        add(topPanel, BorderLayout.NORTH);


        //----------------RIGHT-----------------------
        Panel rightPanel = new Panel();
        rightPanel.setLayout(new FlowLayout());

        JButton new_game_button = new JButton();
        new_game_button.setText("New Game");
        rightPanel.add(new_game_button);
        
        add(rightPanel, BorderLayout.EAST);


        //----------------LEFT-----------------------
        JLabel board_title = new JLabel();
        board_title.setText("Board Type");
        board_title.setFont(new Font("MV  Boli", Font.PLAIN,16));
        board_title.setOpaque(true);
        board_title.setBounds(0,125,90,30);
        
        // Create the radio buttons 
        JRadioButton eng_rb,hex_rb,diam_rb;
        eng_rb = new JRadioButton("English");
        eng_rb.setBounds(0,150,90,50);
        
        hex_rb = new JRadioButton("Hexagon");
        hex_rb.setBounds(0,180,90,50);
        
        diam_rb = new JRadioButton("Diamond");
        diam_rb.setBounds(0,225,90,50);
    
     
        // Group the radio buttons. This ensures only one is selectable.
        ButtonGroup group = new ButtonGroup();
        group.add(eng_rb);
        group.add(hex_rb);
        group.add(diam_rb);

        add(board_title, BorderLayout.WEST);
        add(eng_rb, BorderLayout.WEST);
        add(hex_rb, BorderLayout.WEST);
        add(diam_rb, BorderLayout.WEST);
        


        //----------------CENTER----------------------
        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(7,7));

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (boardState[i][j] != -1) {
                    buttons[i][j] = new JButton();
                    buttons[i][j].setText(boardState[i][j] == 1 ? "P" : "O"); // "P" for Peg, "O" for Empty
                    buttons[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getActionCommand() == "P"){
                                System.out.println("Button");
    