package mek.backend.auth.service.impl;

import mek.backend.auth.model.dto.request.TokenInvalidateRequest;
import mek.backend.auth.service.InvalidTokenService;
import mek.backend.auth.service.LogoutService;
import mek.backend.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Service implementation named {@link LogoutServiceImpl} for handling user logout operations.
 */
@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutService {

    private final TokenService tokenService;
    private final InvalidTokenService invalidTokenService;

    /**
     * Logs out a user session based on the provided token invalidation request.
     *
     * @param tokenInvalidateRequest The request containing tokens to invalidate for logout.
     */
    @Override
    @Transactional
    public void logout(TokenInvalidateRequest tokenInvalidateRequest) {

        tokenService.verifyAndValidate(
                Set.of(
                        tokenInvalidateRequest.getAccessToken(),
                        tokenInvalidateRequest.getRefreshToken()
                )
        );

        final String accessTokenId = tokenService
                .getPayload(tokenInvalidateRequest.getAccessToken())
                .getId();

        invalidTokenService.checkForInvalidityOfToken(accessTokenId);

        final String refreshTokenId = tokenService
                .getPayload(tokenInvalidateRequest.getRefreshToken())
                .getId();

        invalidTokenService.checkForInvalidityOfToken(refreshTokenId);
        invalidTokenService.invalidateTokens(accessTokenId, refreshTokenId);
    }
}
