package danyliuk.mykola.services.impl;

import danyliuk.mykola.domain.User;
import danyliuk.mykola.dto.LoginRequestDTO;
import danyliuk.mykola.exceptions.AuthenticationException;
import danyliuk.mykola.exceptions.ErrorReason;
import danyliuk.mykola.security.AuthenticationResponse;
import danyliuk.mykola.security.JwtTokenProvider;
import danyliuk.mykola.services.AuthService;
import danyliuk.mykola.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider tokenProvider;
  private final UserService userService;

  @Override
  public AuthenticationResponse signIn(LoginRequestDTO loginRequestDTO)
      throws AuthenticationException {
    Authentication authentication;
    try {
      authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginRequestDTO.getUsername(),
              loginRequestDTO.getPassword()));
    } catch (Exception ex) {
      throw new AuthenticationException(ErrorReason.USER_CREDENTIALS_NOT_VALID);
    }
    if (authentication == null || authentication.getPrincipal() == null) {
      throw new AuthenticationException(ErrorReason.USER_CREDENTIALS_NOT_VALID);
    }
    Object userPrincipal = authentication.getPrincipal();
    User authenticatedUser = userService.getUserByUserName(loginRequestDTO.getUsername());
    String jwt = tokenProvider.generateToken(userPrincipal, authenticatedUser.getRole().name());
    return new AuthenticationResponse(jwt, authenticatedUser);
  }
}
