package mek.backend.auth.model.dto.response;



import jakarta.validation.constraints.NotNull;
import mek.backend.auth.model.Token;
import mek.backend.auth.model.entity.UserEntity;

import java.util.List;
import java.util.UUID;


public record LoginResponse(@NotNull Token token, @NotNull User user, @NotNull List<Role> roles) {

    public record Role(@NotNull UUID id, @NotNull String name, @NotNull List<Permission> permissions) {}
    public record Permission(@NotNull UUID id, @NotNull String name) {}
    public record User(@NotNull UUID id, @NotNull String firstName, @NotNull String lastName, @NotNull String email) {}

    public static LoginResponse from(Token token, UserEntity user) {
        return new LoginResponse(
                token,
                new LoginResponse.User(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail()
                ),
                user.getRoles().stream()
                        .map(role -> new LoginResponse.Role(
                                role.getId(),
                                role.getName(),
                                role.getPermissions().stream().map(permission -> new LoginResponse.Permission(
                                        permission.getId(),
                                        permission.getName()
                                )).toList()
                        ))
                        .toList()
        );
    }
}
