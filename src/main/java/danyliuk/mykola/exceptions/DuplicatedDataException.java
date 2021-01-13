package danyliuk.mykola.exceptions;

/**
 * Заборонено. Запит був коректним, але сервер відмовляється відповідати на нього.
 *
 * @author Mykola Danyliuk
 */
public class DuplicatedDataException extends RuntimeException {

  public DuplicatedDataException() {
    super();
  }

  public DuplicatedDataException(String message) {
    super(message);
  }
}
