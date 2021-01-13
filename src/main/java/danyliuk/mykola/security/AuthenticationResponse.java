package danyliuk.mykola.security;

import danyliuk.mykola.domain.User;
import lombok.Data;

@Data
public class AuthenticationResponse {

  private String fullName;
  private String username;
  private String accessToken;
  private String tokenType = "Bearer";

  public AuthenticationResponse(String accessToken, User user) {
    this.accessToken = accessToken;
    this.fullName = user.getFullName();
    this.username = user.getUsername();
  }

}
