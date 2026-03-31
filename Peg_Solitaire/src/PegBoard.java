import java.util.ArrayList;

public class PegBoard {
    private static int board_size;
    private static int[][] board;
    private static Type type;
    private static int moves; 
    private static Boolean game_over; 
    
    public enum Type {
        ENGLISH,
        HEXAGON,
        DIAMOND
    }

    //constructor 
    //POST: creates a default board to be english type and size  7
    public PegBoard(){
        //default
        board_size = 7;
        type = Type.ENGLISH;

        board = createBoard(false);
        moves = getAllPossibleMoves();
        game_over = false;
    }

    //constructor 
    //PRE:  Board_size must be an odd  number and grater than 7 
    //      Type must not be null
    //POST: creates a default board to be english type and size  7
    public PegBoard(int _size, Type _type, Boolean random){
        
        board_size =_size ;
        type = _type;
        
        board = createBoard(random);
        moves = getAllPossibleMoves();
        game_over = false;
    }

    //getters and setters
    public static int getMoves(){return moves;};
    public static int getSize(){return board_size;};
    public static Type getType(){return type;};
    public static  int[][] getBoard(){return board;};
    public static void setType(Type t){type = t; };
    public static void setSize(int size){board_size = size;};

    public static Boolean getGameOver(){
        checkGameOver();
        return game_over;
    };

    public static void checkGameOver(){
        moves = getAllPossibleMoves();
        if(moves > 0)
            game_over = false;
        else
            game_over = true;
    }

    public static void resetBoard(){
        board = createBoard(false);
    }


    //PRE:  Board_size must be an odd  number and grater than 7 
    //      Type must not be null
    //POST: returns a  board  with the  type and size that the booard  is currently set to
    public static int[][] createBoard(Boolean random){

        int[][] board = new int[board_size][board_size];


        switch (type){
            //--------ENGLISH TYPE---------
            case ENGLISH:
                int indexOfStartSpace = (board_size - 3) / 2;

                for(int i = 0; i< board_size; i++){
                    for(int j = 0; j< board_size; j++){
                        //if i is in middle rows
                        if(i >= indexOfStartSpace && i < indexOfStartSpace + 3 )
                            if(random) 
                                board[i][j] = (int) (Math.random()* (2));
                            else
                                board[i][j] = 1;
                        else{
                            //if j is in middle 3 collums
                            if(j >= indexOfStartSpace && j < indexOfStartSpace + 3 ){
                               if(random) 
                                board[i][j] = (int) (Math.random()* (2));
                            else
                                board[i][j] = 1;

                            }
                            else{
                                board[i][j] = -1;
                            }
                        }
                        //set centter to 0
                        if( i == board_size / 2 && j == board_size / 2)
                            if(random) 
                                board[i][j] = (int) (Math.random()* (2));
                            else
                                board[i][j] = 0;
                            //board[i][j] = 0;   
                    }
                }
                //printBoard();
                break;

            //--------DIAMOND TYPE---------
            case DIAMOND:
                
                int mid =  board_size/2;

                //do top half first 
                for(int i = 0; i<= mid; i++){
                    for(int j = 0; j< board_size; j++){
                        
                        //if j is in the range of  middle +/- i
                        if(j >= (mid - i) && j <= (mid + i) ){
                            if(random) 
                                board[i][j] = (int) (Math.random()* (2));
                            else
                                board[i][j] = 1;
                        }
                        else{
                            board[i][j] = -1;
                        }

                        // set middle to 0
                        if( i == mid && j == mid){
                            if(random) 
                                board[i][j] = (int) (Math.random()* (2));
                            else
                                board[i][j] = 0;
                        }
                            //board[i][j] = 0;   
                    }
                }

                //for second  half, i_sub will be counting down
                int i_sub =  mid - 1;

                //start at the next i value (mid + 1)
                for(int i = (mid+1); i< board_size; i++){
                    for(int j = 0; j< board_size; j++){
                        
                         //if j is in the range of  middle +/- i_sub
                        if(j >= (board_size/2 - i_sub) && j <= (board_size/2 + i_sub) ){
                            if(random) 
                                board[i][j] = (int) (Math.random()* (2));
                            else
                                board[i][j] = 1;
                        }
                        else{
                            board[i][j] = -1;
                        }
                        
                    }
                    //decrease  each row
                    i_sub--;
                }

                break;


            //--------HEXAGON TYPE---------
            case HEXAGON:
                indexOfStartSpace = (board_size - 3) / 2;

                for(int i = 0; i< indexOfStartSpace + 3; i++){
                    for(int j = 0; j< board_size; j++){
                        //if i is in middle rows
                        if(i >= indexOfStartSpace && i < indexOfStartSpace + 3 )
                            if(random) 
                                board[i][j] = (int) (Math.random()* (2));
                            else
                                board[i][j] = 1;
                        else{
                            //if j is in middle 3 collums
                            if(j >= indexOfStartSpace-i && j < indexOfStartSpace + 3 +i){
                                if(random) 
                                    board[i][j] = (int) (Math.random()* (2));
                                else
                                    board[i][j] = 1;
                                }
                            else{
                                board[i][j] = -1;
                            }
                        }
                        //set centter to 0
                        if( i == board_size / 2 && j == board_size / 2){
                            if(random) 
                                board[i][j] = (int) (Math.random()* (2));
                            else
                                board[i][j] = 0; 
                        }  
                    }
                } 


                //for second  half, i_sub will be counting down
                i_sub =   board_size/2 - 2;

                for(int i = indexOfStartSpace + 3; i< board_size; i++){
                    for(int j = 0; j< board_size; j++){
                        //if i is in middle rows
                        if(i >= indexOfStartSpace && i < indexOfStartSpace + 3 ){
                            if(random) 
                                board[i][j] = (int) (Math.random()* (2));
                            else
                                board[i][j] = 1;
                        }
                        else{
                            //if j is in middle 3 collums
                            if(j >= indexOfStartSpace-i_sub && j < indexOfStartSpace + 3 +i_sub){
                               if(random) 
                                    board[i][j] = (int) (Math.random()* (2));
                                else
                                    board[i][j] = 1;
                            }
                            else{
                                board[i][j] = -1;
                            }
                        }
                    }
                    i_sub--;
                } 
                break;
        }
        return board;
    }

