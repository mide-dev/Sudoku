import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * This class display the whole board.
 *
 * @author Mide Ibraheem
 * @version v1.0
 */
public class BoardElement extends JPanel {

    /**
     * Constructor of the BoardElement class
     * @param selectedCell - stores the currently selected cell
     * @param theGame - an instance of the Sudoku class
     */
    public BoardElement(JTextField[] selectedCell, Sudoku theGame) {
        int boardSize = theGame.getMoves().length;
        int groupSize = (int) Math.sqrt(boardSize);
        this.setLayout(new GridLayout(groupSize, groupSize));
        this.setBorder(new LineBorder(new Color(0x4F4D50), 2));

        for (int i = 0; i < groupSize; i++) {
            for (int j = 0; j < groupSize; j++) {
                this.add(new CellElementGroup(i, j, groupSize, theGame.getMoves(), selectedCell, theGame));
            }
        }
    }
}
