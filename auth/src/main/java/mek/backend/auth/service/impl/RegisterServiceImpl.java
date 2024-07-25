package mek.backend.auth.service.impl;

import lombok.RequiredArgsConstructor;
import mek.backend.auth.exception.RoleNotFoundException;
import mek.backend.auth.exception.UserAlreadyExistException;
import mek.backend.auth.model.User;
import mek.backend.auth.model.dto.request.RegisterRequest;
import mek.backend.auth.model.entity.UserEntity;
import mek.backend.auth.model.mapper.RegisterRequestToUserEntityMapper;
import mek.backend.auth.model.mapper.UserEntityToUserMapper;
import mek.backend.auth.repository.PermissionRepository;
import mek.backend.auth.repository.RoleRepository;
import mek.backend.auth.repository.UserRepository;
import mek.backend.auth.service.RegisterService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final RegisterRequestToUserEntityMapper registerRequestToUserEntityMapper =
            RegisterRequestToUserEntityMapper.initialize();

    private final UserEntityToUserMapper userEntityToUserMapper = UserEntityToUserMapper.initialize();

    private final PasswordEncoder passwordEncoder;

    public static final String DEFAULT_ROLE = "USER";

    /**
     * Registers a new user based on the provided registration request.
     *
     * @param registerRequest The registration request containing user details.
     * @return The registered user entity.
     */
    @Override
    @Transactional
    public User registerUser(RegisterRequest registerRequest) {

        if (userRepository.existsUserEntityByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistException("The email is already used for another user : " + registerRequest.getEmail());
        }

        final UserEntity userEntityToBeSaved = registerRequestToUserEntityMapper.mapForSaving(registerRequest);
        userEntityToBeSaved.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        final var defaultRole = roleRepository.findByName(DEFAULT_ROLE)
                .orElseThrow(() -> new RoleNotFoundException(DEFAULT_ROLE));

        userEntityToBeSaved.setRoles(List.of(defaultRole));

        final UserEntity savedUserEntity = userRepository.save(userEntityToBeSaved);
        return userEntityToUserMapper.map(savedUserEntity);
    }
}
