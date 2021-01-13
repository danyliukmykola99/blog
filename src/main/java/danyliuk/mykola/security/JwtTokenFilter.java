package danyliuk.mykola.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

@Slf4j
public class JwtTokenFilter extends GenericFilterBean {

  private final JwtTokenProvider jwtTokenProvider;

  public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    log.debug("* ****** ***** JwtTokenFilter");

    if (request.getMethod().equals("OPTIONS")) {

      response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
      response.setHeader("Access-Control-Allow-Credentials", "true");
      response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
      response.setHeader("Access-Control-Max-Age", "1728000");
      response.setHeader("Access-Control-Allow-Headers",
          "Authorization, Content-Type, Accept, X-Requested-With, Origin");
    }

    String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
    if (token == null){
     token =  request.getParameter("access-token");
    }
    if (token != null && jwtTokenProvider.validateToken(token)) {
      log.debug("* ****** *****  token is valid");
      Authentication authentication = jwtTokenProvider.getAuthentication(token);

      if (authentication != null) {
        log.debug("* ****** ***** authentication:: " + authentication.getPrincipal().toString());
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }else {
        log.debug("* ****** ***** authentication::  null" );
      }
    } else {
      log.debug("* ****** ***** null or invalid token");
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }
}
