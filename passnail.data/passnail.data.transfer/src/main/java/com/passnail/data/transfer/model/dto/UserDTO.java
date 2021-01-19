package com.passnail.data.transfer.model.dto;

import com.passnail.data.model.entity.CredentialsEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class UserDTO implements Serializable {


    private UUID ID;

    private String login;

    private String password;

    private String emailAddress;

    private Date creationDate;

    private Set<CredentialsEntity> savedCredentials;
}
