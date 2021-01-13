package danyliuk.mykola.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Базовий абстрактна сутність для класів, які будуть містити дати створення та останнього редагування
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @CreatedDate
  @Column(name = "created_date", updatable = false)
  private LocalDateTime createdDate = LocalDateTime.now();

  @LastModifiedDate
  @Column(name = "last_modified_date")
  @JsonIgnore
  private LocalDateTime lastModifiedDate = LocalDateTime.now();

}
