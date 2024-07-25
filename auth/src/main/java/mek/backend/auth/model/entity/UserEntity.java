package mek.backend.auth.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import mek.backend.auth.model.enums.TokenClaims;
import mek.backend.auth.model.enums.UserStatus;

import java.util.*;


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

    @Builder.Default
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.WAITING_APPROVAL;

    @ManyToMany
    @JoinTable(
            name = "user_role_relation",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private List<RoleEntity> roles;

    public Map<String, Object> getClaims() {

        final Map<String, Object> claims = new HashMap<>();

        claims.put(TokenClaims.USER_ID.getValue(), this.id);
        claims.put(TokenClaims.USER_PERMISSIONS.getValue(), this.roles.stream()
                .map(RoleEntity::getPermissions)
                .flatMap(List::stream)
                .map(PermissionEntity::getName)
                .toList());

        claims.put(TokenClaims.USER_ROLES.getValue(), this.roles.stream()
                .map(RoleEntity::getName)
                .toList());

        claims.put(TokenClaims.USER_STATUS.getValue(), this.status);
//        claims.put(TokenClaims.USER_FIRST_NAME.getValue(), this.firstName);
//        claims.put(TokenClaims.USER_LAST_NAME.getValue(), this.lastName);
//        claims.put(TokenClaims.USER_EMAIL.getValue(), this.email);

        return claims;
    }
}
