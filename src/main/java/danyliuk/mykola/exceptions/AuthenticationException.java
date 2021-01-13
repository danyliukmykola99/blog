package danyliuk.mykola.exceptions;

/**
 * Помилка авторизації
 */
public class AuthenticationException extends GenericException {

  public AuthenticationException(ErrorReason errorReason, Object... reasonParams) {
    super(errorReason, reasonParams);
  }

}
