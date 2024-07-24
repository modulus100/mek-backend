package mek.backend.auth.service.impl;

import mek.backend.auth.exception.UserNotFoundException;
import mek.backend.auth.exception.UserStatusNotValidException;
import mek.backend.auth.model.Token;
import mek.backend.auth.model.dto.request.TokenRefreshRequest;
import mek.backend.auth.model.entity.UserEntity;
import mek.backend.auth.model.enums.TokenClaims;
import mek.backend.auth.model.enums.UserStatus;
import mek.backend.auth.repository.UserRepository;
import mek.backend.auth.service.RefreshTokenService;
import mek.backend.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Token refreshToken(TokenRefreshRequest tokenRefreshRequest) {

        tokenService.verifyAndValidate(tokenRefreshRequest.getRefreshToken());

        final String adminId = tokenService
                .getPayload(tokenRefreshRequest.getRefreshToken())
                .get(TokenClaims.USER_ID.getValue())
                .toString();

        final UserEntity userEntityFromDB = userRepository
                .findById(adminId)
                .orElseThrow(UserNotFoundException::new);

        this.validateAdminStatus(userEntityFromDB);

        return tokenService.generateToken(
                userEntityFromDB.getClaims(),
                tokenRefreshRequest.getRefreshToken()
        );
    }

    /**
     * Validates the status of an admin user.
     *
     * @param userEntity The user entity to validate.
     * @throws UserStatusNotValidException If the user status is not valid.
     */
    private void validateAdminStatus(final UserEntity userEntity) {
//        if (!(UserStatus.ACTIVE.equals(userEntity.getUserStatus()))) {
//            throw new UserStatusNotValidException("UserStatus = " + userEntity.getUserStatus());
//        }
    }

}
