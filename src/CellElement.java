import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * This class represent a single cell on the board
 *
 * @author Mide Ibraheem
 * @version v1.0
 */
public class CellElement extends JTextField implements Observer  {
    private final JTextField[] selectedCell; // store current selected cell on the board
    Slot cell; // an instance of the Slot class
    int size = 50; // size of a single cell/slot
    Sudoku theGame; // an instance of the sudoku game

    /**
     * Constructor for the class CellElement
     * @param cell - an instance of the Slot class
     * @param selcetedCell - an array that holds reference to a selected cell
     * @param theGame - an instance of the sudoku class
     */
    public CellElement(Slot cell, JTextField[] selcetedCell, Sudoku theGame) {
        this.cell = cell;
        this.selectedCell = selcetedCell;
        this.theGame = theGame;
        cell.addObserver(this);
        this.setPreferredSize(new Dimension(size, size));
        this.setBorder(new LineBorder(new Color(0x4F4D50), 1));
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setBackground(new Color(0xCFCCCD));
        this.setFont(new Font(this.getFont().getName(), Font.BOLD, 18));
        this.setEditable(false);
        this.setForeground(new Color(0x333334));

        var isCellHasValue = !cell.getState().equals("-");
        if (isCellHasValue) {
            this.setText(cell.getState());
            this.setBackground(new Color(0x9AAAAA));
        } else {
            this.setText("-");
            this.addMouseListener(new MouseListener());
        }
    }

    /**
     * An override method that implements the update method in observer interface
     * @param arg - the data thats being passed from the observer subject
     */
    @Override
    public void update(Observable o, Object arg) {
        Slot cell = (Slot) arg;
        this.setText(cell.getState());
        if(!cell.getState().equals("-") && cell.getFillable()) {
            theGame.updateMoves(cell);
            if (theGame.checkWin()) {
                new DisplayMessage("Congratulations, You Win", false, 0);
            }
            if (cell.getFillable()) {
                this.setBackground(new Color(0xCFCCCD));
            }
        }
    }

    /**
     * This private class handles the color change on a clicked cell
     */
    private class MouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JTextField clickedCell = (JTextField) e.getSource();
            selectedCell[0] = clickedCell;
            clickedCell.setBackground(new Color(0xE2D3B6));
        }
    }
}