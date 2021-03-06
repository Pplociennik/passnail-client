package com.passnail.data.service;

import com.passnail.data.model.entity.CredentialsEntity;
import com.passnail.data.transfer.model.dto.CredentialsDto;

import java.util.Collection;
import java.util.List;

/**
 * A service containing functions for managing credentials stored in the local database.
 * <p>
 * Created by: Pszemko at poniedziałek, 01.03.2021 19:34
 * Project: passnail-client
 */
public interface CredentialsServiceIf {

    /**
     * Creates a new {@link CredentialsEntity} and saves it in the database.
     *
     * @param aCredentialsDto A dto containing information about the credentials being created.
     * @param aUserLogin      A string being a login of the user.
     */
    void sendNewCredentialsToLocalDatabase(CredentialsDto aCredentialsDto, String aUserLogin, String aPass);

    List<CredentialsDto> decryptEntities(Collection<CredentialsEntity> aEntities, String aPass);
}
