package danyliuk.mykola.rest;

import danyliuk.mykola.dto.LoginRequestDTO;
import danyliuk.mykola.exceptions.AuthenticationException;
import danyliuk.mykola.services.AuthService;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthResource {

  private final AuthService service;

  /**
   * Автентифікація користувача в системі за логіном та паролем
   * @param loginRequestDTO - логін та пароль
   * @return JWT token
   */
  @PostMapping(path = "/login")
  public ResponseEntity<?> authenticateUser(
      @Valid @RequestBody @Schema LoginRequestDTO loginRequestDTO)
      throws AuthenticationException {
    return ResponseEntity.ok(service.signIn(loginRequestDTO));
  }

}
