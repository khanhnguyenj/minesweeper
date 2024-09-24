package com.test.minesweeper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinefieldManagementTest {

  private Minefield mm;

  @Test
  public void testGrid() {
    int gridSize = 4;
    int numberOfMines = 2;
    mm = new Minefield(gridSize, numberOfMines);
    mm.displayMinefield();
    assertEquals(gridSize, mm.getGrid().length);
  }

  @Test
  public void testGenerateRandomMines() {
    int gridSize = 4;
    int numberOfMines = 2;
    mm = new Minefield(gridSize, numberOfMines);
    mm.generateRandomMines();
    int countMines = 0;
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        if (mm.getGrid()[i][j] == -1) {
          countMines++;
        }
      }
    }
    mm.displayMinefield();
    assertEquals(numberOfMines, countMines);
  }

  @Test
  public void testUpdateMinefieldAfterGeneratingRandomMines1() {
    int gridSize = 4;
    int numberOfMines = 2;
    mm = new Minefield(gridSize, numberOfMines);
    mm.getGrid()[0][3] = -1;
    mm.getGrid()[3][0] = -1;
    mm.displayMinefield();
    mm.updateMinefieldAfterGeneratingRandomMines();
    mm.displayMinefield();
    assertEquals(mm.getGrid()[1][2], 1);
    assertEquals(1, mm.getGrid()[1][3]);
    assertEquals(mm.getGrid()[2][0], 1);
    assertEquals(mm.getGrid()[2][1], 1);
    assertEquals(mm.getGrid()[3][1], 1);
  }

  @Test
  public void testUpdateMinefieldAfterGeneratingRandomMines2() {
    int gridSize = 4;
    int numberOfMines = 2;
    mm = new Minefield(gridSize, numberOfMines);
    mm.getGrid()[1][1] = -1;
    mm.getGrid()[3][0] = -1;
    mm.displayMinefield();
    mm.updateMinefieldAfterGeneratingRandomMines();
    mm.displayMinefield();
    assertEquals(1, mm.getGrid()[0][0]);
    assertEquals(1, mm.getGrid()[0][1]);
    assertEquals(1, mm.getGrid()[0][2]);
    assertEquals(1, mm.getGrid()[1][0]);
    assertEquals(1, mm.getGrid()[1][2]);
    assertEquals(2, mm.getGrid()[2][0]);
    assertEquals(2, mm.getGrid()[2][1]);
    assertEquals(1, mm.getGrid()[2][2]);
    assertEquals(1, mm.getGrid()[3][1]);
  }

  @Test
  public void testUpdateMinefieldAfterGeneratingRandomMines3() {
    int gridSize = 4;
    int numberOfMines = 3;
    mm = new Minefield(gridSize, numberOfMines);
    mm.getGrid()[0][3] = -1;
    mm.getGrid()[1][1] = -1;
    mm.getGrid()[2][1] = -1;
    mm.displayMinefield();
    mm.updateMinefieldAfterGeneratingRandomMines();
    mm.displayMinefield();
    assertEquals(1, mm.getGrid()[0][0]);
    assertEquals(1, mm.getGrid()[0][1]);
    assertEquals(2, mm.getGrid()[0][2]);
    assertEquals(2, mm.getGrid()[1][0]);
    assertEquals(3, mm.getGrid()[1][2]);
    assertEquals(1, mm.getGrid()[1][3]);
    assertEquals(2, mm.getGrid()[2][0]);
    assertEquals(2, mm.getGrid()[2][2]);
    assertEquals(1, mm.getGrid()[3][0]);
    assertEquals(1, mm.getGrid()[3][1]);
    assertEquals(1, mm.getGrid()[3][2]);
  }

  @Test
  public void testUpdateMinefieldAfterGeneratingRandomMines4() {
    int gridSize = 5;
    int numberOfMines = 3;
    mm = new Minefield(gridSize, numberOfMines);
    mm.getGrid()[1][0] = -1;
    mm.getGrid()[2][0] = -1;
    mm.getGrid()[2][1] = -1;
    mm.displayMinefield();
    mm.updateMinefieldAfterGeneratingRandomMines();
    mm.displayMinefield();
    assertEquals(1, mm.getGrid()[0][0]);
    assertEquals(1, mm.getGrid()[0][1]);
    assertEquals(3, mm.getGrid()[1][1]);
    assertEquals(1, mm.getGrid()[1][2]);
    assertEquals(1, mm.getGrid()[2][2]);
    assertEquals(2, mm.getGrid()[3][0]);
    assertEquals(2, mm.getGrid()[3][1]);
    assertEquals(1, mm.getGrid()[3][2]);
    checkSurrounding(mm.getGrid(), 2, 3);
  }

  private static void checkSurrounding(int[][] list, int x, int y) {
    for (int dx = -1; dx <= 1; dx++) {
      if ((x + dx >= 0) && (x + dx < list.length)) {
        for (int dy = -1; dy <= 1; dy++) {
          if ((y + dy >= 0) && (y + dy < list[x + dx].length) && (!(dx == 0 && dy == 0))) {
            System.out.println((x + dx) + "x" + (y + dy));
            System.out.println(list[x + dx][y + dy]);
            System.out.println("---");
          }
        }
      }
    }
  }

  @Test
  public void testProcessSelectedSquare() {
    int gridSize = 5;
    int numberOfMines = 3;
    mm = new Minefield(gridSize, numberOfMines);
    mm.getGrid()[1][0] = -1;
    mm.getGrid()[2][0] = -1;
    mm.getGrid()[2][1] = -1;
    mm.updateMinefieldAfterGeneratingRandomMines();
    mm.generateDisplayGrid();
//    mm.displayMinefield1();
    System.out.println("-----");
    mm.displayMinefield();
    System.out.println("-----");
    int n = mm.processSelectedSquare(new Square(2, 3));
    assertEquals(0, n);
    mm.displayMinefield();
  }

  @Test
  public void testProcessSelectedSquare1() {
    int gridSize = 4;
    int numberOfMines = 3;
    mm = new Minefield(gridSize, numberOfMines);
    mm.getGrid()[0][1] = -1;
    mm.getGrid()[1][1] = -1;
    mm.getGrid()[2][0] = -1;
    mm.updateMinefieldAfterGeneratingRandomMines();
    mm.generateDisplayGrid();
//    mm.displayMinefield1();
    System.out.println("-----");
    mm.displayMinefield();
    System.out.println("-----");
    int n = mm.processSelectedSquare(new Square(1, 0));
    assertEquals(3, n);
    mm.displayMinefield();
  }

  @Test
  public void testProcessSelectedSquare2() {
    int gridSize = 4;
    int numberOfMines = 3;
    mm = new Minefield(gridSize, numberOfMines);
    mm.getGrid()[0][1] = -1;
    mm.getGrid()[1][1] = -1;
    mm.getGrid()[2][0] = -1;
    mm.updateMinefieldAfterGeneratingRandomMines();
    mm.generateDisplayGrid();
//    mm.displayMinefield1();
    System.out.println("-----");
    mm.displayMinefield();
    System.out.println("-----");
    int n = mm.processSelectedSquare(new Square(3, 3));
    assertEquals(0, n);

    mm.displayMinefield();

    assertEquals('0', mm.getDisplayGrid()[0][3]);
    assertEquals('0', mm.getDisplayGrid()[1][3]);
    assertEquals('0', mm.getDisplayGrid()[2][3]);
    assertEquals('0', mm.getDisplayGrid()[3][3]);
    assertEquals('0', mm.getDisplayGrid()[3][2]);

    assertEquals('2', mm.getDisplayGrid()[0][2]);
    assertEquals('2', mm.getDisplayGrid()[1][2]);
    assertEquals('1', mm.getDisplayGrid()[2][2]);
    assertEquals('2', mm.getDisplayGrid()[2][1]);
    assertEquals('1', mm.getDisplayGrid()[3][1]);
  }

  @Test
  public void testProcessSelectedSquare3() {
    int gridSize = 4;
    int numberOfMines = 3;
    mm = new Minefield(gridSize, numberOfMines);
    mm.getGrid()[0][1] = -1;
    mm.getGrid()[1][1] = -1;
    mm.getGrid()[2][0] = -1;
    mm.updateMinefieldAfterGeneratingRandomMines();
    mm.generateDisplayGrid();
//    mm.displayMinefield1();
    System.out.println("-----");
    mm.displayMinefield();
    System.out.println("-----");
    int n = mm.processSelectedSquare(new Square(0, 0));
    assertEquals(2, n);
    mm.displayMinefield();
  }

  @Test
  public void testProcessSelectedSquare4() {
    int gridSize = 4;
    int numberOfMines = 3;
    mm = new Minefield(gridSize, numberOfMines);
    mm.getGrid()[0][1] = -1;
    mm.getGrid()[1][1] = -1;
    mm.getGrid()[2][0] = -1;
    mm.updateMinefieldAfterGeneratingRandomMines();
    mm.generateDisplayGrid();
//    mm.displayMinefield1();
    System.out.println("-----");
    mm.displayMinefield();
    System.out.println("-----");
    int n = mm.processSelectedSquare(new Square(3, 0));
    assertEquals(1, n);
    mm.displayMinefield();
  }
}