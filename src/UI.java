import java.util.Scanner;

/**
 * This class provides a text based user interface for the player to interact with the game
 * @author Lauren Scott
 * @version student sample code
 */
public class UI {
    private Sudoku thegame; //this is the game model
    private String menuChoice; //this is the users choice from the menu
    private Scanner reader; //this scanner is used to read the terminal
    private Operations gameOperations; //this is an instance of the Operations class

    /**
     * Constructor for the class UI
     * Modified constructor to allow user decide which gameSize to play
     */
    public UI() {

        reader = new Scanner(System.in);
        boolean validUserChoice = false;
        String userModeChoice;

        System.out.println();
        System.out.println("Please Select a Mode to Start Game:");
        System.out.println("[1] Easy Mode");
        System.out.println("[2] Hard Mode");

        while(!validUserChoice) {
            userModeChoice = reader.next();
            if (userModeChoice.equals("1")) {
                thegame = new Sudoku("easy");
                validUserChoice = true;
            } else if (userModeChoice.equals("2")) {
                thegame = new Sudoku("hard");
                validUserChoice = true;
            } else {
                System.out.println("Wrong Input (Enter 1 or 2):");
            }
        }

        gameOperations = new Operations(thegame);
        menuChoice = "";

        while(!menuChoice.equalsIgnoreCase(("Q")) && !thegame.checkWin()) {
            displayGame();
            menu();
            menuChoice = getChoice();
        }

        if (thegame.checkWin()) {
            winningAnnouncement();
        }
    }

    /**
     * Method that outputs an announcement when the user has won the game
     */
    public void winningAnnouncement() {
        System.out.println("Congratulations, you solved the puzzle");
    }

    /**
     * Method that displays the game for the user to play
     */
    public void displayGame() {
        //boardmoves = thegame.getMoves();
        if (thegame.getGameSize() == 9) {
            System.out.println("Col   0 1 2 3 4 5 6 7 8");
            System.out.println("      - - - - - - - - -");
        } else {
            System.out.println("Col   0 1 2 3 ");
            System.out.println("      - - - - ");
        }

        for (int i = 0; i < thegame.getGameSize(); i++) {
            System.out.print("Row "+i+"|");
            for (int c = 0; c < thegame.getGameSize(); c++) {
                if (thegame.getGameSize() == 9) {
                    if (c == 2 || c == 5 || c == 8) {
                        if (thegame.getIndividualMove(i,c).contains("-") ){
                            System.out.print(" " + "|");
                        } else{
                            System.out.print(thegame.getIndividualMove(i,c) + "|");
                        }
                    } else {
                        if (thegame.getIndividualMove(i,c).contains("-") ){
                            System.out.print(" " + ".");
                        } else{
                            System.out.print(thegame.getIndividualMove(i,c) + ".");
                        }
                    }

                } else if (thegame.getGameSize() == 4) {
                    if (c == 1 || c == 3) {
                        if (thegame.getIndividualMove(i,c).contains("-") ){
                            System.out.print(" " + "|");
                        } else{
                            System.out.print(thegame.getIndividualMove(i,c) + "|");
                        }
                    } else {
                        if (thegame.getIndividualMove(i,c).contains("-") ){
                            System.out.print(" " + ".");
                        } else{
                            System.out.print(thegame.getIndividualMove(i,c) + ".");
                        }
                    }


                }
            }if (thegame.getGameSize() == 9 && (i == 2 || i == 5|| i == 8)) {
                System.out.println("\n      - - - - - - - - -");

            } else if (thegame.getGameSize() == 9 ){
                System.out.println("\n      .................");

            } else if (thegame.getGameSize() == 4 && (i==1||i==3) ){
                System.out.println("\n      - - - - ");

            } else {
                System.out.println("\n     .........");
            }
        }
    }

    /**
     * Method that displays the menu to the user
     */
    public void menu() {

        System.out.println("Please select an option: \n"
                + "[M] make move\n"
                + "[S] save game\n"
                + "[L] load saved game\n"
                + "[U] undo move\n"
                + "[C] clear game\n"
                + "[Q] quit game\n");
    }

    /**
     * Method that gets the user's choice from the menu and conducts the activities
     * accordingly
     * @return the choice the user has selected
     *
     */
    public String getChoice() {
        String choice = reader.next();
        if (choice.equalsIgnoreCase("M")) {
            System.out.print("Which row is the cell you wish to fill?  ");
            String row = reader.next();
            System.out.print("Which colum is the cell you wish to fill?  ");
            String col = reader.next();
            System.out.print("Which number do you want to enter?  ");
            String number = reader.next();
            if(!thegame.makeMove(row, col, number)) {
                System.out.println("That cell cannot be changed");
                while (!thegame.makeMove(row, col, number)) {
                    System.out.print("Which row is the cell you wish to fill?  ");
                    row = reader.next();
                    System.out.print("Which colum is the cell you wish to fill?  ");
                    col = reader.next();
                    System.out.print("Which number do you want to enter?  ");
                    number = reader.next();
                }thegame.makeMove(row, col, number);
            }

        } else if (choice.equalsIgnoreCase("S")) {
            saveGame();
        } else if (choice.equalsIgnoreCase("U")) {
            undoMove();
        } else if (choice.equalsIgnoreCase("L")) {
            loadGame();
        } else if (choice.equalsIgnoreCase("C")) {
            clearGame();
        } else if (choice.equalsIgnoreCase("Q")) {
            System.exit(0);
        }
        return choice;
    }

    /**
     * This method calls the external saveGame Implementation
     */
    public void saveGame() {
        gameOperations.saveGame();
        System.out.println();
        System.out.println("************************");
        System.out.println("Game saved successfully!");
        System.out.println("************************");
        System.out.println();
    }

    /**
     * This method calls the external undoMove Implementation
     */
    public void undoMove() {
        if (gameOperations.undoMove()) {
        System.out.println("***************");
        System.out.println("You Undo a Move");
        System.out.println("***************");
        } else {
            System.out.println("**********************");
            System.out.println("No prior moves to undo");
            System.out.println("**********************");
        }

    }

    /**
     * This method calls the external loadGame Implementation
     */
    public void loadGame() {
        gameOperations.loadGame();
        System.out.println();
        System.out.println("************************");
        System.out.println("Game Loaded successfully!");
        System.out.println("************************");
        System.out.println();
    }

    /**
     * This method calls the external clearGame Implementation
     */
    public void clearGame() {
        gameOperations.clearGame();
        System.out.println();
        System.out.println("*************");
        System.out.println("Game Cleared!");
        System.out.println("*************");
        System.out.println();
    }

    /**
     * The main method within the Java application.
     * It's the core method of the program and calls all others.
     */
    public static void main(String args[]) {
        UI thisUI = new UI();
    }
}//end of class UI