package com.passnail.security.service;

import com.passnail.data.access.model.dao.CredentialsRepository;
import com.passnail.data.access.model.dao.UserRepository;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.transfer.model.dto.LocalUserDto;
import com.passnail.data.transfer.model.dto.UserDto;
import com.passnail.security.throwable.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Contains methods for authorization.
 * <p>
 * Created by: Pszemko at wtorek, 02.02.2021 19:40
 * Project: passnail-client
 */
@Service
public class AuthorizationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * Authorizes local (offline) user by login and password contained in the dto object.
     *
     * @param aUserDto
     * @return
     */
    public UserEntity authorizeLocalUser(UserDto aUserDto) {
        Objects.nonNull(aUserDto);

        UserEntity userForAuthorization = userRepository.findByLogin(aUserDto.getLogin());

        if (!encoder.matches(aUserDto.getPassword(), userForAuthorization.getPassword())) {
            throw new AuthorizationException("Incorrect Password!");
        }

        return userForAuthorization;

    }
}
