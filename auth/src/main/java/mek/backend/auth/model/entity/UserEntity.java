package mek.backend.auth.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import mek.backend.auth.model.enums.TokenClaims;

import java.util.*;


/**
 * Represents an entity as {@link UserEntity} for users.
 */
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "product_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

//    @ManyToMany
//    @JoinTable(
//            name = "user_role_relation",
//            joinColumns = @JoinColumn(name = "USER_ID"),
//            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
//    )

    public Map<String, Object> getClaims() {

        final Map<String, Object> claims = new HashMap<>();
        var roles = new ArrayList<RoleEntity>();

        claims.put(TokenClaims.USER_ID.getValue(), this.id);
        claims.put(TokenClaims.USER_PERMISSIONS.getValue(), roles.stream()
                .map(RoleEntity::getPermissions)
                .flatMap(List::stream)
                .map(PermissionEntity::getName)
                .toList());
        claims.put(TokenClaims.USER_FIRST_NAME.getValue(), this.firstName);
        claims.put(TokenClaims.USER_LAST_NAME.getValue(), this.lastName);
        claims.put(TokenClaims.USER_EMAIL.getValue(), this.email);

        return claims;

    }

}
