package com.passnail.data.service.impl;

import com.passnail.data.access.model.dao.CredentialsRepository;
import com.passnail.data.access.model.dao.UserRepository;
import com.passnail.data.model.entity.CredentialsEntity;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.security.crypto.CredentialsEncoderAES256;
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


        credentialsRepository.save(mapCredentialsDtoToCredentialsEntity(aCredentialsDto, aUserLogin));
    }


    private CredentialsEntity mapCredentialsDtoToCredentialsEntity(CredentialsDto aCredentialsDto, String aUserLogin) {

        UserEntity credentialsOwner = userRepository.findByLogin(aUserLogin);
        var creationDate = new Date();
        var shortName = aCredentialsDto.getCredentialsShortName();
        var description = aCredentialsDto.getDescription();
        var url = aCredentialsDto.getUrl();

        var encryptionKey = CryptoUtility.prepareKey(aCredentialsDto.getPassword());
        var encryptionSalt = CryptoUtility.prepareSalt(aCredentialsDto.getPassword());

        var encryptedPassword = encrypt(aCredentialsDto.getPassword(), encryptionKey, encryptionSalt);


        return CredentialsEntity.builder()
                .credentialsOwner(credentialsOwner)
                .credentialsShortName(shortName)
                .creationDate(creationDate)
                .description(description)
                .lastModificationDate(creationDate)
                .login(aUserLogin)
                .password(encryptedPassword)
                .url(url)
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
