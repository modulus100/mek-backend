package mek.backend.auth.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Represents an entity as {@link PermissionEntity} for permissions.
 */
@Entity
@Getter
@Setter
@Table(name = "user_permission")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NAME")
    private String name;

}
