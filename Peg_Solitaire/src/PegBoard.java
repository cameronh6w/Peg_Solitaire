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

    public PegBoard(){
        //degfault
        board_size = 7;
        type = Type.ENGLISH;
        board = createBoard();
        moves = getAllPossibleMoves();
        game_over = false;
    }

    public static int getMoves(){return moves;};
    public static int getSize(){return board_size;};
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

    public static int[][] createBoard(){
        //calculate based on type and size but for now im cheating
        int[][] board = {
            {-1, -1, 1, 1, 1, -1, -1},
            {-1, -1, 1, 1, 1, -1, -1},
            { 1,  1, 1, 1, 1,  1,  1},
            { 1,  1, 1, 0, 1,  1,  1}, 
            { 1,  1, 1, 1, 1,  1,  1},
            {-1, -1, 1, 1, 1, -1, -1},
            {-1, -1, 1, 1, 1, -1, -1}
        };
        return board;
    }

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


    public static int getAllPossibleMoves(){
        int moves = 0;
        for (int i=0; i<board_size; i++){
            for (int j=0; j<board_size; j++){
                if(board[i][j]==0){
                    int[][] dir1 = { {i-1,j},{i,j+1},{i+1,j},{i,j-1},}; //N,E,S,W one spot away
                    int[][] dir2 = { {i-2,j},{i,j+2},{i+2,j},{i,j-2},}; //N,E,S,W two spots away

                    for(int k = 0; k < 4; k++){

                        //check if two spots out of bounds to the N,E,S,W
                        if(!(dir2[k][0] < 0
                            || dir2[k][1] >= board_size
                            || dir2[k][0] >= board_size
                            || dir2[k][1] < 0)){
                                
                                if(board[dir2[k][0]][ dir2[k][1]] == 1 
                                    && board[dir1[k][0]][ dir1[k][1]] == 1){
                                        moves++;
                                        //System.out.println("location: "+i+","+j);
                                        //System.out.println("2 away: "+dir2[k][0]+","+dir2[k][1]);
                                       // System.out.println();
                                    }
                        } 
                    }
                }
            }

        }

        return moves;
    }


    public static void makeMove(int[] source, int[] goal){
        //check if source is out of bounds
        if((source[0] < 0
            || source[1] >= board_size
            || source[0] >= board_size
            || source[1] < 0)){
                System.out.println("Out of bounds");
                return;
        }
        if((goal[0] < 0
            || goal[1] >= board_size
            || goal[0] >= board_size
            || goal[1] < 0)){
                System.out.println("Out of bounds");
                return;
        }

        //check if moves are valid
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


        board[source[0]][source[1]] = 0;
        board[middle[0]][middle[1]] = 0;
        board[goal[0]][goal[1]] = 1;
    
        
               
    }

    
}
