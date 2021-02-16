package com.passnail.security.service;

import com.passnail.data.transfer.model.dto.RegistrationDto;

/**
 * Created by: Pszemko at wtorek, 16.02.2021 00:28
 * Project: passnail-client
 */
public interface ValidationServiceIf {

    void validateRegistrationData(RegistrationDto aDto);
}
