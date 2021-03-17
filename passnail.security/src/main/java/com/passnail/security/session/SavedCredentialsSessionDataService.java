package com.passnail.security.session;

import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.service.CredentialsServiceIf;
import com.passnail.data.service.UserServiceIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.passnail.data.status.CredentialsStatus.MAINTAINED;
import static java.util.stream.Collectors.toList;

/**
 * Created by: Pszemko at sobota, 06.03.2021 22:16
 * Project: passnail-client
 */
@Service
public class SavedCredentialsSessionDataService {

    @Autowired
    private CredentialsServiceIf credentialsService;

    @Autowired
    private UserServiceIf userService;


    private SessionData sessionData = SessionData.INSTANCE;


    public void refreshAuthorizedUserSavedCredentialsData() {

        UserEntity user = userService.findByLogin(sessionData.getAuthorizedUsername());

        sessionData.getAuthorizedUserSavedCredentials().clear();
        sessionData.setAuthorizedUserSavedCredentials(credentialsService.decryptEntities(user.getSavedCredentials(), sessionData.getPassword()));

        sessionData.setAuthorizedPassNumber(String.valueOf(sessionData.getAuthorizedUserSavedCredentials().stream()
                .filter(c -> c.getStatus().equals(MAINTAINED))
                .collect(toList()).size()));

        sessionData.setAuthorizedOnlineId(user.getOnlineID());
        sessionData.setAuthorizedUsername(user.getLogin());
        sessionData.setAuthorizedUserLastSynchDate(user.getLastSynchronization());
    }
}
