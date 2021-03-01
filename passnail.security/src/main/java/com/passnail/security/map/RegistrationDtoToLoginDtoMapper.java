package com.passnail.security.map;

import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.data.transfer.model.dto.RegistrationDto;

/**
 * A mapper of the {@link RegistrationDto} typed objects to the {@link LoginDto} type objects.
 * <p>
 * Created by: Pszemko at wtorek, 23.02.2021 00:13
 * Project: passnail-client
 */
public class RegistrationDtoToLoginDtoMapper {

    /**
     * Maps the {@link RegistrationDto} typed objects to the {@link LoginDto} type objects.
     *
     * @param aDto {@link RegistrationDto}
     * @return {@link LoginDto}
     */
    public LoginDto map(RegistrationDto aDto) {
        LoginDto resultDto = new LoginDto();
        resultDto.setLoginOrEmail(aDto.getLogin());
        resultDto.setPassword(aDto.getPassword());

        return resultDto;
    }
}
