package danyliuk.mykola.services;

import danyliuk.mykola.domain.User;
import danyliuk.mykola.dto.UserRequestDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Сервіс для користувачів
 */
public interface UserService extends UserDetailsService {

  /**
   * Отримання користувача за логіном
   * @param username Логін
   * @return Користувач
   */
  User getUserByUserName(String username);

  /**
   * Отримання поточного користувача
   * @return Користувач
   */
  User getCurrentUser();

  /**
   * Отримання логіну поточного користувача
   * @return
   */
  String getCurrentUserName();

  /**
   * Реєстрація нового користувача
   * @param userRequestDTO Інформація про користувача
   * @return Зараєстрований користувач
   */
  User registerNewUser(UserRequestDTO userRequestDTO);

}
