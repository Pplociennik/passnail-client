package com.passnail.connect.service.impl;

import com.passnail.connect.service.UserServiceIf;
import com.passnail.data.access.model.dao.CredentialsRepository;
import com.passnail.data.access.model.dao.UserRepository;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.security.service.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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


    @Override
    public UserEntity findByLogin(String aLogin) {
        return userRepository.findByLogin(aLogin);
    }

    @Override
    public UserEntity findByEmail(String aEmail) {
        return userRepository.findByEmailAddress(aEmail);
    }

    @Override
    public void registerNewLocalUser(UserEntity aEntity) {
        userRepository.save(aEntity);
    }
}
