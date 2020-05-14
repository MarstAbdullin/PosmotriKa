package posmotriKa.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class ProfileForm {
    @NotEmpty(message = "{error.email.empty}")
    @Email(message = "{error.email.incorrect}")
    private String email;

    @NotEmpty(message = "{error.username.empty}")
    private String username;
}

