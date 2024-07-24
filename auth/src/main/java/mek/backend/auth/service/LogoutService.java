package mek.backend.auth.service;

import mek.backend.auth.model.dto.request.TokenInvalidateRequest;

/**
 * Service interface named {@link LogoutService} for handling user logout operations.
 */
public interface LogoutService {

    /**
     * Logs out a user session based on the provided token invalidation request.
     *
     * @param tokenInvalidateRequest The request containing tokens to invalidate for logout.
     */
    void logout(final TokenInvalidateRequest tokenInvalidateRequest);

}
