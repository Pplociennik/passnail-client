package com.passnail.data.transfer.model.dto;


import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.status.CredentialsStatus;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * A Data Transfer Object for transferring the {@link com.passnail.data.model.entity.CredentialsEntity} database objects.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class CredentialsDto implements Serializable {

    private String password;

    private String credentialsShortName;

    private String login;

    private String url;

    private String description;

    private Date creationDate;

    private Date lastModificationDate;

    private UUID uniqueIdentifier;

    private CredentialsStatus status;

    public String toString() {
        return credentialsShortName + "   <->   " + url;
    }
}
