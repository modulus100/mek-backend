package mek.backend.auth.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Represents a login request as {@link LoginRequest} with email and password.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
