package com.passnail.security.service;

import com.passnail.data.transfer.model.dto.LoginDto;

/**
 * Created by: Pszemko at poniedziałek, 22.02.2021 23:28
 * Project: passnail-client
 */
public interface JWTServiceIf {

    void isValid(String aToken, String aKey);

    String createToken(LoginDto aDto);

    String createOnlineToken(LoginDto aDto);
}
