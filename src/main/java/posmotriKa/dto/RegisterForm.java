package posmotriKa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterForm {
    @NotEmpty(message = "{error.email.empty}")
    @Email(message = "{error.email.incorrect}")
    private String email;

    @NotEmpty(message = "{error.empty.password}")
    private String password;

    @NotEmpty(message = "{error.name.empty}")
    @Size(min = 1, max = 45,message = "{error.name.max}")
    private String username;
}
