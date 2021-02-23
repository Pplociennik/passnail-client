package com.passnail.security.map;

import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.data.transfer.model.dto.RegistrationDto;

/**
 * Created by: Pszemko at wtorek, 23.02.2021 00:13
 * Project: passnail-client
 */
public class RegistrationDtoToLoginDtoMapper {

    public LoginDto map(RegistrationDto aDto) {
        LoginDto resultDto = new LoginDto();
        resultDto.setLoginOrEmail(aDto.getLogin());
        resultDto.setPassword(aDto.getPassword());

        return resultDto;
    }
}
