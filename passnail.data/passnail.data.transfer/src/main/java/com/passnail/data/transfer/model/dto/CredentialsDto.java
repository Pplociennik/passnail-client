package com.passnail.data.transfer.model.dto;


import lombok.*;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public String toString() {
        return credentialsShortName + "   <->   " + url;
    }
}
