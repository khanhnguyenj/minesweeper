package com.test.minesweeper;

import static com.test.minesweeper.Minesweeper.ALPHABET;

import com.test.minesweeper.exception.DetonatedMinesException;
import java.util.Random;

/**
 * Class represents for a minefield.
 */
public final class Minefield {

  private final int[][] grid;
  private final int gridSize;
  private final int numberOfMines;
  private final char[][] displayGrid;

  /**
   * Constructor.
   *
   * @param gridSize      Size of grid for the minefield.
   * @param numberOfMines Number of mines in the minefield.
   */
  public Minefield(final int gridSize, final int numberOfMines) {
    this.gridSize = gridSize;
    this.numberOfMines = numberOfMines;
    this.grid = new int[this.gridSize][this.gridSize];
    this.displayGrid = new char[this.gridSize][this.gridSize];
  }

  /**
   * Initialize random mines base on the number of mines and update the minefield.
   */
  public void initialize() {
    generateRandomMines();
    updateMinefieldAfterGeneratingRandomMines();
    generateDisplayGrid();
  }

  /**
   * Display the minefield.
   */
  public void generateDisplayGrid() {
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        displayGrid[i][j] = '_';
      }
    }
  }

  /**
   * Update minefield after generating the random mines.
   */
  public void updateMinefieldAfterGeneratingRandomMines() {
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        int value = grid[i][j];

        if (value == -1) {
          continue;
        }

        grid[i][j] = countNumberOfMinesSurrounding(i, j);
      }
    }
  }

  private int countNumberOfMinesSurrounding(final int xPos, final int yPos) {
    int numberOfMinesSurrounding = 0;

    if (xPos >= 1 && yPos >= 1 && grid[xPos - 1][yPos - 1] == -1) {
      numberOfMinesSurrounding++;
    }
    if (xPos >= 1 && grid[xPos - 1][yPos] == -1) {
      numberOfMinesSurrounding++;
    }
    if (xPos >= 1 && yPos + 1 < gridSize && grid[xPos - 1][yPos + 1] == -1) {
      numberOfMinesSurrounding++;
    }
    if (yPos + 1 < gridSize && grid[xPos][yPos + 1] == -1) {
      numberOfMinesSurrounding++;
    }
    if (xPos + 1 < gridSize && yPos + 1 < gridSize && grid[xPos + 1][yPos + 1] == -1) {
      numberOfMinesSurrounding++;
    }
    if (xPos + 1 < gridSize && grid[xPos + 1][yPos] == -1) {
      numberOfMinesSurrounding++;
    }
    if (yPos >= 1 && (xPos + 1) < gridSize && grid[xPos + 1][yPos - 1] == -1) {
      numberOfMinesSurrounding++;
    }
    if (yPos >= 1 && grid[xPos][yPos - 1] == -1) {
      numberOfMinesSurrounding++;
    }

    return numberOfMinesSurrounding;
  }

  /**
   * Generate random mines.
   */
  public void generateRandomMines() {
    Random random = new Random();

    for (int i = 0; i < numberOfMines; i++) {
      int j = random.nextInt(gridSize);
      int k = random.nextInt(gridSize);

      while (grid[j][k] == -1) {
        j = random.nextInt(gridSize);
        k = random.nextInt(gridSize);
      }

      grid[j][k] = -1;
    }
  }

  /**
   * Display the minefield.
   */
  public void displayMinefield() {
    for (int i = 0; i <= gridSize; i++) {
      if (i == 0) {
        System.out.print("  ");
      } else {
        System.out.print(" " + i);
      }
    }

    System.out.println("");

    for (int i = 0; i < gridSize; i++) {
      System.out.print(" " + ALPHABET[i]);

      for (int j = 0; j < gridSize; j++) {
        System.out.print(" " + displayGrid[i][j]);
      }

      System.out.println("");
    }
  }

  /**
   * Process selected square after user chooses a square.
   *
   * @param selectedSquare The {@link Square} which was selected.
   * @return Number of adjacent mines if any
   */
  public int processSelectedSquare(final Square selectedSquare) {
    int selectedX = selectedSquare.xPos();
    int selectedY = selectedSquare.yPos();

    if (grid[selectedX][selectedY] == -1) {
      throw new DetonatedMinesException("Mines detonated!");
    }

    if (grid[selectedX][selectedY] != 0) {
      int selectedValue = grid[selectedX][selectedY];
      displayGrid[selectedX][selectedY] = (char) (selectedValue + '0');

      return grid[selectedX][selectedY];
    }

    if (grid[selectedX][selectedY] == 0) {
      displayGrid[selectedX][selectedY] = '0';

      for (int i = selectedX; i >= 0; i--) {
        for (int j = selectedY; j >= 0; j--) {
          if (grid[i][j] == 0) {
            displayGrid[i][j] = '0';

            revealSurrounding(i, j);
          }
        }
      }

      for (int i = selectedX; i >= 0; i--) {
        for (int j = selectedY + 1; j < grid.length; j++) {
          if (grid[i][j] == 0) {
            displayGrid[i][j] = '0';

            revealSurrounding(i, j);
          }
        }
      }

      for (int i = selectedX + 1; i < grid.length; i++) {
        for (int j = selectedY; j < grid.length; j++) {
          if (grid[i][j] == 0) {
            displayGrid[i][j] = '0';

            revealSurrounding(i, j);
          }
        }
      }

      for (int i = selectedX + 1; i < grid.length; i++) {
        for (int j = selectedY - 1; j >= 0; j--) {
          if (grid[i][j] == 0) {
            displayGrid[i][j] = '0';

            revealSurrounding(i, j);
          }
        }
      }
    }

    return 0;
  }

  private void revealSurrounding(final int xPos, final int yPos) {
    if (xPos >= 1 && yPos >= 1 && displayGrid[xPos - 1][yPos - 1] == '_') {
      int v = grid[xPos - 1][yPos - 1];

      displayGrid[xPos - 1][yPos - 1] = (char) (v + '0');
    }
    if (xPos >= 1 && displayGrid[xPos - 1][yPos] == '_') {
      int v = grid[xPos - 1][yPos];

      displayGrid[xPos - 1][yPos] = (char) (v + '0');
    }
    if (xPos >= 1 && yPos + 1 < gridSize && displayGrid[xPos - 1][yPos + 1] == '_') {
      int v = grid[xPos - 1][yPos + 1];

      displayGrid[xPos - 1][yPos + 1] = (char) (v + '0');
    }
    if (yPos + 1 < gridSize && displayGrid[xPos][yPos + 1] == '_') {
      int v = grid[xPos][yPos + 1];

      displayGrid[xPos][yPos + 1] = (char) (v + '0');
    }
    if (xPos + 1 < gridSize && yPos + 1 < gridSize && displayGrid[xPos + 1][yPos + 1] == '_') {
      int v = grid[xPos + 1][yPos + 1];

      displayGrid[xPos + 1][yPos + 1] = (char) (v + '0');
    }
    if (xPos + 1 < gridSize && displayGrid[xPos + 1][yPos] == '_') {
      int v = grid[xPos + 1][yPos];

      displayGrid[xPos + 1][yPos] = (char) (v + '0');
    }
    if (yPos >= 1 && (xPos + 1) < gridSize && displayGrid[xPos + 1][yPos - 1] == '_') {
      int v = grid[xPos + 1][yPos - 1];

      displayGrid[xPos + 1][yPos - 1] = (char) (v + '0');
    }
    if (yPos >= 1 && displayGrid[xPos][yPos - 1] == '_') {
      int v = grid[xPos][yPos - 1];

      displayGrid[xPos][yPos - 1] = (char) (v + '0');
    }
  }

  /**
   * Count remaining mines after user chooses a square.
   *
   * @return The remaining mines.
   */
  public int countRemainingMines() {
    int remainingMines = 0;

    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        if (displayGrid[i][j] == '_') {
          remainingMines++;
        }
      }
    }

    return remainingMines;
  }

  /**
   * Grid contains the actual value of the minefield.
   *
   * @return Array of actual value of the minefield.
   */
  public int[][] getGrid() {
    return grid;
  }

  /**
   * Grid will be used to display the minefield to a user.
   *
   * @return Array of value will be displayed to a user.
   */
  public char[][] getDisplayGrid() {
    return displayGrid;
  }
}