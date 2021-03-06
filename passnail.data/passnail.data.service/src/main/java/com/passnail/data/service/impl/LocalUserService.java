package com.passnail.data.service.impl;

import com.passnail.data.access.model.dao.LocalUserRepository;
import com.passnail.data.model.entity.LocalUserEntity;
import com.passnail.data.service.LocalUserServiceIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by: Pszemko at wtorek, 16.02.2021 01:10
 * Project: passnail-client
 */
@Service
public class LocalUserService implements LocalUserServiceIf {

    @Autowired
    private LocalUserRepository localUserRepository;

    @Override
    public Boolean localEmailExists(String aEmail) {
        return (localUserRepository.findByEmailAddress(aEmail) != null ? true : false);
    }

    public Boolean localLoginExists(String aLogin) {
        return (localUserRepository.findByLogin(aLogin) != null ? true : false);
    }

    @Override
    public void registerNewLocalUserName(LocalUserEntity aLocalEntity) {
        localUserRepository.save(aLocalEntity);
    }

    @Override
    public LocalUserEntity getByEmailAddress(String aEmail) {
        return localUserRepository.findByEmailAddress(aEmail);
    }

}
