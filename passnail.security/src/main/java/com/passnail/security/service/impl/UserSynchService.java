package com.passnail.security.service.impl;

import com.passnail.common.throwable.security.AuthorizationException;
import com.passnail.connect.service.RequestSenderServiceIf;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.service.UserServiceIf;
import com.passnail.security.service.UserSynchServiceIf;
import com.passnail.security.session.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.passnail.connect.util.ConnectionConstants.GENERATE_ONLINE_ID_URI;
import static com.passnail.connect.util.ConnectionConstants.SERVER_RPI_HOST;
import static com.passnail.data.transfer.model.map.EntityToDtoDataMapper.mapSingleUser;
import static com.passnail.security.SecurityConstants.UNAUTHORIZED_USERNAME_SESSION_DATA;

/**
 * Created by: Pszemko at wtorek, 16.03.2021 18:32
 * Project: passnail-client
 */
@Service
public class UserSynchService implements UserSynchServiceIf {

    @Autowired
    private UserServiceIf userService;

    @Autowired
    private RequestSenderServiceIf sender;

    @Override
    public void enableOnlineSynchronizationForUserLoggedIn() {
        SessionData sessionData = SessionData.INSTANCE;
        if (sessionData.getAuthorizedUsername().equals(UNAUTHORIZED_USERNAME_SESSION_DATA)) {
            throw new AuthorizationException("No user authorized!");
        }

        UserEntity offlineUser = userService.findByLogin(sessionData.getAuthorizedUsername());

        String newUniqueOnlineId = sender.sendOnlineIdGenerationRequest(getUrlForRpi(GENERATE_ONLINE_ID_URI), mapSingleUser(offlineUser)).block();

        offlineUser.setOnlineID(newUniqueOnlineId);
        offlineUser.setLocal(false);

        userService.saveUserInDatabase(offlineUser);

        sessionData.setAuthorizedOnlineId(newUniqueOnlineId);
    }

    private String getUrlForRpi(String aUri) {
        StringBuilder builder = new StringBuilder(SERVER_RPI_HOST);
        return builder.append(aUri).toString();
    }
}
