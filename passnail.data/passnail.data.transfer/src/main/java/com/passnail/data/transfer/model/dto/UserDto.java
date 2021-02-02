package com.passnail.data.transfer.model.dto;

import com.passnail.data.model.entity.CredentialsEntity;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * A Data Transfer Object for transferring the {@link com.passnail.data.model.entity.UserEntity} database objects.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserDto implements Serializable {

    private UUID ID;

    private String login;

    private String password;

    private String emailAddress;

    private Date creationDate;

    private Boolean local;

    private Set<CredentialsEntity> savedCredentials;
}
