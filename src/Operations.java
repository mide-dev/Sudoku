import java.io.*;
import java.util.Scanner;

/**
 * This class implements game actions logic (save, undo, clear, load)
 * @author Mide Ibraheem
 * @version 1.0
 */
public class Operations {
    private FileWriter writer; // This writer is used to write game state to file
    private File file; // This is used to locate the address of a file
    private Sudoku thegame; // This represents the Sudoku class
    private String levelFile; // This represents the "getLevelFile" method in Sudoku class

    /**
     * Constructor of the operation class
     * @param sudoku - takes in the Sudoku class as a parameter
     */
    public Operations(Sudoku sudoku) {
        thegame = sudoku;
        levelFile = thegame.getLevelFile();
    }

    /**
     * This method determines the correct file to access depending on gameSize
     * @return the file path
     */
    public File filePath() {
        String filePath;
        File file;

        if (thegame.getGameSize() == 4) {
            filePath = "SavedGame/saved_easy.txt";
            file = new File(filePath);
            return file;
        } else { // if getGameSize is not 4, then its 9
            filePath = "SavedGame/saved_hard.txt";
            file = new File(filePath);
            return file;
        }
    }

    /**
     * This method implements the logic of saving a game state
     */
    public void saveGame() {
        file = filePath(); // get the file path
        Slot[][] currentGameState = thegame.getMoves(); // access current state of the populated board

        try {
            writer = new FileWriter(file);
            writer.write(thegame.getGameSize() + "\n");

            for (int row = 0; row < thegame.getGameSize(); row++) {
                for (int col = 0; col < thegame.getGameSize(); col++) {
                    Slot cell = currentGameState[row][col];
                    String state = cell.getState(); // get state returns the value of the slot
                    writer.write(row + " " + col + " " + state + "\n"); // write each slot to file
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { // try block to close the writer
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method implements the logic of loading a game from file
     */
    public void loadGame() {
        Scanner reader = null;

        file = filePath();
        try {
            reader = new Scanner(file);
            Integer.parseInt(reader.next()); // to get past the first value in the file (game Size)

            while (reader.hasNext()) {
                int row = Integer.parseInt(reader.next());
                int col = Integer.parseInt(reader.next());
                String value = reader.next();

                thegame.getMoves()[row][col].setState(value); // thegame.getMoves()[row][col] == populatedBoard[row][col]
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

    }

    /**
     * This method implements the logic of undoing a user move
     */
    public boolean undoMove() {
        if (thegame.getMovesMade().isEmpty()) {
            return false;
        } else {
            Slot lastModifiedSlot = thegame.getMovesMade().remove(thegame.getMovesMade().size() - 1); // thegame.getMovesMade() == user moves arraylist
            lastModifiedSlot.resetSlot(); // since each element in our arraylist a Slot, we can call slot methods on them
            return true;
        }
    }

    /**
     * This method implements the logic of clearing the board
     */
    public void clearGame() {
        Scanner reader;
        file = new File(this.levelFile);

        try {
            reader = new Scanner(file);
            reader.next(); // to get past the first value in the file (game Size)
            while (reader.hasNext()) {
                int row = Integer.parseInt(reader.next());
                int col = Integer.parseInt(reader.next());
                String value = reader.next();

                thegame.getMoves()[row][col].setState(value); // thegame.getMoves()[row][col] == populatedBoard[row][col]
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
} // end of Class Operations