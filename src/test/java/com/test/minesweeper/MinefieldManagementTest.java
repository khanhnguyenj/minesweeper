package com.test.minesweeper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import org.junit.jupiter.api.Test;

public class MinefieldManagementTest {

  private MinefieldManagement mm;

  @Test
  public void testGrid() {
    int gridSize = 4;
    int numberOfMines = 2;
    mm = new MinefieldManagement(gridSize, numberOfMines);
    mm.displayMinefield(new HashSet<>());
    assertEquals(gridSize, mm.getGrid().length);
  }

  @Test
  public void testGenerateRandomMines() {
    int gridSize = 4;
    int numberOfMines = 2;
    mm = new MinefieldManagement(gridSize, numberOfMines);
    mm.generateRandomMines();
    int countMines = 0;
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        if (mm.getGrid()[i][j] == -1) {
          countMines++;
        }
      }
    }
    mm.displayMinefield(new HashSet<>());
    assertEquals(numberOfMines, countMines);
  }

  @Test
  public void testUpdateMinefieldAfterGeneratingRandomMines1() {
    int gridSize = 4;
    int numberOfMines = 2;
    mm = new MinefieldManagement(gridSize, numberOfMines);
    mm.getGrid()[0][3] = -1;
    mm.getGrid()[3][0] = -1;
    mm.displayMinefield(new HashSet<>());
    mm.updateMinefieldAfterGeneratingRandomMines();
    mm.displayMinefield(new HashSet<>());
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
    mm = new MinefieldManagement(gridSize, numberOfMines);
    mm.getGrid()[1][1] = -1;
    mm.getGrid()[3][0] = -1;
    mm.displayMinefield(new HashSet<>());
    mm.updateMinefieldAfterGeneratingRandomMines();
    mm.displayMinefield(new HashSet<>());
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
    mm = new MinefieldManagement(gridSize, numberOfMines);
    mm.getGrid()[0][3] = -1;
    mm.getGrid()[1][1] = -1;
    mm.getGrid()[2][1] = -1;
    mm.displayMinefield(new HashSet<>());
    mm.updateMinefieldAfterGeneratingRandomMines();
    mm.displayMinefield(new HashSet<>());
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
    mm = new MinefieldManagement(gridSize, numberOfMines);
    mm.getGrid()[1][0] = -1;
    mm.getGrid()[2][0] = -1;
    mm.getGrid()[2][1] = -1;
    mm.displayMinefield(new HashSet<>());
    mm.updateMinefieldAfterGeneratingRandomMines();
    mm.displayMinefield(new HashSet<>());
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
}