    //POST: outputs board into terminal for testing
    public static void printBoard(){
         for (int i=0; i<board_size; i++){
            for (int j=0; j<board_size; j++){
                if(board[i][j]>=0)
                    System.out.print(" ");
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }


    public static ArrayList<ArrayList<Integer>> getAllPossibleMovesList(){
        int moves = 0;
        ArrayList<ArrayList<Integer>> allMovesList = new ArrayList<>();


        
        for (int i=0; i<board_size; i++){
            for (int j=0; j<board_size; j++){
                
                // look at all the empty spaces, and  see if there are two pins in any directtion 
                if(board[i][j]==0){
                   
                    //gttting the board spots for all driections,  on and two spots away
                    int[][] dir1 = { {i-1,j},{i,j+1},{i+1,j},{i,j-1},}; //N,E,S,W one spot away
                    int[][] dir2 = { {i-2,j},{i,j+2},{i+2,j},{i,j-2},}; //N,E,S,W two spots away

                    //for each direction
                    for(int k = 0; k < 4; k++){
                        
                        //check if two spots away is out of bounds
                        if(!(dir2[k][0] < 0
                            || dir2[k][1] >= board_size
                            || dir2[k][0] >= board_size
                            || dir2[k][1] < 0)){
                                
                                //if both spots are not out of bounds and thye both are pins, increase move count
                                if(board[dir2[k][0]][ dir2[k][1]] == 1  
                                    && board[dir1[k][0]][ dir1[k][1]] == 1){
                                        allMovesList.add(new ArrayList<Integer>());
                                        allMovesList.get(moves).add(dir2[k][0]);
                                        allMovesList.get(moves).add(dir2[k][1]) ;
                                        allMovesList.get(moves).add(i);
                                        allMovesList.get(moves).add(j);
                                        moves++;

                                }
                                
                        } 
                    }
                   
                }
            }
        }

        return allMovesList;
    }
    //POST: returns the number of moves possible on the board 
    public static int getAllPossibleMoves(){
        int moves = 0;
       
        for (int i=0; i<board_size; i++){
            for (int j=0; j<board_size; j++){
                
                // look at all the empty spaces, and  see if there are two pins in any directtion 
                if(board[i][j]==0){
                   
                    //gttting the board spots for all driections,  on and two spots away
                    int[][] dir1 = { {i-1,j},{i,j+1},{i+1,j},{i,j-1},}; //N,E,S,W one spot away
                    int[][] dir2 = { {i-2,j},{i,j+2},{i+2,j},{i,j-2},}; //N,E,S,W two spots away

                    //for each direction
                    for(int k = 0; k < 4; k++){
                        
                        //check if two spots away is out of bounds
                        if(!(dir2[k][0] < 0
                            || dir2[k][1] >= board_size
                            || dir2[k][0] >= board_size
                            || dir2[k][1] < 0)){
                                
                                //if both spots are not out of bounds and thye both are pins, increase move count
                                if(board[dir2[k][0]][ dir2[k][1]] == 1  
                                    && board[dir1[k][0]][ dir1[k][1]] == 1){
                                        moves++;
                                }
                        } 
                    }
                   
                }
            }
        }
        //return thte number of moves 
        return moves;
    }

    //PRE: source and goal must be arrays with size  2, 0 positon is y, 1 position is x
    //POST: checks for any invalid moves, then updates the board matrix  for any valid  moves  
    public static void makeMove(int[] source, int[] goal){
        
        //check if source is out of bounds
        if((source[0] < 0
            || source[1] >= board_size
            || source[0] >= board_size
            || source[1] < 0)){
                System.out.println("Out of bounds");
                return;
        }

        //check if goal is out of bounds
        if((goal[0] < 0
            || goal[1] >= board_size
            || goal[0] >= board_size
            || goal[1] < 0)){
                System.out.println("Out of bounds");
                return;
        }

        //check if moves are valid (source must be a pin, and  goal must be empty)
        if(board[source[0]][source[1]] != 1){
            System.out.println("invalid source");
            return;
        }
        if(board[goal[0]][goal[1]] != 0){
            System.out.println("invalid goal");
            return;
        }

        //check if there is a pin between goal and source
        int y_dif= goal[0]-source[0];
        int x_dif = goal[1]-source[1];
        
        if(Math.abs(y_dif+x_dif) != 2){
            System.out.println("invalid move");
            return;
        }

        int[] middle = {-1,-1};
        
        //find middle pin location
        if(y_dif == 2){
            middle[0] = source[0]+1;
            middle[1] = source[1];
        }
        else if(y_dif == -2){
            middle[0] = source[0]-1;
            middle[1] = source[1];
        }
        else if(x_dif == 2){
            middle[0] = source[0];
            middle[1] = source[1]+1;
        }
        else if(x_dif == -2){
            middle[0] = source[0];
            middle[1] = source[1]-1;
        }

        //check if middle pin is valid (must be a pin)
        if(board[middle[0]][middle[1]] == 0){
            System.out.println("invalid middle pin");
            return;
        }

        //finally  update the board
        board[source[0]][source[1]] = 0;
        board[middle[0]][middle[1]] = 0;
        board[goal[0]][goal[1]] = 1;       
    }
}