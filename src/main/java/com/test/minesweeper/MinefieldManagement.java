package com.test.minesweeper;

import java.util.Random;
import java.util.Set;

public class MinefieldManagement {

  char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
  private final int[][] grid;
  private final int gridSize;
  private final int numberOfMines;

  public MinefieldManagement(int gridSize, int numberOfMines) {
    this.gridSize = gridSize;
    this.numberOfMines = numberOfMines;
    this.grid = new int[this.gridSize][this.gridSize];
  }

  public void initialize() {
    generateRandomMines();
    updateMinefieldAfterGeneratingRandomMines();
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

  public void displayMinefield(Set<Square> selectedSquares) {
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
}