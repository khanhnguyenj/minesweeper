package com.test.minesweeper;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

public class Minesweeper {

  char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

  private static Set<Square> selectedSquares = new HashSet<>();

  public static void main(String[] args) {
    System.out.println("Welcome to Minesweeper!");

    Minesweeper application = new Minesweeper();

    try (Scanner scanner = new Scanner(System.in);) {
      System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid):");
      int gridSize = scanner.nextInt();

      System.out.println(
          "Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
      int numberOfMines = scanner.nextInt();
      MinefieldManagement mm = new MinefieldManagement(gridSize, numberOfMines);
      mm.initialize();

      System.out.println("Here is your minefield:");
      mm.displayMinefield(selectedSquares);

      scanner.nextLine();

      System.out.println("Select a square to reveal (e.g. A1):");
      String square = scanner.nextLine();

      selectedSquares.add(application.getSelectedSquare(square));


    }
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