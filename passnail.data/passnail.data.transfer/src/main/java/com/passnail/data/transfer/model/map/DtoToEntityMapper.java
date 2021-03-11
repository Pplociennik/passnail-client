package com.passnail.data.transfer.model.map;

import com.passnail.data.model.entity.CredentialsEntity;
import com.passnail.data.transfer.model.dto.CredentialsDto;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by: Pszemko at czwartek, 11.03.2021 22:33
 * Project: passnail-client
 */
public class DtoToEntityMapper {

    public static List<CredentialsEntity> mapManyCredentialsDtoToEntities(Collection<CredentialsDto> aDtos) {
        return aDtos.stream()
                .map(aDto -> mapSingleCredentialsDtoToEntity(aDto))
                .collect(toList());
    }

    public static CredentialsEntity mapSingleCredentialsDtoToEntity(CredentialsDto aDto) {
        return CredentialsEntity.builder()
                .creationDate(aDto.getCreationDate())
                .credentialsShortName(aDto.getCredentialsShortName())
                .description(aDto.getDescription())
                .login(aDto.getLogin())
                .password(aDto.getPassword())
                .uniqueIdentifier(aDto.getUniqueIdentifier())
                .url(aDto.getUrl())
                .build();
    }
}