package mek.backend.auth.repository;

import mek.backend.auth.model.entity.InvalidTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface named {@link InvalidTokenRepository} for managing {@link InvalidTokenEntity} entities.
 */
public interface InvalidTokenRepository extends JpaRepository<InvalidTokenEntity, UUID> {

    /**
     * Finds an invalid token entity by its tokenId.
     *
     * @param tokenId The tokenId of the invalid token to find.
     * @return An {@link Optional} containing the found {@link InvalidTokenEntity}, or empty if not found.
     */
    Optional<InvalidTokenEntity> findByTokenId(final String tokenId);

}
