package mek.backend.auth.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @Email(message = "Please enter valid e-mail address")
    @Size(min = 7, message = "Minimum e-mail length is 7 characters.")
    @NotBlank
    private String email;

    @Size(min = 8)
    @NotBlank
    private String password;

    @NotBlank(message = "First name can't be blank.")
    private String firstName;

    @NotBlank(message = "Last name can't be blank.")
    private String lastName;
}
