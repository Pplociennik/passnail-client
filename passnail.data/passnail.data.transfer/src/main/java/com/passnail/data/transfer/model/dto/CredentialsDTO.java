package com.passnail.data.transfer.model.dto;


import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * A Data Transfer Object for transferring the {@link com.passnail.data.model.entity.CredentialsEntity} entity class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class CredentialsDTO implements Serializable {

    private UUID ID;

    private String password;

    private String credentialsShortName;

    private String login;

    private String url;

    private String description;

    private Date creationDate;

    private Date lastModificationDate;
}
