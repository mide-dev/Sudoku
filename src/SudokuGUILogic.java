import javax.swing.*;
import java.awt.*;

/**
 * This class implements the Graphical interface logic for the player to interact with the game
 *
 * @author Mide Ibraheem
 * @version v1.0
 */
public class SudokuGUILogic extends JFrame {
    /**
     * The interface is divided into 3 sections, "topPanel", "middlePanel", and "bottomPanel"
     * */
    JPanel mainContent; // acts as the container for all sections
    JPanel topPanel; // wraps function buttons in the top section of the UI
    JPanel middlePanel; // wraps number buttons
    JPanel bottomPanel; // wraps the Sudoku board
    int frameWidth = 600; // width of the UI
    int frameHeight = 700; // Height of the UI
    int topPanelHeight = 80; // Top panel height

    private CellElement[] selectedCell = new CellElement[1]; // stores a clicked cell in an array
    private Sudoku sudoku; // creates an instance of the Sudoku class
    private Operations operations; // an instance of the operations class

    /**
     * Constructor for the class SudokuGUILogic
     * @param mode - determines the game mode i.e. easy or hard
     */
    public SudokuGUILogic(String mode) {
        selectedCell[0] = null;
        this.sudoku = new Sudoku(mode);
        this.operations = new Operations(sudoku);

        if (mode.equals("easy")) { // change frame width and height if mode is easy
            frameHeight = 500;
            frameWidth = 400;
        }

        this.setSize(frameWidth, frameHeight);

        mainContent = new JPanel();
        mainContent.setSize(frameWidth, frameHeight);
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));

        topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        topPanel.setPreferredSize(new Dimension(frameWidth, topPanelHeight));
        topPanel.setMaximumSize(new Dimension(frameWidth, topPanelHeight));
        topPanel.add(new ButtonElementGroup(true, selectedCell, operations, mode));

        middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        middlePanel.setPreferredSize(new Dimension(frameWidth, topPanelHeight));
        middlePanel.setMaximumSize(new Dimension(frameWidth, topPanelHeight));
        middlePanel.add(new ButtonElementGroup(false, selectedCell, operations, mode));

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setPreferredSize(new Dimension(frameWidth, frameHeight - topPanelHeight));
        bottomPanel.setMaximumSize(new Dimension(frameWidth, frameHeight - topPanelHeight));
        bottomPanel.add(new BoardElement(selectedCell, sudoku));


        mainContent.add(topPanel);
        mainContent.add(middlePanel);
        mainContent.add(bottomPanel);

        this.add(mainContent);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
