package danyliuk.mykola.services.impl;

import danyliuk.mykola.domain.User;
import danyliuk.mykola.dto.UserRequestDTO;
import danyliuk.mykola.exceptions.AuthenticationException;
import danyliuk.mykola.exceptions.DuplicatedDataException;
import danyliuk.mykola.exceptions.ErrorReason;
import danyliuk.mykola.repositories.UserRepository;
import danyliuk.mykola.security.JwtUser;
import danyliuk.mykola.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private final ApplicationContext appContext;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ApplicationContext appContext) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.appContext = appContext;
  }
  @Override
  public User getUserByUserName(String userName) {
    return userRepository.getUserByUsername(userName);
  }

  @Override
  public User getCurrentUser() {
    return userRepository.findByUsernameIgnoreCase(getCurrentUserName())
            .orElseThrow(() -> new AuthenticationException(ErrorReason.INVALID_TOKEN));
  }

  @Override
  public String getCurrentUserName() throws AuthenticationException {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if(principal.getClass().equals(JwtUser.class)){
      return ((JwtUser) principal).getUsername();
    }else{
      return "Неавторизований користувач";
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public User registerNewUser(UserRequestDTO userRequestDTO) {
    userRepository.findByUsernameIgnoreCase(userRequestDTO.getUsername()).ifPresent((it) -> {
      throw new DuplicatedDataException(String.format("User with username %s is already exists", it.getUsername()));
    });
    User user = userRequestDTO.toEntity();
    String decodedPassword = user.getPassword();
    user.setPassword(passwordEncoder.encode(decodedPassword));
    userRepository.save(user);
    return user;
  }

  @Override
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
    return getUserByUserName(username);
  }
}
