package mek.backend.auth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Represents a role domain object as {@link Role} in the application.
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
public class Role {

    private String id;
    private String name;
    private List<Permission> permissions;

}
