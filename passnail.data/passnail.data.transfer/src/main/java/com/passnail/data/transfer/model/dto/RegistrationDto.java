package com.passnail.data.transfer.model.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Created by: Pszemko at wtorek, 16.02.2021 00:37
 * Project: passnail-client
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RegistrationDto implements Serializable {

    private String login;

    private String email;

    private String password;

    private String passwordRepeat;
}
