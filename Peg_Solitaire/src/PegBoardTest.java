
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class PegBoardTest {

    PegBoard board;

    @BeforeEach
    void setup() {
        PegBoard p = new PegBoard();
    }

    @Test
    void testRandomBoardHasValidPlayableValues() {
       int board_size = 7;
        PegBoard.Type type = PegBoard.Type.ENGLISH;
        PegBoard p = new PegBoard(board_size, type,true);

        int[][] board = PegBoard.createBoard(true);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] != -1) {
                    assertTrue(board[i][j] == 0 || board[i][j] == 1);
                }
            }
        }
    }
     
    @Test
    void testEnglishBoardCornersAreInvalid() {
        int board_size = 7;
        PegBoard.Type type = PegBoard.Type.ENGLISH;
        PegBoard p = new PegBoard(board_size, type,false);

        int[][] board = PegBoard.createBoard(false);

        assertEquals(-1, board[0][0]);
        assertEquals(-1, board[0][6]);
        assertEquals(-1, board[6][0]);
        assertEquals(-1, board[6][6]);
    }

    @Test
    void testBoardSize() {
        assertEquals(7, PegBoard.getSize());
    }

    @Test
    void testBoardInitializationCenterEmpty() {
        int[][] b = PegBoard.getBoard();

        assertEquals(0, b[3][3]); // center hole should be empty
    }

    @Test
    void testBoardInitializationPegExists() {
        int[][] b = PegBoard.getBoard();

        assertEquals(1, b[3][1]); // should start with peg
    }

    @Test
    void testResetBoard() {
        int[] source = {3,1};
        int[] goal = {3,3};

        PegBoard.makeMove(source, goal);
        PegBoard.resetBoard();

        int[][] b = PegBoard.getBoard();

        assertEquals(1, b[3][1]);
        assertEquals(0, b[3][3]);
    }

    @Test
    void testValidMoveUpdatesBoard() {
        int[] source = {3,1};
        int[] goal = {3,3};

        PegBoard.makeMove(source, goal);

        int[][] b = PegBoard.getBoard();

        assertEquals(0, b[3][1]); // source now empty
        assertEquals(0, b[3][2]); // jumped peg removed
        assertEquals(1, b[3][3]); // peg moved to goal
    }

    @Test
    void testInvalidMoveDoesNotChangeBoard() {

        int[][] before = PegBoard.getBoard();

        int[] source = {0,0}; // invalid location
        int[] goal = {0,2};

        PegBoard.makeMove(source, goal);

        int[][] after = PegBoard.getBoard();

        assertEquals(before[0][0], after[0][0]);
    }

    @Test
    void testPossibleMovesPositive() {
        int moves = PegBoard.getAllPossibleMoves();

        assertTrue(moves > 0);
    }

    @Test
    void testGameNotOverInitially() {
        assertFalse(PegBoard.getGameOver());
    }
}