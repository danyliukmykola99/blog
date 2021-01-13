package danyliuk.mykola.domain;

import danyliuk.mykola.dto.PostResponseDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Mykola Danyliuk
 */
@Entity
@Table(name = "post", schema = "public")
@Builder
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post extends AbstractAuditingEntity{

  @Id
  @SequenceGenerator(name="post_id_seq", sequenceName="post_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_seq")
  @Column(name = "id")
  @EqualsAndHashCode.Include
  private Integer id;

  private String title;

  private String body;

  private User poster;

  public PostResponseDTO toResponseDTO(){
    return PostResponseDTO.builder()
        .id(id)
        .title(title)
        .body(body)
        .posterID(poster.getId())
        .posterName(poster.getFullName())
        .createdDate(getCreatedDate())
        .build();
  }

}
