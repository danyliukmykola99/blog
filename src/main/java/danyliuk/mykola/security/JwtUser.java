package danyliuk.mykola.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
public class JwtUser implements UserDetails {

  private static final String ROLE_PREFIX = "ROLE_";

  private static final long serialVersionUID = 3960019229972162399L;
  private final String username;
  private final String role;
  private final String userId;


  public JwtUser(String username, String role, String userId) {
    this.username = username;
    this.role = role;
    this.userId = userId;
  }

  @Override
  public Collection <? extends GrantedAuthority> getAuthorities() {
    List <GrantedAuthority> list = new ArrayList <GrantedAuthority>();

    list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
    return list;
  }

  public String getUsernameInternal() {
    return username;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return username;
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

  public String getEmail() {
    return username;
  }

}
