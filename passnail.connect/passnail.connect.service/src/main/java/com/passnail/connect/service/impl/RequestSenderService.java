package com.passnail.connect.service.impl;

import com.passnail.connect.service.RequestSenderServiceIf;
import com.passnail.data.transfer.model.dto.SynchronizationResultDto;
import com.passnail.data.transfer.model.dto.UserDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Created by: Pszemko at czwartek, 11.03.2021 19:42
 * Project: passnail-client
 */
public class RequestSenderService implements RequestSenderServiceIf {


    @Override
    public Mono<String> sendOnlineIdGenerationRequest(String aUrl, UserDto aUserDto) {
        WebClient client = createWebClient(aUrl);

        return client.post()
                .body(Mono.just(aUserDto), UserDto.class)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public Mono<SynchronizationResultDto> sendSynchronizationRequest(String aUrl, UserDto aUserDto) {
        WebClient client = createWebClient(aUrl);

        return client.post()
                .body(Mono.just(aUserDto), UserDto.class)
                .retrieve()
                .bodyToMono(SynchronizationResultDto.class);
    }

    private WebClient createWebClient(String aUrl) {
        return WebClient.builder()
                .baseUrl(aUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
