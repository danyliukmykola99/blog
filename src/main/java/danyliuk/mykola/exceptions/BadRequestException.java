package danyliuk.mykola.exceptions;

/**
 * Неправильний запит
 *
 * @author Mykola Danyliuk
 */
public class BadRequestException extends RuntimeException {

  public BadRequestException(String message) {
    super(message);
  }
}
