import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class BoardGUITest {

    BoardGUI gui;

    @BeforeEach
    void setup() {
        BoardGUI gui = new BoardGUI(7, PegBoard.Type.ENGLISH, false);
    }

    @Test
    void testFirstClickSetsFirstClicked() {
        BoardGUI gui = new BoardGUI(7, PegBoard.Type.ENGLISH, false);
        MyButton btn = gui.buttonsMatrix[3][3];

        btn.getButton().doClick();

        assertNotNull(BoardGUI.firstClicked);
    }

    

}