package mek.backend.auth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Represents a user domain object as {@link User} in the application.
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
public class User {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
}
