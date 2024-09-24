package com.test.minesweeper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinesweeperTest {

  private Minesweeper minesweeper;

  @Test
  public void testGetSelectedSquare() {
    minesweeper = new Minesweeper();

    Square square = minesweeper.getSelectedSquare("A1");

    assertEquals(0, square.xPos());
    assertEquals(0, square.yPos());
  }

  @Test
  public void testGetSelectedSquare1() {
    minesweeper = new Minesweeper();

    Square square = minesweeper.getSelectedSquare("B5");

    assertEquals(1, square.xPos());
    assertEquals(4, square.yPos());
  }

}
