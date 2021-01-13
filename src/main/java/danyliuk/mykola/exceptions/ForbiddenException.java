package danyliuk.mykola.exceptions;

/**
 * Заборонено. Запит був коректним, але сервер відмовляється відповідати на нього.
 *
 * @author Mykola Danyliuk
 */
public class ForbiddenException extends RuntimeException {

  public ForbiddenException() {
    super();
  }

  public ForbiddenException(String message) {
    super(message);
  }
}
