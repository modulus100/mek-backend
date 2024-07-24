package mek.backend.auth.service.impl;

import mek.backend.auth.exception.TokenAlreadyInvalidatedException;
import mek.backend.auth.model.entity.InvalidTokenEntity;
import mek.backend.auth.repository.InvalidTokenRepository;
import mek.backend.auth.service.InvalidTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service implementation named {@link InvalidTokenServiceImpl} for managing invalid tokens.
 */
@Service
@RequiredArgsConstructor
public class InvalidTokenServiceImpl implements InvalidTokenService {

    private final InvalidTokenRepository invalidTokenRepository;

    /**
     * Invalidates multiple tokens identified by their IDs.
     *
     * @param tokenIds The set of token IDs to invalidate.
     */
    @Override
    public void invalidateTokens(Set<String> tokenIds) {

        final Set<InvalidTokenEntity> invalidTokenEntities = tokenIds.stream()
                .map(tokenId -> InvalidTokenEntity.builder()
                        .tokenId(tokenId)
                        .build()
                )
                .collect(Collectors.toSet());

        invalidTokenRepository.saveAll(invalidTokenEntities);
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