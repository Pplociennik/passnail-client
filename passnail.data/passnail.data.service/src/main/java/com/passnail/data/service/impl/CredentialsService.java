package com.passnail.data.service.impl;

import com.passnail.data.access.model.dao.CredentialsRepository;
import com.passnail.data.access.model.dao.UserRepository;
import com.passnail.data.model.entity.CredentialsEntity;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.service.CredentialsServiceIf;
import com.passnail.data.transfer.model.dto.CredentialsDto;
import com.passnail.data.transfer.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * {@inheritDoc}
 * <p>
 * Created by: Pszemko at wtorek, 02.02.2021 18:24
 * Project: passnail-client
 */
@Service
public class CredentialsService implements CredentialsServiceIf {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private UserRepository userRepository;


    public void sendNewCredentialsToLocalDatabase(CredentialsDto aCredentialsDto, UserDto aUserDto) {

        UserEntity credentialsOwner = userRepository.getOne(aUserDto.getID());
        Date creationDate = new Date();

        CredentialsEntity newCredentials = CredentialsEntity.builder()
                .credentialsOwner(credentialsOwner)
                .credentialsShortName(aCredentialsDto.getCredentialsShortName())
                .creationDate(creationDate)
                .description(aCredentialsDto.getDescription())
                .lastModificationDate(creationDate)
                .login(aCredentialsDto.getLogin())
                .password(aCredentialsDto.getPassword())
                .url(aCredentialsDto.getUrl())
                .build();

        credentialsRepository.save(newCredentials);
    }
}
