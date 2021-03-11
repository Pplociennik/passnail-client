package com.passnail.connect.service;

import com.passnail.data.transfer.model.dto.SynchronizationResultDto;
import com.passnail.data.transfer.model.dto.UserDto;
import reactor.core.publisher.Mono;

/**
 * Created by: Pszemko at czwartek, 11.03.2021 19:42
 * Project: passnail-client
 */
public interface RequestSenderServiceIf {

    Mono<String> sendOnlineIdGenerationRequest(String aUrl, UserDto aUserDto);

    Mono<SynchronizationResultDto> sendSynchronizationRequest(String aUrl, UserDto aUserDto);
}
