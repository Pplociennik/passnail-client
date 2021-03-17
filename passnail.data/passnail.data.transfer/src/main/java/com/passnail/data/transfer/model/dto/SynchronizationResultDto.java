package com.passnail.data.transfer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by: Pszemko at czwartek, 11.03.2021 18:40
 * Project: passnail-server
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SynchronizationResultDto implements Serializable {

    private List<CredentialsDto> toCreateOnClient;

    private List<CredentialsDto> toUpdateOnClient;

    private List<CredentialsDto> toDeleteOnClient;

    private List<CredentialsDto> createdOnServer;
}
