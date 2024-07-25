package mek.backend.auth.service;

import mek.backend.auth.model.dto.request.TokenRefreshRequest;
import mek.backend.auth.model.dto.response.LoginResponse;

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
    LoginResponse refreshToken(final TokenRefreshRequest tokenRefreshRequest);

}
