package danyliuk.mykola.services;

import danyliuk.mykola.dto.LoginRequestDTO;
import danyliuk.mykola.exceptions.AuthenticationException;
import danyliuk.mykola.security.AuthenticationResponse;

/**
 * Сервіс для автентифікації
 */
public interface AuthService {

  /**
   * Автентифікація користувача
   *
   * @param loginRequestDTO запит
   * @return AuthenticationResponse з повним ім'ям користувача та access token
   * @throws AuthenticationException якщо автентифікація невдала
   */
  AuthenticationResponse signIn(LoginRequestDTO loginRequestDTO) throws AuthenticationException;

}
