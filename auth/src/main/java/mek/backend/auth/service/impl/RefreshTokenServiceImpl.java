package mek.backend.auth.service.impl;

import lombok.RequiredArgsConstructor;
import mek.backend.auth.exception.UserNotFoundException;
import mek.backend.auth.exception.UserStatusNotValidException;
import mek.backend.auth.model.dto.request.TokenRefreshRequest;
import mek.backend.auth.model.dto.response.LoginResponse;
import mek.backend.auth.model.entity.UserEntity;
import mek.backend.auth.model.enums.TokenClaims;
import mek.backend.auth.model.enums.UserStatus;
import mek.backend.auth.repository.UserRepository;
import mek.backend.auth.service.RefreshTokenService;
import mek.backend.auth.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service implementation named {@link RefreshTokenServiceImpl} for refreshing authentication tokens.
 */
@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    /**
     * Refreshes an authentication token based on the provided refresh token request.
     *
     * @param tokenRefreshRequest The request containing the refresh token.
     * @return The refreshed authentication token.
     */
    @Override
    public LoginResponse refreshToken(TokenRefreshRequest tokenRefreshRequest) {

        tokenService.verifyAndValidate(tokenRefreshRequest.getRefreshToken());

        final String userId = tokenService
                .getPayload(tokenRefreshRequest.getRefreshToken())
                .get(TokenClaims.USER_ID.getValue())
                .toString();

        final UserEntity user = userRepository
                .findById(UUID.fromString(userId))
                .orElseThrow(UserNotFoundException::new);

        this.validateAdminStatus(user);

        final var token = tokenService.generateToken(
                user.getClaims(),
                tokenRefreshRequest.getRefreshToken()
        );

        return LoginResponse.from(token, user);
    }

    /**
     * Validates the status of an admin user.
     *
     * @param userEntity The user entity to validate.
     * @throws UserStatusNotValidException If the user status is not valid.
     */
    private void validateAdminStatus(final UserEntity userEntity) {
        if (!(UserStatus.ACTIVE.equals(userEntity.getStatus()))) {
            throw new UserStatusNotValidException("UserStatus = " + userEntity.getStatus());
        }
    }
}
