package danyliuk.mykola.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Mykola Danyliuk
 */
@Entity
@Table(name = "user", schema = "public")
@Builder
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable, UserDetails {

  @Id
  @SequenceGenerator(name="user_id_seq", sequenceName="user_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
  @Column(name = "id")
  @EqualsAndHashCode.Include
  private Integer id;

  @NotNull
  @Column(name = "username")
  private String username;

  @NotNull
  @Column(name = "password")
  private String password;

  @NotNull
  @Column(name = "full_name")
  private String fullName;

  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private Role role;

  @Override
  public Collection <? extends GrantedAuthority> getAuthorities() {
    List <GrantedAuthority> list = new ArrayList <GrantedAuthority>();

    list.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
    return list;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
