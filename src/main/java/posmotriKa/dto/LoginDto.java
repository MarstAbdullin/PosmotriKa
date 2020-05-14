package posmotriKa.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class LoginDto {

    @NotEmpty(message = "{errors.password.empty}")
    private String password;

    @NotEmpty(message = "{errors.email.empty}")
    @Email(message = "{errors.email.email}")
    private String email;
}
