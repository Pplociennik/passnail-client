package com.passnail.data.service.impl;

import com.passnail.data.access.model.dao.CredentialsRepository;
import com.passnail.data.access.model.dao.UserRepository;
import com.passnail.data.model.entity.CredentialsEntity;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.service.UserServiceIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.passnail.data.security.crypto.CredentialsEncoderAES256.encrypt;
import static com.passnail.data.security.crypto.CryptoUtility.prepareKey;
import static com.passnail.data.security.crypto.CryptoUtility.prepareSalt;

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

    @Override
    public void encryptCredentialsAndSaveUserInDatabase(UserEntity aEntity, String aAuthorizedPass) {
        var encryptionKey = prepareKey(aAuthorizedPass);
        var encryptionSalt = prepareSalt(aAuthorizedPass);

        for (CredentialsEntity c : aEntity.getSavedCredentials()) {
            c.setUrl(encrypt(c.getUrl(), encryptionKey, encryptionSalt));
            c.setPassword(encrypt(c.getPassword(), encryptionKey, encryptionSalt));
            c.setLogin(encrypt(c.getLogin(), encryptionKey, encryptionSalt));
            c.setDescription(encrypt(c.getDescription(), encryptionKey, encryptionSalt));
            c.setCredentialsShortName(encrypt(c.getCredentialsShortName(), encryptionKey, encryptionSalt));
        }
        userRepository.save(aEntity);
    }
}
