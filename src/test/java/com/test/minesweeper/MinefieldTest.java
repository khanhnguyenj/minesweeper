package com.test.minesweeper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinefieldTest {

  private Minefield minefield;

  @Test
  public void testGrid() {
    int gridSize = 4;
    int numberOfMines = 2;
    minefield = new Minefield(gridSize, numberOfMines);
    minefield.displayMinefield();

    assertEquals(gridSize, minefield.getGrid().length);
  }

  @Test
  public void testGenerateRandomMines() {
    int gridSize = 4;
    int numberOfMines = 2;
    minefield = new Minefield(gridSize, numberOfMines);
    minefield.generateRandomMines();

    minefield.displayMinefield();

    int countMines = 0;
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        if (minefield.getGrid()[i][j] == -1) {
          countMines++;
        }
      }
    }

    assertEquals(numberOfMines, countMines);
  }

  @Test
  public void testUpdateMinefieldAfterGeneratingRandomMines1() {
    int gridSize = 4;
    int numberOfMines = 2;
    minefield = new Minefield(gridSize, numberOfMines);
    minefield.getGrid()[0][3] = -1;
    minefield.getGrid()[3][0] = -1;

    minefield.displayMinefield();
    minefield.updateMinefieldAfterGeneratingRandomMines();
    minefield.displayMinefield();

    assertEquals(minefield.getGrid()[1][2], 1);
    assertEquals(1, minefield.getGrid()[1][3]);
    assertEquals(minefield.getGrid()[2][0], 1);
    assertEquals(minefield.getGrid()[2][1], 1);
    assertEquals(minefield.getGrid()[3][1], 1);
  }

  @Test
  public void testUpdateMinefieldAfterGeneratingRandomMines2() {
    int gridSize = 4;
    int numberOfMines = 2;
    minefield = new Minefield(gridSize, numberOfMines);
    minefield.getGrid()[1][1] = -1;
    minefield.getGrid()[3][0] = -1;

    minefield.displayMinefield();
    minefield.updateMinefieldAfterGeneratingRandomMines();
    minefield.displayMinefield();

    assertEquals(1, minefield.getGrid()[0][0]);
    assertEquals(1, minefield.getGrid()[0][1]);
    assertEquals(1, minefield.getGrid()[0][2]);
    assertEquals(1, minefield.getGrid()[1][0]);
    assertEquals(1, minefield.getGrid()[1][2]);
    assertEquals(2, minefield.getGrid()[2][0]);
    assertEquals(2, minefield.getGrid()[2][1]);
    assertEquals(1, minefield.getGrid()[2][2]);
    assertEquals(1, minefield.getGrid()[3][1]);
  }

  @Test
  public void testUpdateMinefieldAfterGeneratingRandomMines3() {
    int gridSize = 4;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);
    minefield.getGrid()[0][3] = -1;
    minefield.getGrid()[1][1] = -1;
    minefield.getGrid()[2][1] = -1;

    minefield.displayMinefield();
    minefield.updateMinefieldAfterGeneratingRandomMines();
    minefield.displayMinefield();

    assertEquals(1, minefield.getGrid()[0][0]);
    assertEquals(1, minefield.getGrid()[0][1]);
    assertEquals(2, minefield.getGrid()[0][2]);
    assertEquals(2, minefield.getGrid()[1][0]);
    assertEquals(3, minefield.getGrid()[1][2]);
    assertEquals(1, minefield.getGrid()[1][3]);
    assertEquals(2, minefield.getGrid()[2][0]);
    assertEquals(2, minefield.getGrid()[2][2]);
    assertEquals(1, minefield.getGrid()[3][0]);
    assertEquals(1, minefield.getGrid()[3][1]);
    assertEquals(1, minefield.getGrid()[3][2]);
  }

  @Test
  public void testUpdateMinefieldAfterGeneratingRandomMines4() {
    int gridSize = 5;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);
    minefield.getGrid()[1][0] = -1;
    minefield.getGrid()[2][0] = -1;
    minefield.getGrid()[2][1] = -1;

    minefield.displayMinefield();
    minefield.updateMinefieldAfterGeneratingRandomMines();
    minefield.displayMinefield();

    assertEquals(1, minefield.getGrid()[0][0]);
    assertEquals(1, minefield.getGrid()[0][1]);
    assertEquals(3, minefield.getGrid()[1][1]);
    assertEquals(1, minefield.getGrid()[1][2]);
    assertEquals(1, minefield.getGrid()[2][2]);
    assertEquals(2, minefield.getGrid()[3][0]);
    assertEquals(2, minefield.getGrid()[3][1]);
    assertEquals(1, minefield.getGrid()[3][2]);
  }

  @Test
  public void testGenerateDisplayGrid() {
    int gridSize = 5;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);

    minefield.generateDisplayGrid();

    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        assertEquals('_', minefield.getDisplayGrid()[i][j]);
      }
    }
  }

  @Test
  public void testInitialize() {
    int gridSize = 5;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);

    minefield.initialize();

    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        assertEquals('_', minefield.getDisplayGrid()[i][j]);
      }
    }

    int countMines = 0;
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        if (minefield.getGrid()[i][j] == -1) {
          countMines++;
        }
      }
    }

    assertEquals(numberOfMines, countMines);
  }

  @Test
  public void testProcessSelectedSquare() {
    int gridSize = 5;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);
    minefield.getGrid()[1][0] = -1;
    minefield.getGrid()[2][0] = -1;
    minefield.getGrid()[2][1] = -1;

    minefield.updateMinefieldAfterGeneratingRandomMines();
    minefield.generateDisplayGrid();

    minefield.displayMinefield();
    int n = minefield.processSelectedSquare(new Square(2, 3));
    minefield.displayMinefield();

    assertEquals(0, n);
  }

  @Test
  public void testProcessSelectedSquare1() {
    int gridSize = 4;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);
    minefield.getGrid()[0][1] = -1;
    minefield.getGrid()[1][1] = -1;
    minefield.getGrid()[2][0] = -1;

    minefield.updateMinefieldAfterGeneratingRandomMines();

    minefield.generateDisplayGrid();

    minefield.displayMinefield();
    int n = minefield.processSelectedSquare(new Square(1, 0));
    minefield.displayMinefield();

    assertEquals(3, n);
  }

  @Test
  public void testProcessSelectedSquare2() {
    int gridSize = 4;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);
    minefield.getGrid()[0][1] = -1;
    minefield.getGrid()[1][1] = -1;
    minefield.getGrid()[2][0] = -1;

    minefield.updateMinefieldAfterGeneratingRandomMines();
    minefield.generateDisplayGrid();

    minefield.displayMinefield();
    int n = minefield.processSelectedSquare(new Square(3, 3));
    minefield.displayMinefield();

    assertEquals(0, n);

    assertEquals('0', minefield.getDisplayGrid()[0][3]);
    assertEquals('0', minefield.getDisplayGrid()[1][3]);
    assertEquals('0', minefield.getDisplayGrid()[2][3]);
    assertEquals('0', minefield.getDisplayGrid()[3][3]);
    assertEquals('0', minefield.getDisplayGrid()[3][2]);

    assertEquals('2', minefield.getDisplayGrid()[0][2]);
    assertEquals('2', minefield.getDisplayGrid()[1][2]);
    assertEquals('1', minefield.getDisplayGrid()[2][2]);
    assertEquals('2', minefield.getDisplayGrid()[2][1]);
    assertEquals('1', minefield.getDisplayGrid()[3][1]);
  }

  @Test
  public void testProcessSelectedSquare3() {
    int gridSize = 4;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);
    minefield.getGrid()[0][1] = -1;
    minefield.getGrid()[1][1] = -1;
    minefield.getGrid()[2][0] = -1;

    minefield.updateMinefieldAfterGeneratingRandomMines();
    minefield.generateDisplayGrid();

    minefield.displayMinefield();
    int n = minefield.processSelectedSquare(new Square(0, 0));
    minefield.displayMinefield();

    assertEquals(2, n);
  }

  @Test
  public void testProcessSelectedSquare4() {
    int gridSize = 4;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);
    minefield.getGrid()[0][1] = -1;
    minefield.getGrid()[1][1] = -1;
    minefield.getGrid()[2][0] = -1;

    minefield.updateMinefieldAfterGeneratingRandomMines();
    minefield.generateDisplayGrid();

    minefield.displayMinefield();
    int n = minefield.processSelectedSquare(new Square(3, 0));
    minefield.displayMinefield();

    assertEquals(1, n);
  }

  @Test
  public void testCountRemainingMines() {
    int gridSize = 4;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);
    minefield.getGrid()[0][1] = -1;
    minefield.getGrid()[1][1] = -1;
    minefield.getGrid()[2][0] = -1;

    minefield.updateMinefieldAfterGeneratingRandomMines();
    minefield.generateDisplayGrid();

    minefield.displayMinefield();
    minefield.processSelectedSquare(new Square(0, 0));
    minefield.displayMinefield();

    int n = minefield.countRemainingMines();

    assertEquals(15, n);
  }
}