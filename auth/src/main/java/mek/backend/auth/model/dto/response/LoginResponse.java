package mek.backend.auth.model.dto.response;



import mek.backend.auth.model.Token;
import mek.backend.auth.model.entity.UserEntity;

import java.util.List;
import java.util.UUID;


public record LoginResponse(Token token, User user, List<Role> roles) {

    public record Role(UUID id, String name, List<Permission> permissions) {}
    public record Permission(UUID id, String name) {}
    public record User(UUID id, String firstName, String lastName, String email) {}

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
