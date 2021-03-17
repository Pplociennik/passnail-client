package com.passnail.connect.service.impl;

import com.passnail.common.throwable.security.AuthorizationException;
import com.passnail.connect.service.RequestSenderServiceIf;
import com.passnail.connect.service.SynchronizationServiceIf;
import com.passnail.data.model.entity.CredentialsEntity;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.service.UserServiceIf;
import com.passnail.data.transfer.model.dto.CredentialsDto;
import com.passnail.data.transfer.model.dto.SynchronizationResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.passnail.connect.util.ConnectionConstants.SERVER_RPI_HOST;
import static com.passnail.connect.util.ConnectionConstants.SYNCHRONIZE_DATA_URI;
import static com.passnail.data.status.CredentialsStatus.REMOVED;
import static com.passnail.data.transfer.model.map.DtoToEntityMapper.mapManyCredentialsDtoToEntities;
import static com.passnail.data.transfer.model.map.EntityToDtoDataMapper.mapSingleUser;

/**
 * {@inheritDoc}
 * <p>
 * Created by: Pszemko at wtorek, 23.02.2021 00:23
 * Project: passnail-client
 */
@Service
public class SynchronizationService implements SynchronizationServiceIf {

    @Autowired
    private UserServiceIf userService;

    @Autowired
    private RequestSenderServiceIf sender;


    @Override
    public void synchronize(String aUserName) {
        UserEntity userBeingSynchronizing = userService.findByLogin(aUserName);

        List<CredentialsEntity> userCredentials = new LinkedList<>();

        if (userBeingSynchronizing == null) {
            throw new AuthorizationException("No user authorized!");
        }

        for (CredentialsEntity c : userBeingSynchronizing.getSavedCredentials()) {
            userCredentials.add(
                    CredentialsEntity.builder()
                            .creationDate(c.getCreationDate())
                            .credentialsShortName(c.getCredentialsShortName()
                            )
                            .lastModificationDate(c.getLastModificationDate())
                            .description(c.getDescription())
                            .login(c.getLogin())
                            .password(c.getPassword())
                            .uniqueIdentifier(c.getUniqueIdentifier())
                            .url(c.getUrl())
                            .status(c.getStatus())
                            .build());
        }

        UserEntity copy = UserEntity.builder()
                .onlineID(userBeingSynchronizing.getOnlineID())
                .savedCredentials(userCredentials)
                .build();


        SynchronizationResultDto aResponseDto =
                sender.sendSynchronizationRequest(getUrlForRpi(SYNCHRONIZE_DATA_URI), mapSingleUser(copy)).block();

        manageResponseData(aResponseDto, userBeingSynchronizing);
        userBeingSynchronizing.setLastSynchronization(new Date());
        userService.saveUserInDatabase(userBeingSynchronizing);

    }

    private void manageResponseData(SynchronizationResultDto aResponseDto, UserEntity aUserBeingSynchronizing) {
        deleteOnThisClient(aResponseDto.getToDeleteOnClient(), aUserBeingSynchronizing);
        setUniqueIdentifiersForExistingCredentials(aResponseDto.getCreatedOnServer(), aUserBeingSynchronizing);
        createNewInThisClient(aResponseDto.getToCreateOnClient(), aUserBeingSynchronizing);
        updateExistingOnClient(aResponseDto.getToUpdateOnClient(), aUserBeingSynchronizing);
    }

    private void setUniqueIdentifiersForExistingCredentials(List<CredentialsDto> createdOnServer, UserEntity aUserBeingSynchronizing) {
        if (createdOnServer.isEmpty()) {
            return;
        }

        for (CredentialsEntity fromServer : mapManyCredentialsDtoToEntities(createdOnServer, aUserBeingSynchronizing)) {
            for (CredentialsEntity fromClient : aUserBeingSynchronizing.getSavedCredentials()) {
                if (fromClient.equals(fromServer)) {
                    fromClient.setUniqueIdentifier(fromServer.getUniqueIdentifier());
                }
            }
        }
    }

    private void createNewInThisClient(List<CredentialsDto> toCreateOnClient, UserEntity aUserBeingSynchronizing) {
        if (toCreateOnClient.isEmpty()) {
            return;
        }

        aUserBeingSynchronizing.getSavedCredentials().addAll(mapManyCredentialsDtoToEntities(toCreateOnClient, aUserBeingSynchronizing));
    }

    private void updateExistingOnClient(List<CredentialsDto> toUpdateOnClient, UserEntity aUserBeingSynchronizing) {
        if (toUpdateOnClient.isEmpty()) {
            return;
        }

        for (CredentialsEntity fromServer : mapManyCredentialsDtoToEntities(toUpdateOnClient, aUserBeingSynchronizing)) {
            for (CredentialsEntity fromClient : aUserBeingSynchronizing.getSavedCredentials()) {
                if (fromClient.getUniqueIdentifier().equals(fromServer.getUniqueIdentifier())) {
                    fromClient.setCredentialsShortName(fromServer.getCredentialsShortName());
                    fromClient.setDescription(fromServer.getDescription());
                    fromClient.setLastModificationDate(fromServer.getLastModificationDate());
                    fromClient.setLogin(fromServer.getLogin());
                    fromClient.setPassword(fromServer.getPassword());
                    fromClient.setUrl(fromServer.getUrl());
                    fromClient.setCreationDate(fromServer.getCreationDate());
                }
            }
        }
    }

    private void deleteOnThisClient(List<CredentialsDto> toDeleteOnClient, UserEntity aUserBeingSynchronizing) {
        if (toDeleteOnClient.isEmpty()) {
            return;
        }

        for (CredentialsEntity toRemove : mapManyCredentialsDtoToEntities(toDeleteOnClient, aUserBeingSynchronizing)) {
            for (CredentialsEntity fromClient : aUserBeingSynchronizing.getSavedCredentials()) {
                if (toRemove.getUniqueIdentifier().equals(fromClient.getUniqueIdentifier())) {
                    fromClient.setStatus(REMOVED);
                }
            }
        }

    }


    private String getUrlForRpi(String aUri) {
        StringBuilder builder = new StringBuilder(SERVER_RPI_HOST);
        return builder.append(aUri).toString();
    }
}
