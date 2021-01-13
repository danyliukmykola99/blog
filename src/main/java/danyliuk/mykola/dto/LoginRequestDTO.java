package danyliuk.mykola.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Містить дані користувача необхідні для авторизації в системі
 */
@Data
public class LoginRequestDTO {

  /**
   * Логін користувача
   */
  @NotNull
  @NotBlank
  private String username;

  /**
   * Пароль користувача
   */
  @NotNull
  @NotBlank
  private String password;

}
