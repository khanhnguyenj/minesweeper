package com.test.minesweeper;

import java.util.Random;
import java.util.Set;

public class Minefield {

  char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
  private final int[][] grid;
  private final int gridSize;
  private final int numberOfMines;
  private final char[][] displayGrid;

  public Minefield(int gridSize, int numberOfMines) {
    this.gridSize = gridSize;
    this.numberOfMines = numberOfMines;
    this.grid = new int[this.gridSize][this.gridSize];
    this.displayGrid = new char[this.gridSize][this.gridSize];
  }

  public void initialize() {
    generateRandomMines();
    updateMinefieldAfterGeneratingRandomMines();
    generateDisplayGrid();
  }

  public void generateDisplayGrid() {
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        displayGrid[i][j] = '_';
      }
    }
  }

  public void updateMinefieldAfterGeneratingRandomMines() {
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        int value = grid[i][j];
        if (value == -1) {
          continue;
        }
        int count = 0;
        if (i >= 1 && j >= 1 && grid[i - 1][j - 1] == -1) {
          count++;
        }
        if (i >= 1 && grid[i - 1][j] == -1) {
          count++;
        }
        if (i >= 1 && j + 1 < gridSize && grid[i - 1][j + 1] == -1) {
          count++;
        }
        if (j + 1 < gridSize && grid[i][j + 1] == -1) {
          count++;
        }
        if (i + 1 < gridSize && j + 1 < gridSize && grid[i + 1][j + 1] == -1) {
          count++;
        }
        if (i + 1 < gridSize && grid[i + 1][j] == -1) {
          count++;
        }
        if (j >= 1 && (i + 1) < gridSize && grid[i + 1][j - 1] == -1) {
          count++;
        }
        if (j >= 1 && grid[i][j - 1] == -1) {
          count++;
        }
        grid[i][j] = count;
      }
    }
  }

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
      System.out.print(" " + alphabet[i]);
      for (int j = 0; j < gridSize; j++) {
        System.out.print(" " + displayGrid[i][j]);
      }
      System.out.println("");
    }
  }

  public void displayMinefield1(Set<Square> selectedSquares) {
    for (int i = 0; i <= gridSize; i++) {
      if (i == 0) {
        System.out.print("  ");
      } else {
        System.out.print(" " + i);
      }
    }
    System.out.println("");
    for (int i = 0; i < gridSize; i++) {
      System.out.print(" " + alphabet[i]);
      for (int j = 0; j < gridSize; j++) {
        System.out.print(" " + grid[i][j]);
      }
      System.out.println("");
    }
  }

  public int[][] getGrid() {
    return grid;
  }

  public char[][] getDisplayGrid() {
    return displayGrid;
  }

  public int processSelectedSquare(Square selectedSquare) {
    int numberOfAdjacentMines = 0;
    int selectedX = selectedSquare.xPos();
    int selectedY = selectedSquare.yPos();
    if (grid[selectedX][selectedY] == -1) {
      throw new RuntimeException("Select bom");
    }
    if (grid[selectedX][selectedY] != 0) {
      int v = grid[selectedX][selectedY];
      displayGrid[selectedX][selectedY] = (char) (v + '0');

      return grid[selectedX][selectedY];
    }
    if (grid[selectedX][selectedY] == 0) {
      displayGrid[selectedX][selectedY] = '0';
      System.out.println("----");
      for (int i = selectedX; i >= 0; i--) {
        for (int j = selectedY; j >= 0; j--) {
          //          System.out.println(grid[i][j]);
          if (grid[i][j] == 0) {
            displayGrid[i][j] = '0';

            extracted(i, j);
          }

        }
      }
      for (int i = selectedX; i >= 0; i--) {
        for (int j = selectedY + 1; j < grid.length; j++) {
          if (grid[i][j] == 0) {
            displayGrid[i][j] = '0';

            extracted(i, j);
          }

        }
      }
      for (int i = selectedX + 1; i < grid.length; i++) {
        for (int j = selectedY; j < grid.length; j++) {
          if (grid[i][j] == 0) {
            displayGrid[i][j] = '0';

            extracted(i, j);
          }
        }
      }
      for (int i = selectedX + 1; i < grid.length; i++) {
        for (int j = selectedY - 1; j >= 0; j--) {
          if (grid[i][j] == 0) {
            displayGrid[i][j] = '0';

            extracted(i, j);
          }
        }
      }
    }
    return numberOfAdjacentMines;
  }

  private void extracted(int i, int j) {
    if (i >= 1 && j >= 1 && displayGrid[i - 1][j - 1] == '_') {
      int v = grid[i - 1][j - 1];

      displayGrid[i - 1][j - 1] = (char) (v + '0');
    }
    if (i >= 1 && displayGrid[i - 1][j] == '_') {
      int v = grid[i - 1][j];

      displayGrid[i - 1][j] = (char) (v + '0');
    }
    if (i >= 1 && j + 1 < gridSize && displayGrid[i - 1][j + 1] == '_') {
      int v = grid[i - 1][j + 1];

      displayGrid[i - 1][j + 1] = (char) (v + '0');
    }
    if (j + 1 < gridSize && displayGrid[i][j + 1] == '_') {
      int v = grid[i][j + 1];

      displayGrid[i][j + 1] = (char) (v + '0');
    }
    if (i + 1 < gridSize && j + 1 < gridSize && displayGrid[i + 1][j + 1] == '_') {
      int v = grid[i + 1][j + 1];

      displayGrid[i + 1][j + 1] = (char) (v + '0');
    }
    if (i + 1 < gridSize && displayGrid[i + 1][j] == '_') {
      int v = grid[i + 1][j];

      displayGrid[i + 1][j] = (char) (v + '0');
    }
    if (j >= 1 && (i + 1) < gridSize && displayGrid[i + 1][j - 1] == '_') {
      int v = grid[i + 1][j - 1];

      displayGrid[i + 1][j - 1] = (char) (v + '0');
    }
    if (j >= 1 && displayGrid[i][j - 1] == '_') {
      int v = grid[i][j - 1];

      displayGrid[i][j - 1] = (char) (v + '0');
    }
  }

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
}