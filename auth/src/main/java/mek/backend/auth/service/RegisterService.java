package mek.backend.auth.service;

import mek.backend.auth.model.User;
import mek.backend.auth.model.dto.request.RegisterRequest;

/**
 * Service interface named {@link RegisterService} for user registration operations.
 */
public interface RegisterService {

    /**
     * Registers a new user based on the provided registration request.
     *
     * @param registerRequest The registration request containing user details.
     * @return The registered user entity.
     */
    User registerUser(final RegisterRequest registerRequest);
}
