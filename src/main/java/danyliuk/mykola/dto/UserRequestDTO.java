package danyliuk.mykola.dto;

import danyliuk.mykola.domain.Role;
import danyliuk.mykola.domain.User;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String fullName;
    @NotBlank
    private String role;

    public User toEntity(){
        return User.builder()
            .password(password)
            .username(username)
            .fullName(fullName)
            .role(Role.valueOf(role))
            .build();
    }
}
