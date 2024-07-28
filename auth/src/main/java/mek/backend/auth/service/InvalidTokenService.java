package mek.backend.auth.service;

import java.util.Set;

/**
 * Service interface named {@link InvalidTokenService} for managing invalid tokens.
 */
public interface InvalidTokenService {


    void invalidateTokens(String accessTokenId, String refreshTokenId);

    /**
     * Checks if a token identified by its ID is invalid.
     *
     * @param tokenId The ID of the token to check for invalidity.
     */
    void checkForInvalidityOfToken(final String tokenId);

}
