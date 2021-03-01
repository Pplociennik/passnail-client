package com.passnail.security.service;

import com.passnail.data.transfer.model.dto.RegistrationDto;

/**
 * A service for the registration data validation.
 * <p>
 * Created by: Pszemko at wtorek, 16.02.2021 00:28
 * Project: passnail-client
 */
public interface RegistrationValidationServiceIf {

    /**
     * Validates the user's registration data.
     *
     * @param aDto A registration data.
     */
    void validateRegistrationData(RegistrationDto aDto);
}
