package puregito.backend.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link puregito.backend.entity.UserEntity}
 */

@Value
public class UserRegisterDto implements Serializable {
    String firstName;
    String lastName;
    String username;
    String email;
    String password;
    String confirmPassword;
    String phone;

}