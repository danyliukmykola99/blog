package danyliuk.mykola.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mykola Danyliuk
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDTO {
  private Integer id;
  private String title;
  private String body;
  private String posterName;
  private Integer posterID;
  private LocalDateTime createdDate;
}
