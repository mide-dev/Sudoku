import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class provides a Graphical user interface for the player to interact with the game.
 * NOTE: This class only implements the initial welcome screen and calls the "SudokuGUILogic"
 *       class which handles game play display logic.
 *
 * @author Mide Ibraheem
 * @version v1.0
 */
class GraphicalUI implements ActionListener  {
    JFrame initialGameScreen; // JFrame window to display the welcome screen

    /**
     * Constructor of the class GraphicalUI
     * This constructor styles the welcome screen display.
     */
    public GraphicalUI() {
        int width = 400; // width for the welcome screen
        int height = 400; // height for the welcome screen

        // create new JFrame, set preferences and style.
        initialGameScreen = new JFrame("Sudoku");
        initialGameScreen.setSize(width,height);
        initialGameScreen.setLayout(new FlowLayout());
        initialGameScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initialGameScreen.setLocationRelativeTo(null);


        ImageIcon imageIcon = new ImageIcon("logo.png");
        JLabel logo = new JLabel(imageIcon);

        JLabel welcomeMessage= new JLabel("Welcome To Sudoku");
        welcomeMessage.setFont(new Font(welcomeMessage.getFont().getName(), Font.PLAIN, 18));
        welcomeMessage.setHorizontalAlignment(JLabel.CENTER);

        JLabel displayOption= new JLabel("Please Select a Mode to Start Game:");
        displayOption.setFont(new Font(welcomeMessage.getFont().getName(), Font.PLAIN, 15));
        displayOption.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        displayOption.setHorizontalAlignment(JLabel.CENTER);

        JButton easyMode = new JButton("Easy");
        JButton hardMode = new JButton("Hard");

        easyMode.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        hardMode.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        easyMode.setFont(new Font(easyMode.getFont().getName(), Font.BOLD, 14));
        hardMode.setFont(new Font(hardMode.getFont().getName(), Font.BOLD, 14));

        easyMode.addActionListener(this);
        hardMode.addActionListener(this);

        JPanel buttonGroup = new JPanel();
        buttonGroup.add(easyMode);
        buttonGroup.add(hardMode);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout (new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(width, 150));
        headerPanel.setMaximumSize(new Dimension(width, 150));
        headerPanel.add(logo, BorderLayout.NORTH);
        headerPanel.add(welcomeMessage, BorderLayout.SOUTH);

        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout (new BorderLayout());
        bodyPanel.add(displayOption, BorderLayout.NORTH);
        bodyPanel.add(buttonGroup, BorderLayout.SOUTH);
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));


        initialGameScreen.add(headerPanel);
        initialGameScreen.add(bodyPanel);
        initialGameScreen.setVisible(true);
    }

    /**
     * This method overrides the inbuilt method from ActionListener Interface
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Initialize a new Sudoku game depending on user choice
        if (e.getActionCommand().equals("Easy")) {
            new SudokuGUILogic("easy");
        } else if (e.getActionCommand().equals("Hard")) {
            new SudokuGUILogic("hard");
            System.out.println("hard");
        }

        initialGameScreen.dispose();
    }

    /**
     * The main method within the Java application.
     * It's the core method of the program and calls all others.
     */
    public static void main(String args[]) {
        new GraphicalUI();
    }
}//end of class GraphicalUI
