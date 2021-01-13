package danyliuk.mykola.rest;


import danyliuk.mykola.dto.UserRequestDTO;
import danyliuk.mykola.services.UserService;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mykola Danyliuk
 */
@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserResource {

  private final UserService userService;

  @PostMapping
  public ResponseEntity <?> createUser(
      @Valid @RequestBody @Schema UserRequestDTO userRequestDTO){
    userService.registerNewUser(userRequestDTO);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}
