package mek.backend.auth.service;

import mek.backend.auth.model.dto.request.RegisterRequest;
import mek.backend.auth.model.entity.UserEntity;

/**
 * Service interface named {@link RegisterService} for user registration operations.
 */
public interface RegisterService {

    UserEntity registerUser(final RegisterRequest request);
}
