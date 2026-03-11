import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PegBoard board = new  PegBoard();
        System.out.println("moves:" + board.getMoves());
        Scanner scanner = new Scanner(System.in);

        int start_x,start_y,end_x,end_y;
        int[] source = {-1,-1};
        int[] end = {-1,-1};
        while(!board.getGameOver()){
            board.printBoard();
            
            System.out.print("select an x to start: ");
            start_x = scanner.nextInt();
            source[1] = start_x;
            
            System.out.print("select an y to start: ");
            start_y = scanner.nextInt();
            source[0] = start_y;
            
            System.out.print("select an x to end: ");
            end_x = scanner.nextInt();
            end[1] = end_x;
            
            System.out.print("select an y to end: ");
            end_y = scanner.nextInt();
            end[0] = end_y;

            
            System.out.println(source[0]+" "+source[1]+"-"+end[0]+" "+end[1]);
            
            board.makeMove(source, end);
            System.out.println();
            board.printBoard();
            System.out.println("moves:" + board.getMoves());
        }
         
    }
}