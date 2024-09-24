package com.test.minesweeper;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Minesweeper {

  char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

  public static void main(String[] args) {
    Minesweeper minesweeper = new Minesweeper();
    minesweeper.newGame();
  }

  private void newGame() {
    System.out.println("Welcome to Minesweeper!\n");

    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid):");
      int gridSize = Integer.parseInt(scanner.nextLine());

      System.out.println(
          "Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
      int numberOfMines = Integer.parseInt(scanner.nextLine());

      Minefield mm = new Minefield(gridSize, numberOfMines);
      mm.initialize();

      System.out.println("\nHere is your minefield:");
      mm.displayMinefield();

      while (true) {
        System.out.print("\nSelect a square to reveal (e.g. A1): ");
        String square = scanner.nextLine();
        Square selectedSquare = getSelectedSquare(square);

        try {
          int numberOfAdjacentMines = mm.processSelectedSquare(selectedSquare);

          System.out.printf("This square contains %s adjacent mines. \n", numberOfAdjacentMines);

          System.out.println("\nHere is your minefield:");
          mm.displayMinefield();

          int n = mm.countRemainingMines();
          if (n == numberOfMines) {
            System.out.println("Congratulations, you have won the game!");

            startANewGame(scanner);
          }
        } catch (Exception e) {
          System.out.println("Oh no, you detonated a mine! Game over.");

          startANewGame(scanner);
        }
      }
    }
  }

  private void startANewGame(Scanner scanner) {
    System.out.println("Press any key to play again...");
    scanner.nextLine();

    newGame();
  }

  public Square getSelectedSquare(String square) {
    String xString = square.substring(0, 1);
    int x =
        IntStream.range(0, alphabet.length)
            .filter(i -> alphabet[i] == xString.charAt(0))
            .findFirst()
            .orElse(-1);
    int y = Integer.parseInt(square.substring(1, 2));
    return new Square(x, y - 1);
  }
}