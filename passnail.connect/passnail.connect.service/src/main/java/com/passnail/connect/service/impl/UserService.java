package com.passnail.connect.service.impl;

import com.passnail.connect.service.UserServiceIf;
import com.passnail.data.access.model.dao.CredentialsRepository;
import com.passnail.data.access.model.dao.UserRepository;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.transfer.model.dto.UserDto;
import com.passnail.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * A service containing functions for managing users stored in the local database.
 * <p>
 * Created by: Pszemko at wtorek, 02.02.2021 19:01
 * Project: passnail-client
 */
@Service
public class UserService implements UserServiceIf {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * Creates a new local (offline) user and saves it in the local database.
     *
     * @param aUserDto
     */
    public void createNewUser(UserDto aUserDto) {

        authenticationService.validateNewUserCredentials(aUserDto);
        Date creationDate = new Date();

        UserEntity newUserEntity = UserEntity.builder()
                .creationDate(creationDate)
                .login(aUserDto.getLogin())
                .password(
                        encoder
                                .encode(aUserDto.getPassword()))
                .local(true)
                .build();

        userRepository.save(newUserEntity);

    }

    @Override
    public UserEntity findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

}
