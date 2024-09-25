package com.test.minesweeper.exception;

/**
 * The exception class will be used to throw an exception when user choose a mine.
 */
public class DetonatedMinesException extends RuntimeException {

  /**
   * Constructor.
   *
   * @param message Message of the exception.
   */
  public DetonatedMinesException(final String message) {
    super(message);
  }
}
