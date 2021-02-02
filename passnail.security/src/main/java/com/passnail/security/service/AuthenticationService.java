package com.passnail.security.service;

import com.passnail.data.access.model.dao.UserRepository;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.transfer.model.dto.LocalUserDto;
import com.passnail.security.throwable.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by: Pszemko at wtorek, 02.02.2021 19:20
 * Project: passnail-client
 */
@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;


    public void validateNewLocalUserCredentials(LocalUserDto aUserDto) {
        Objects.nonNull(aUserDto);
        checkIfLoginUnique(aUserDto.getLogin());
    }


    private void checkIfLoginUnique(String aLogin) {
        Objects.nonNull(aLogin);

        UserEntity userByLogin = userRepository.findByLogin(aLogin);
        if (userByLogin == null) {
            throw new AuthenticationException("Login '" + aLogin + "' is already taken!");
        }
    }
}
