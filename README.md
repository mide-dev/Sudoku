# Sudoku Game Architecture Documentation

This documentation provides a comprehensive overview of the architectural framework of a Sudoku game project. It outlines the modular design approach, class roles and interactions, key design decisions, and the implementation of various design patterns. The objective is to offer insights into the developmental thought process behind the project.

<p float="left">
  <img src="1.png" width="400" height="450">
  <img src="2.png" width="400" height="450">
</p>

![Sudoku UML](main.png)


## Overview

The codebase consists of twelve (11) classes, with `Slot`, `Sudoku`, and `UI` responsible for the core functionality, and additional classes including `Operations`, `DisplayMessage`, `ButtonElement`, `ButtonElementGroup`, `CellElement`, `CellElementGroup`, `BoardElement`, `SudokuGUILogic`, and `GraphicalUI` were added to improve gameplay experience.

## Class Functionality

### Sudoku Class

The core logic of the game is encapsulated within the `Sudoku` Class. It manages the game's state, initializes the board based on the selected difficulty level (easy or hard), and provides methods for making moves, validating inputs, and determining win conditions.

**Modifications:** The constructor now accepts a `mode` parameter to determine the board's difficulty level (4x4 or 9x9). New methods, such as `updateMoves`, have been added to track player moves.

### Slot Class

Originally designed to represent a single cell on the grid, the `Slot` Class manages each cell's value and state (fillable or fixed). It extends the `Observable` class, enabling notifications to the `GraphicalUI` class upon changes.

**Modifications:** The `setState` method now notifies observers after a state change, ensuring updates are communicated.

### Operations Class

This class handles additional game actions beyond the core logic, including saving and loading game states, undoing moves, and resetting the game board.

### UI Class

Serving as the text-based interface, the `UI` Class facilitates player interaction with the game through the command prompt, interacting directly with the `Sudoku` and `Operations` classes.

**Modifications:** The constructor has been updated to accept a game mode preference from the user, which is then passed to the `Sudoku` Class.

### DisplayMessage Class

Aimed at enhancing user experience, this class manages the creation and handling of popup messages, informing players of game states, errors, and confirmations.

### CellElement Class

Representing the GUI counterpart of the `Slot` class, `CellElement` extends `JTextField` to display and allow modifications to cell values.

### CellElementGroup Class

This class groups `CellElement` instances into sub-grids for both 9x9 and 4x4 board configurations, extending `JPanel` for organization.

### ButtonElement Class

`ButtonElement` extends `JButton` to manage user interactions related to game actions and numeric inputs, with constructor overloading for different button functionalities.

### ButtonElementGroup Class

This class manages groups of `ButtonElement` instances, creating distinct sets for game functionalities and numerical inputs.

### BoardElement Class

Responsible for displaying the entire Sudoku board, this class organizes the grid by arranging `CellElementGroup` instances.

### SudokuGUILogic Class

Extending JFrame, this class manages the main application window, integrating the various GUI components into a functional layout.

### GraphicalUI Class

The entry point for the graphical user interface, this class displays the welcome screen and facilitates game mode selection, transitioning users to the main game interface.
