package com.passnail.data.transfer.model.map;

import com.passnail.data.model.entity.CredentialsEntity;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.transfer.model.dto.CredentialsDto;

import java.util.Collection;
import java.util.List;

import static com.passnail.data.status.CredentialsStatus.MAINTAINED;
import static java.util.stream.Collectors.toList;

/**
 * Created by: Pszemko at czwartek, 11.03.2021 22:33
 * Project: passnail-client
 */
public class DtoToEntityMapper {

    public static List<CredentialsEntity> mapManyCredentialsDtoToEntities(Collection<CredentialsDto> aDtos, UserEntity aOwner) {
        return aDtos.stream()
                .map(aDto -> mapSingleCredentialsDtoToEntity(aDto, aOwner))
                .collect(toList());
    }

    public static CredentialsEntity mapSingleCredentialsDtoToEntity(CredentialsDto aDto, UserEntity aOwner) {
        return CredentialsEntity.builder()
                .creationDate(aDto.getCreationDate())
                .credentialsShortName(aDto.getCredentialsShortName())
                .description(aDto.getDescription())
                .login(aDto.getLogin())
                .password(aDto.getPassword())
                .uniqueIdentifier(aDto.getUniqueIdentifier())
                .url(aDto.getUrl())
                .lastModificationDate(aDto.getLastModificationDate())
                .credentialsOwner(aOwner)
                .status(aDto.getStatus())
                .build();
    }
}
