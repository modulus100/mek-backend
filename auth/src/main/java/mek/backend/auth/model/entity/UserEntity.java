package mek.backend.auth.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import mek.backend.auth.model.enums.TokenClaims;
import mek.backend.auth.model.enums.UserStatus;

import java.time.Instant;
import java.util.*;


@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "user_account")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @NonNull
    @Column(name = "email")
    private String email;

    @NonNull
    @Column(name = "password")
    private String password;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Builder.Default
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.WAITING_APPROVAL;

    @NonNull
    @Column(name = "registration_date")
    private Instant registrationDate = Instant.now();

    @NonNull
    @Column(name = "birth_date")
    private Instant birthDate = Instant.now();

    @NonNull
    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "personal_id_code")
    private String personalIdCode;

    @ManyToMany
    @JoinTable(
            name = "user_role_relation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles;

    public Map<String, Object> getClaims() {
        final var claims = new HashMap<String, Object>();

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
        return claims;
    }
}
