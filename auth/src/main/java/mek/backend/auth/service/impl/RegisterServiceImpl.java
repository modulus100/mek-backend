package mek.backend.auth.service.impl;

import lombok.RequiredArgsConstructor;
import mek.backend.auth.exception.RoleNotFoundException;
import mek.backend.auth.exception.UserAlreadyExistException;
import mek.backend.auth.model.dto.request.LoginRequest;
import mek.backend.auth.model.dto.request.RegisterRequest;
import mek.backend.auth.model.entity.UserEntity;
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

    private final PasswordEncoder passwordEncoder;

    public static final String DEFAULT_ROLE = "USER";


    @Override
    @Transactional
    public UserEntity registerUser(RegisterRequest request) {
        var userEntity = UserEntity.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();

        if (userRepository.existsUserEntityByEmail(userEntity.getEmail())) {
            throw new UserAlreadyExistException("The email is already used for another user : " + userEntity.getEmail());
        }

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        final var defaultRole = roleRepository.findByName(DEFAULT_ROLE)
                .orElseThrow(() -> new RoleNotFoundException(DEFAULT_ROLE));

        userEntity.setRoles(List.of(defaultRole));
        return userRepository.save(userEntity);
    }
}
