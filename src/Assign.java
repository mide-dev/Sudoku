// this class handles creation of game moves
public class Assign {
    private int col, row; // row and col being assigned
    private Sudoku thegame; // the game
    Slot[][] moves; // 2d arr to store game moves

    // constructor
    public Assign(Sudoku game, int row, int col, String number) {
        this.thegame = game;
        this.col = col;
        this.row = row;
        moves = new Slot[thegame.getGameSize()][thegame.getGameSize()];
        assignMove(number);
    }

    // assign a move to the game
    public void assignMove(String number) {
        if (moves[row][col] == null) {
            moves[row][col] = new Slot(col, row, number); // Initialize the BoardCell if it's null
        } else {
            moves[row][col].setState(number);
        }
//        thegame.setPopulatedBoard(moves);
    }


    public int getRow() {
        return row;
    }
}
