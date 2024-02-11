import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represent a single reuseable button on the GraphicalUI
 *
 * @author Mide Ibraheem
 * @version v1.0
 */
public class ButtonElement extends JButton {
    private CellElement[] selectedCell = null; // stores the current clicked cell
    int functionButtonWidth = 80; // function button width
    int functionButtonHeight = 30; // function button height
    int numberButtonSize = 35; // number button width and height

    /**
     * Constructor 1 for the class ButtonElement
     * @param buttonText - is required to set button text
     */
    public ButtonElement(String buttonText) {
        super(buttonText);

        // style settings for function buttons
        this.setPreferredSize(new Dimension(functionButtonWidth, functionButtonHeight));
        this.setMaximumSize(new Dimension(functionButtonWidth, functionButtonHeight));
        this.setMinimumSize(new Dimension(functionButtonWidth, functionButtonHeight));
        this.setFocusable(false);
        this.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.setBackground(Color.gray);
        this.setForeground(Color.white);
    }

    /**
     * Constructor 2 for the class ButtonElement
     * @param buttonText - is required to set button text
     * @param selectedCell - stores the current cell
     */
    public ButtonElement(int buttonText, CellElement[] selectedCell) {
        super(String.valueOf(buttonText));
        this.selectedCell = selectedCell;

        // style settings for number buttons
        this.setPreferredSize(new Dimension(numberButtonSize, numberButtonSize));
        this.setMaximumSize(new Dimension(numberButtonSize, numberButtonSize));
        this.setMinimumSize(new Dimension(numberButtonSize, numberButtonSize));
        this.setFocusable(false);
        this.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.setBackground(Color.gray);
        this.setForeground(Color.white);
        this.addActionListener(new NumberButtonActionListener());
    }

    /**
     * This private class sets a slot to the value of clicked number button
     */
    private class NumberButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedCell != null) {
                CellElement currentCell = ButtonElement.this.selectedCell[0];
                if (currentCell != null) {
                    JButton source = (JButton) e.getSource();
                    String newValue = source.getText();
                    currentCell.setText(newValue);
                    currentCell.cell.setState(newValue); // since "cell" stores a slot object, we can call slot methods on it.
                } else {
                    new DisplayMessage("Please click on a cell before assigning value!", true, 3000);
                }

            }
        }
    }
}
