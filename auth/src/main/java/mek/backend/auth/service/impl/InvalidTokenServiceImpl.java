package mek.backend.auth.service.impl;

import lombok.RequiredArgsConstructor;
import mek.backend.auth.exception.TokenAlreadyInvalidatedException;
import mek.backend.auth.model.entity.InvalidTokenEntity;
import mek.backend.auth.repository.InvalidTokenRepository;
import mek.backend.auth.service.InvalidTokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service implementation named {@link InvalidTokenServiceImpl} for managing invalid tokens.
 */
@Service
@RequiredArgsConstructor
public class InvalidTokenServiceImpl implements InvalidTokenService {

    private final InvalidTokenRepository invalidTokenRepository;

    @Override
    public void invalidateTokens(String accessTokenId, String refreshTokenId) {
        invalidTokenRepository.saveAll(List.of(
                new InvalidTokenEntity(null, accessTokenId, LocalDateTime.now()),
                new InvalidTokenEntity(null, refreshTokenId, LocalDateTime.now())
        ));
    }

    /**
     * Checks if a token identified by its ID is invalid.
     *
     * @param tokenId The ID of the token to check for invalidity.
     * @throws TokenAlreadyInvalidatedException If the token is already invalidated.
     */
    @Override
    public void checkForInvalidityOfToken(String tokenId) {
        final boolean isTokenInvalid = invalidTokenRepository.findByTokenId(tokenId).isPresent();

        if (isTokenInvalid) {
            throw new TokenAlreadyInvalidatedException(tokenId);
        }
    }
}