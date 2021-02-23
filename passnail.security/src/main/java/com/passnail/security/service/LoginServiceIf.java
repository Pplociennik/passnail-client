package com.passnail.security.service;

import com.passnail.data.transfer.model.dto.LoginDto;

/**
 * Created by: Pszemko at wtorek, 16.02.2021 00:27
 * Project: passnail-client
 */
public interface LoginServiceIf {


    void authenticateAndLoginUser(LoginDto aDto);
}
