package mek.backend.auth.service;

import mek.backend.auth.model.Token;
import mek.backend.auth.model.dto.request.TokenRefreshRequest;

/**
 * Service interface named {@link RefreshTokenService} for refreshing authentication tokens.
 */
public interface RefreshTokenService {

    /**
     * Refreshes an authentication token based on the provided refresh token request.
     *
     * @param tokenRefreshRequest The request containing the refresh token.
     * @return The refreshed authentication token.
     */
    Token refreshToken(final TokenRefreshRequest tokenRefreshRequest);

}
