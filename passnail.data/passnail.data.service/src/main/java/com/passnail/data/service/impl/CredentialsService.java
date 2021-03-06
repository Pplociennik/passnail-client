package com.passnail.data.service.impl;

import com.passnail.data.access.model.dao.CredentialsRepository;
import com.passnail.data.access.model.dao.UserRepository;
import com.passnail.data.model.entity.CredentialsEntity;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.security.crypto.CryptoUtility;
import com.passnail.data.service.CredentialsServiceIf;
import com.passnail.data.transfer.model.dto.CredentialsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.passnail.data.security.crypto.CredentialsEncoderAES256.decrypt;
import static com.passnail.data.security.crypto.CredentialsEncoderAES256.encrypt;
import static com.passnail.data.security.crypto.CryptoUtility.prepareKey;
import static com.passnail.data.security.crypto.CryptoUtility.prepareSalt;

/**
 * {@inheritDoc}
 * <p>
 * Created by: Pszemko at wtorek, 02.02.2021 18:24
 * Project: passnail-client
 */
//TODO Javadoc
@Service
public class CredentialsService implements CredentialsServiceIf {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private UserRepository userRepository;


    public void sendNewCredentialsToLocalDatabase(CredentialsDto aCredentialsDto, String aUserLogin) {

        UserEntity user = userRepository.findByLogin(aUserLogin);
        var newCredentials = mapCredentialsDtoToCredentialsEntity(aCredentialsDto, aUserLogin, user);
        user.getSavedCredentials().add(newCredentials);

        userRepository.save(user);
    }


    private CredentialsEntity mapCredentialsDtoToCredentialsEntity(CredentialsDto aCredentialsDto, String aUserLogin, UserEntity aUser) {

        var encryptionKey = CryptoUtility.prepareKey(aCredentialsDto.getPassword());
        var encryptionSalt = CryptoUtility.prepareSalt(aCredentialsDto.getPassword());

        var creationDate = new Date();
        var encryptedShortName = encrypt(aCredentialsDto.getCredentialsShortName(), encryptionKey, encryptionSalt);
        var encryptedDescription = encrypt(aCredentialsDto.getDescription(), encryptionKey, encryptionSalt);
        var encryptedUrl = encrypt(aCredentialsDto.getUrl(), encryptionKey, encryptionSalt);


        var encryptedPassword = encrypt(aCredentialsDto.getPassword(), encryptionKey, encryptionSalt);


        return CredentialsEntity.builder()
                .credentialsOwner(aUser)
                .credentialsShortName(encryptedShortName)
                .creationDate(creationDate)
                .description(encryptedDescription)
                .lastModificationDate(creationDate)
                .login(encrypt(aUserLogin, encryptionKey, encryptionSalt))
                .password(encryptedPassword)
                .url(encryptedUrl)
                .build();
    }


    public List<CredentialsDto> decryptEntities(Collection<CredentialsEntity> aEntities, String aPass) {
        List<CredentialsDto> resultList = new LinkedList<>();

        for (CredentialsEntity entity : aEntities) {
            resultList.add(decryptEntity(entity, aPass));
        }

        return resultList;
    }

    private CredentialsDto decryptEntity(CredentialsEntity entity, String aKey) {

        var key = prepareKey(aKey);
        var salt = prepareSalt(aKey);

        return CredentialsDto.builder()
                .password(decrypt(entity.getPassword(), key, salt))
                .login(decrypt(entity.getLogin(), key, salt))
                .lastModificationDate(entity.getLastModificationDate())
                .description(decrypt(entity.getDescription(), key, salt))
                .credentialsShortName(decrypt(entity.getCredentialsShortName(), key, salt))
                .url(decrypt(entity.getUrl(), key, salt))
                .creationDate(entity.getCreationDate())
                .build();
    }
}
