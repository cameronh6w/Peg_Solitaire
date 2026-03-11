public class Main {
    public static void main(String[] args) {
        PegBoard board = new  PegBoard();
        System.out.println("moves:" + board.getMoves());
        
        //System.out.println("game over:" + board.getGameOver());

        board.printBoard();
        int[] source = {3,1};
        int[] end = {3,3};
        board.makeMove(source, end);
        System.out.println();
        board.printBoard();
        System.out.println("moves:" + board.getMoves());
    }
}