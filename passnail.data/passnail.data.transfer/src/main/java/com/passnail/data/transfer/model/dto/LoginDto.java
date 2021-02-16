package com.passnail.data.transfer.model.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Created by: Pszemko at wtorek, 16.02.2021 00:36
 * Project: passnail-client
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class LoginDto implements Serializable {

    private String loginOrEmail;

    private String password;

    private String onlineID;
}
