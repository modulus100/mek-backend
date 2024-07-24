package mek.backend.auth.service.impl;

import mek.backend.auth.exception.PasswordNotValidException;
import mek.backend.auth.exception.UserNotFoundException;
import mek.backend.auth.model.Token;
import mek.backend.auth.model.dto.request.LoginRequest;
import mek.backend.auth.model.entity.UserEntity;
import mek.backend.auth.repository.UserRepository;
import mek.backend.auth.service.LoginService;
import mek.backend.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service implementation named {@link LoginServiceImpl} for handling user login operations.
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    /**
     * Performs user login based on the provided login request.
     *
     * @param loginRequest The login request containing user credentials.
     * @return The token representing the user's session.
     */
    @Override
    public Token login(LoginRequest loginRequest) {

        final UserEntity userEntityFromDB = userRepository
                .findUserEntityByEmail(loginRequest.getEmail())
                .orElseThrow(
                        () -> new UserNotFoundException(loginRequest.getEmail())
                );

        if (Boolean.FALSE.equals(passwordEncoder.matches(
                loginRequest.getPassword(), userEntityFromDB.getPassword()))) {
            throw new PasswordNotValidException();
        }

        return tokenService.generateToken(userEntityFromDB.getClaims());
    }

}
