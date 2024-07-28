package mek.backend.auth.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents an entity as {@link InvalidTokenEntity} for invalid tokens.
 */
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "invalidated_token")
public class InvalidTokenEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "TOKEN")
    private String tokenId;

    @Column(name = "INVALIDATED_AT")
    private LocalDateTime invalidatedAt = LocalDateTime.now();
}
