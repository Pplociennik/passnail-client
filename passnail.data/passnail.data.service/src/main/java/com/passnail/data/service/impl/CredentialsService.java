package com.passnail.data.service.impl;

import com.passnail.data.access.model.dao.CredentialsRepository;
import com.passnail.data.access.model.dao.UserRepository;
import com.passnail.data.model.entity.CredentialsEntity;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.service.CredentialsServiceIf;
import com.passnail.data.transfer.model.dto.CredentialsDto;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class CredentialsService implements CredentialsServiceIf {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private UserRepository userRepository;


    public void sendNewCredentialsToLocalDatabase(CredentialsDto aCredentialsDto, String aUserLogin, String aPass) {

        UserEntity user = userRepository.findByLogin(aUserLogin);
        var newCredentials = mapCredentialsDtoToCredentialsEntity(aCredentialsDto, user, aPass);
        user.getSavedCredentials().add(newCredentials);

        userRepository.save(user);

        log.info("Credentials saved!");
    }


    private CredentialsEntity mapCredentialsDtoToCredentialsEntity(CredentialsDto aCredentialsDto, UserEntity aUser, String aPass) {

        var encryptionKey = prepareKey(aPass);
        var encryptionSalt = prepareSalt(aPass);

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
                .login(encrypt(aCredentialsDto.getLogin(), encryptionKey, encryptionSalt))
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

    @Override
    public void removeCredentialsFromTheDatabase(CredentialsDto credentials, String aAuthorizedUserLogin, String aAuthorizedPass) {

        UserEntity authorizedUser = userRepository.findByLogin(aAuthorizedUserLogin);

        var encryptionKey = prepareKey(aAuthorizedPass);
        var encryptionSalt = prepareSalt(aAuthorizedPass);

        String encryptedShortName = encrypt(credentials.getCredentialsShortName(), encryptionKey, encryptionSalt);

        CredentialsEntity toRemove = authorizedUser.getSavedCredentials()
                .stream()
                .filter(
                        c ->
                                c.getCredentialsShortName().equals(encryptedShortName))
                .findFirst().orElseThrow(
                        () -> new IllegalArgumentException("Credentials with a short name: " + credentials.getCredentialsShortName() + " has not been found."));

        List<CredentialsEntity> copy = new LinkedList<>();
        copy.addAll(authorizedUser.getSavedCredentials());
        copy.remove(toRemove);

        authorizedUser.getSavedCredentials().clear();
        authorizedUser.setSavedCredentials(copy);

        userRepository.save(authorizedUser);
    }

    private CredentialsDto decryptEntity(CredentialsEntity entity, String aPass) {

        var key = prepareKey(aPass);
        var salt = prepareSalt(aPass);

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
