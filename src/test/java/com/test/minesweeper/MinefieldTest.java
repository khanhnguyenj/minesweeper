package com.test.minesweeper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;

public class MinefieldTest {

  private Minefield minefield;

  @Test
  public void testGrid() throws NoSuchFieldException, IllegalAccessException {
    int gridSize = 4;
    int numberOfMines = 2;
    minefield = new Minefield(gridSize, numberOfMines);
    minefield.displayMinefield();

    Field field = minefield.getClass().getDeclaredField("grid");
    field.setAccessible(true);
    int[][] grid = (int[][]) field.get(minefield);

    assertEquals(gridSize, grid.length);
  }

  @Test
  public void testGenerateRandomMines()
      throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
    int gridSize = 4;
    int numberOfMines = 2;
    minefield = new Minefield(gridSize, numberOfMines);

    Method method = minefield.getClass().getDeclaredMethod("generateRandomMines");
    method.setAccessible(true);
    method.invoke(minefield, null);

    minefield.displayMinefield();

    Field field = minefield.getClass().getDeclaredField("grid");
    field.setAccessible(true);
    int[][] grid = (int[][]) field.get(minefield);

    int countMines = 0;
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        if (grid[i][j] == -1) {
          countMines++;
        }
      }
    }

    assertEquals(numberOfMines, countMines);
  }

  @Test
  public void testUpdateMinefieldAfterGeneratingRandomMines1()
      throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    int gridSize = 4;
    int numberOfMines = 2;
    minefield = new Minefield(gridSize, numberOfMines);

    Field field = minefield.getClass().getDeclaredField("grid");
    field.setAccessible(true);
    int[][] grid = (int[][]) field.get(minefield);

    grid[0][3] = -1;
    grid[3][0] = -1;

    minefield.displayMinefield();

    Method method = minefield.getClass()
        .getDeclaredMethod("updateMinefieldAfterGeneratingRandomMines");
    method.setAccessible(true);
    method.invoke(minefield, null);

    minefield.displayMinefield();

    assertEquals(1, grid[1][2]);
    assertEquals(1, grid[1][3]);
    assertEquals(1, grid[2][0]);
    assertEquals(1, grid[2][1]);
    assertEquals(1, grid[3][1]);
  }

  @Test
  public void testUpdateMinefieldAfterGeneratingRandomMines2()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
    int gridSize = 4;
    int numberOfMines = 2;
    minefield = new Minefield(gridSize, numberOfMines);

    Field field = minefield.getClass().getDeclaredField("grid");
    field.setAccessible(true);
    int[][] grid = (int[][]) field.get(minefield);

    grid[1][1] = -1;
    grid[3][0] = -1;

    minefield.displayMinefield();

    Method method = minefield.getClass()
        .getDeclaredMethod("updateMinefieldAfterGeneratingRandomMines");
    method.setAccessible(true);
    method.invoke(minefield, null);

    minefield.displayMinefield();

    assertEquals(1, grid[0][0]);
    assertEquals(1, grid[0][1]);
    assertEquals(1, grid[0][2]);
    assertEquals(1, grid[1][0]);
    assertEquals(1, grid[1][2]);
    assertEquals(2, grid[2][0]);
    assertEquals(2, grid[2][1]);
    assertEquals(1, grid[2][2]);
    assertEquals(1, grid[3][1]);
  }

  @Test
  public void testUpdateMinefieldAfterGeneratingRandomMines3()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
    int gridSize = 4;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);
    Field field = minefield.getClass().getDeclaredField("grid");
    field.setAccessible(true);
    int[][] grid = (int[][]) field.get(minefield);

    grid[0][3] = -1;
    grid[1][1] = -1;
    grid[2][1] = -1;

    minefield.displayMinefield();

    Method method = minefield.getClass()
        .getDeclaredMethod("updateMinefieldAfterGeneratingRandomMines");
    method.setAccessible(true);
    method.invoke(minefield, null);

    minefield.displayMinefield();

    assertEquals(1, grid[0][0]);
    assertEquals(1, grid[0][1]);
    assertEquals(2, grid[0][2]);
    assertEquals(2, grid[1][0]);
    assertEquals(3, grid[1][2]);
    assertEquals(1, grid[1][3]);
    assertEquals(2, grid[2][0]);
    assertEquals(2, grid[2][2]);
    assertEquals(1, grid[3][0]);
    assertEquals(1, grid[3][1]);
    assertEquals(1, grid[3][2]);
  }

  @Test
  public void testUpdateMinefieldAfterGeneratingRandomMines4()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
    int gridSize = 5;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);

    Field field = minefield.getClass().getDeclaredField("grid");
    field.setAccessible(true);
    int[][] grid = (int[][]) field.get(minefield);

    grid[1][0] = -1;
    grid[2][0] = -1;
    grid[2][1] = -1;

    minefield.displayMinefield();

    Method method = minefield.getClass()
        .getDeclaredMethod("updateMinefieldAfterGeneratingRandomMines");
    method.setAccessible(true);
    method.invoke(minefield, null);

    minefield.displayMinefield();

    assertEquals(1, grid[0][0]);
    assertEquals(1, grid[0][1]);
    assertEquals(3, grid[1][1]);
    assertEquals(1, grid[1][2]);
    assertEquals(1, grid[2][2]);
    assertEquals(2, grid[3][0]);
    assertEquals(2, grid[3][1]);
    assertEquals(1, grid[3][2]);
  }

  @Test
  public void testGenerateDisplayGrid()
      throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
    int gridSize = 5;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);

    Method method = minefield.getClass().getDeclaredMethod("generateDisplayGrid");
    method.setAccessible(true);
    method.invoke(minefield, null);

    Field field = minefield.getClass().getDeclaredField("displayGrid");
    field.setAccessible(true);
    char[][] displayGrid = (char[][]) field.get(minefield);

    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        assertEquals('_', displayGrid[i][j]);
      }
    }
  }

  @Test
  public void testInitialize() throws NoSuchFieldException, IllegalAccessException {
    int gridSize = 5;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);

    minefield.initialize();

    Field field = minefield.getClass().getDeclaredField("displayGrid");
    field.setAccessible(true);
    char[][] displayGrid = (char[][]) field.get(minefield);
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        assertEquals('_', displayGrid[i][j]);
      }
    }

    Field field1 = minefield.getClass().getDeclaredField("grid");
    field1.setAccessible(true);
    int[][] grid = (int[][]) field1.get(minefield);
    int countMines = 0;
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        if (grid[i][j] == -1) {
          countMines++;
        }
      }
    }

    assertEquals(numberOfMines, countMines);
  }

  @Test
  public void testProcessSelectedSquare()
      throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    int gridSize = 5;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);

    Field field = minefield.getClass().getDeclaredField("grid");
    field.setAccessible(true);
    int[][] grid = (int[][]) field.get(minefield);

    grid[1][0] = -1;
    grid[2][0] = -1;
    grid[2][1] = -1;

    Method method = minefield.getClass()
        .getDeclaredMethod("updateMinefieldAfterGeneratingRandomMines");
    method.setAccessible(true);
    method.invoke(minefield, null);

    Method method2 = minefield.getClass().getDeclaredMethod("generateDisplayGrid");
    method2.setAccessible(true);
    method2.invoke(minefield, null);

    minefield.displayMinefield();
    int n = minefield.processSelectedSquare(new Square(2, 3));
    minefield.displayMinefield();

    assertEquals(0, n);
  }

  @Test
  public void testProcessSelectedSquare1()
      throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    int gridSize = 4;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);

    Field field = minefield.getClass().getDeclaredField("grid");
    field.setAccessible(true);
    int[][] grid = (int[][]) field.get(minefield);

    grid[0][1] = -1;
    grid[1][1] = -1;
    grid[2][0] = -1;

    Method method = minefield.getClass()
        .getDeclaredMethod("updateMinefieldAfterGeneratingRandomMines");
    method.setAccessible(true);
    method.invoke(minefield, null);

    Method method2 = minefield.getClass().getDeclaredMethod("generateDisplayGrid");
    method2.setAccessible(true);
    method2.invoke(minefield, null);

    minefield.displayMinefield();
    int n = minefield.processSelectedSquare(new Square(1, 0));
    minefield.displayMinefield();

    assertEquals(3, n);
  }

  @Test
  public void testProcessSelectedSquare2()
      throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    int gridSize = 4;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);

    Field field = minefield.getClass().getDeclaredField("grid");
    field.setAccessible(true);
    int[][] grid = (int[][]) field.get(minefield);

    grid[0][1] = -1;
    grid[1][1] = -1;
    grid[2][0] = -1;

    Method method = minefield.getClass()
        .getDeclaredMethod("updateMinefieldAfterGeneratingRandomMines");
    method.setAccessible(true);
    method.invoke(minefield, null);

    Method method2 = minefield.getClass().getDeclaredMethod("generateDisplayGrid");
    method2.setAccessible(true);
    method2.invoke(minefield, null);

    minefield.displayMinefield();
    int n = minefield.processSelectedSquare(new Square(3, 3));
    minefield.displayMinefield();

    assertEquals(0, n);

    Field field1 = minefield.getClass().getDeclaredField("displayGrid");
    field1.setAccessible(true);
    char[][] displayGrid = (char[][]) field1.get(minefield);

    assertEquals('0', displayGrid[0][3]);
    assertEquals('0', displayGrid[1][3]);
    assertEquals('0', displayGrid[2][3]);
    assertEquals('0', displayGrid[3][3]);
    assertEquals('0', displayGrid[3][2]);

    assertEquals('2', displayGrid[0][2]);
    assertEquals('2', displayGrid[1][2]);
    assertEquals('1', displayGrid[2][2]);
    assertEquals('2', displayGrid[2][1]);
    assertEquals('1', displayGrid[3][1]);
  }

  @Test
  public void testProcessSelectedSquare3()
      throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    int gridSize = 4;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);

    Field field = minefield.getClass().getDeclaredField("grid");
    field.setAccessible(true);
    int[][] grid = (int[][]) field.get(minefield);

    grid[0][1] = -1;
    grid[1][1] = -1;
    grid[2][0] = -1;

    Method method = minefield.getClass()
        .getDeclaredMethod("updateMinefieldAfterGeneratingRandomMines");
    method.setAccessible(true);
    method.invoke(minefield, null);

    Method method2 = minefield.getClass().getDeclaredMethod("generateDisplayGrid");
    method2.setAccessible(true);
    method2.invoke(minefield, null);

    minefield.displayMinefield();
    int n = minefield.processSelectedSquare(new Square(0, 0));
    minefield.displayMinefield();

    assertEquals(2, n);
  }

  @Test
  public void testProcessSelectedSquare4()
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
    int gridSize = 4;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);

    Field field = minefield.getClass().getDeclaredField("grid");
    field.setAccessible(true);
    int[][] grid = (int[][]) field.get(minefield);

    grid[0][1] = -1;
    grid[1][1] = -1;
    grid[2][0] = -1;

    Method method = minefield.getClass()
        .getDeclaredMethod("updateMinefieldAfterGeneratingRandomMines");
    method.setAccessible(true);
    method.invoke(minefield, null);

    Method method2 = minefield.getClass().getDeclaredMethod("generateDisplayGrid");
    method2.setAccessible(true);
    method2.invoke(minefield, null);

    minefield.displayMinefield();
    int n = minefield.processSelectedSquare(new Square(3, 0));
    minefield.displayMinefield();

    assertEquals(1, n);
  }

  @Test
  public void testCountRemainingMines()
      throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    int gridSize = 4;
    int numberOfMines = 3;
    minefield = new Minefield(gridSize, numberOfMines);

    Field field = minefield.getClass().getDeclaredField("grid");
    field.setAccessible(true);
    int[][] grid = (int[][]) field.get(minefield);

    grid[0][1] = -1;
    grid[1][1] = -1;
    grid[2][0] = -1;

    Method method = minefield.getClass()
        .getDeclaredMethod("updateMinefieldAfterGeneratingRandomMines");
    method.setAccessible(true);
    method.invoke(minefield, null);

    Method method2 = minefield.getClass().getDeclaredMethod("generateDisplayGrid");
    method2.setAccessible(true);
    method2.invoke(minefield, null);

    minefield.displayMinefield();
    minefield.processSelectedSquare(new Square(0, 0));
    minefield.displayMinefield();

    int n = minefield.countRemainingMines();

    assertEquals(15, n);
  }
}