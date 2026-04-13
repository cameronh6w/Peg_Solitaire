import java.io.PrintWriter;

public class Main {
    public static PrintWriter moveWriter;
    public static void main(String[] args) {
        System.out.println("test");

        try {
            moveWriter = new PrintWriter("moves.txt");
            moveWriter.println("New Game Started");
        } catch (Exception e) {
            e.printStackTrace();
        }
    

        BoardGUI b = new BoardGUI(7,PegBoard.Type.ENGLISH, false, moveWriter);
        
    }
}