package com.passnail.security.service;

import com.passnail.data.transfer.model.dto.LoginDto;

/**
 * Created by: Pszemko at wtorek, 23.02.2021 00:38
 * Project: passnail-client
 */
public interface LoginValidationServiceIf {

    void validateLocalLoginOrEmail(String aLoginOrEmail);

    void validatePasswordInUserDb(LoginDto aDto);
}
