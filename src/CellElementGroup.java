import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * This class display cells in group. Makes it easier to create the inner grid on a sudoku board.
 *
 * @author Mide Ibraheem
 * @version v1.0
 */
public class CellElementGroup extends JPanel {

    /**
     * Constructor of the CellElementGroup class
     * @param startRowIndex - takes a row index to start displaying a cell group from.
     * @param startColumnIndex - takes a column index to start displaying a cell group from.
     * @param groupSize - decides how many cell in each cluster i.e. 3*3 for a 9*9 board.
     * @param board - an instance of the Slot class
     * @param selectedCell - stores the currently selected cell
     * @param theGame - takes in the Sudoku class as a parameter
     */
    public CellElementGroup(int startRowIndex, int startColumnIndex, int groupSize, Slot[][] board, JTextField[] selectedCell, Sudoku theGame) {
        this.setLayout(new GridLayout(groupSize, groupSize));
        this.setBorder(new LineBorder(new Color(0x4F4D50), 1));

        int rowIndex = startRowIndex * groupSize;
        for (int i = 0; i < groupSize; i++) {
            int columnIndex = (startColumnIndex * groupSize);
            for (int j = 0; j < groupSize; j++) {
                Slot cell = board[rowIndex][columnIndex];
                this.add(new CellElement(cell, selectedCell, theGame));

                columnIndex++;
            }
            rowIndex++;
        }
    }
}
