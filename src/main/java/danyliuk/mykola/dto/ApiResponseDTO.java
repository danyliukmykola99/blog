package danyliuk.mykola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Відповідь-статус, використовується, для повідомлень від сервера
 */
@Data
@AllArgsConstructor
public class ApiResponseDTO {

  /**
   * Статус
   */
  private Boolean success;
  /**
   * Повідомлення
   */
  private String message;

}
