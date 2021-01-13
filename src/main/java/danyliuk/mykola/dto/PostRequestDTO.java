package danyliuk.mykola.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mykola Danyliuk
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDTO {
  @NotBlank
  private String title;
  @NotBlank
  private String body;
}
