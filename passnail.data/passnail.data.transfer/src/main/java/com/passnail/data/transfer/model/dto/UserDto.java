package com.passnail.data.transfer.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A Data Transfer Object for transferring the {@link com.passnail.data.model.entity.UserEntity} database objects.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserDto implements Serializable {

    private String login;

    private String password;

    private String emailAddress;

    private Date creationDate;

    private Boolean local;

    private String onlineId;

    private List<CredentialsDto> savedCredentials;
}
