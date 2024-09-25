package com.test.minesweeper;

import com.test.minesweeper.exception.DetonatedMinesException;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Main class for the Minesweeper application.
 */
public final class Minesweeper {

  public static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

  private void newGame() {
    System.out.println("Welcome to Minesweeper!\n");

    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid):");
      int gridSize = Integer.parseInt(scanner.nextLine());

      System.out.println(
          "Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
      int numberOfMines = Integer.parseInt(scanner.nextLine());

      Minefield minefield = new Minefield(gridSize, numberOfMines);
      minefield.initialize();

      System.out.println("\nHere is your minefield:");
      minefield.displayMinefield();

      while (true) {
        System.out.print("\nSelect a square to reveal (e.g. A1): ");
        String square = scanner.nextLine();
        Square selectedSquare = getSelectedSquare(square);

        try {
          int numberOfAdjacentMines = minefield.processSelectedSquare(selectedSquare);

          System.out.printf("This square contains %s adjacent mines. \n", numberOfAdjacentMines);

          System.out.println("\nHere is your minefield:");
          minefield.displayMinefield();

          int remainingMines = minefield.countRemainingMines();
          if (remainingMines == numberOfMines) {
            System.out.println("\nCongratulations, you have won the game!");

            startNewGame(scanner);
          }
        } catch (DetonatedMinesException e) {
          System.out.println("Oh no, you detonated a mine! Game over.");

          startNewGame(scanner);
        } catch (Exception e) {
          System.out.println("Something went wrong!");
          e.printStackTrace();

          startNewGame(scanner);
        }
      }
    }
  }

  private void startNewGame(Scanner scanner) {
    System.out.println("Press any key to play again...");
    scanner.nextLine();

    newGame();
  }

  /**
   * Get selected square base on the input from user, for example: A1.
   *
   * @param square String represent the square.
   * @return Square object
   */
  public Square getSelectedSquare(String square) {
    String xPos = square.substring(0, 1);
    int x =
        IntStream.range(0, ALPHABET.length)
            .filter(i -> ALPHABET[i] == xPos.charAt(0))
            .findFirst()
            .orElse(-1);

    int y = Integer.parseInt(square.substring(1));

    return new Square(x, y - 1);
  }

  public static void main(String[] args) {
    Minesweeper minesweeper = new Minesweeper();
    minesweeper.newGame();
  }
}
