import javax.swing.*;
import java.awt.*;

/**
 * This class uses the ButtonElement class to create function button group, number button group,
 * and house the logic for function button press.
 *
 * @author Mide Ibraheem
 * @version v1.0
 */
public class ButtonElementGroup extends JPanel {
    String gameMode; // stores preferred player game mode

    /**
     * Constructor of the ButtonElementGroup class
     * @param isFunctionButton - determines whether to return function or numbered buttons
     * @param selectedCell - stores the currently selected cell
     * @param operations - an instance of the operation Class
     * @param mode - stores preferred player game mode
     */
    public ButtonElementGroup(boolean isFunctionButton, CellElement[] selectedCell, Operations operations, String mode) {
        this.gameMode = mode;

        if (isFunctionButton) {
            String[] buttonNames = {"Save", "Undo", "Clear", "Load", "Quit"};
            this.setLayout(new FlowLayout(FlowLayout.CENTER));

            for (String name : buttonNames) {
                JButton button = getjButton(operations, name);
                this.add(button);
            }
        } else {
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
            int[] numbers;

            if (gameMode.equals("hard")) {
                numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
            } else {
                numbers = new int[]{1, 2, 3, 4};
            }

            for (int number : numbers) {
                this.add(new ButtonElement(number, selectedCell));
            }
        }
    }

    /**
     * a private method to listen to function button events and call the appropriate function
     * @param operations - an instance of the operation Class
     * @param name - the text to display on the button
     */
    private JButton getjButton(Operations operations, String name) {
        JButton button = new ButtonElement(name);
        button.addActionListener(e -> {
            if (name.equals("Save")) {
                operations.saveGame();
                new DisplayMessage("Game Saved Successfully!", true, 3000);
            } else if (name.equals("Undo")) {
                if (!operations.undoMove()) {
                    new DisplayMessage("No Prior Moves to Undo", true, 3000);
                }
            } else if (name.equals("Load")) {
                operations.loadGame();
                new DisplayMessage("Game Loaded Successfully!", true, 3000);
            } else if (name.equals("Clear")) {
                operations.clearGame();
                new DisplayMessage("Game Cleared!", true, 3000);
            } else if (name.equals("Quit")) {
                System.exit(0);
            }
        }
        );
        return button;
    }
}
