package danyliuk.mykola.security;

import danyliuk.mykola.domain.User;
import danyliuk.mykola.exceptions.AuthenticationException;
import danyliuk.mykola.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtTokenProvider {

  @Value("${spring.jwt.jwtSecret}")
  private String jwtSecret;

  private UserRepository userRepository;

  public JwtTokenProvider(UserRepository repository) {
    this.userRepository = repository;
  }

  @Value("${spring.jwt.tokenExp}")
  private long tokenExpiration;

  public Authentication getAuthentication(String token) {
    Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
    Claims body = claimsJws.getBody();
    String username = body.get("username", String.class);
    String role = body.get("role", String.class);
    String user_id = body.get("user_id", String.class);
    JwtUser jwtUser = new JwtUser(username, role, user_id);
    return new UsernamePasswordAuthenticationToken(jwtUser, "", jwtUser.getAuthorities());
  }

  public String generateToken(Object authenticationPrincipal, String role)
      throws AuthenticationException {
    String username = "";
    String userId = "";
    if(authenticationPrincipal.getClass().equals(User.class)){
      User user = ((User)authenticationPrincipal);
      username = user.getUsername();
      userId = String.valueOf(user.getId());
    }
    return Jwts.builder().claim("username", username).claim("user_id", userId)
        .claim("role", role).setIssuedAt(new Date())
        .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
  }

  public String getUsernameFromJWT(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject();
  }



  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException ex) {
      log.error("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty.");
    }
    return false;
  }

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");

    if (bearerToken != null && bearerToken.startsWith("Bearer")) {
      return bearerToken.substring(7);
    }

    return null;
  }
}
