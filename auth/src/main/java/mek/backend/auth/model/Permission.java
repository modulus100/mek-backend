package mek.backend.auth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Represents a permission domain object as {@link Permission} in the application.
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
public class Permission {
    private String id;
    private String name;
}
